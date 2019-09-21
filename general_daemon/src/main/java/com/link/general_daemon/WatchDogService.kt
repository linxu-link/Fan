package com.link.general_daemon

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit


class WatchDogService : Service() {

    /**
     * 守护服务，运行在:watch子进程中
     */
    protected fun onStart(intent: Intent, flags: Int, startId: Int): Int {

        if (!DaemonEnv.sInitialized) return START_STICKY

        if (sDisposable != null && !sDisposable!!.isDisposed()) return Service.START_STICKY

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            startForeground(Companion.HASH_CODE, Notification())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                DaemonEnv.startServiceSafely(Intent(DaemonEnv.sApp, WatchDogNotificationService::class.java))
        }

        //定时检查 AbsWorkService 是否在运行，如果不在运行就把它拉起来
        //Android 5.0+ 使用 JobScheduler，效果比 AlarmManager 好
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val builder = JobInfo.Builder(Companion.HASH_CODE, ComponentName(DaemonEnv.sApp, JobSchedulerService::class.java))
            builder.setPeriodic(DaemonEnv.getWakeUpInterval())
            //Android 7.0+ 增加了一项针对 JobScheduler 的新限制，最小间隔只能是下面设定的数字
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) builder.setPeriodic(JobInfo.getMinPeriodMillis(), JobInfo.getMinFlexMillis())
            builder.setPersisted(true)
            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(builder.build())
        } else {
            //Android 4.4- 使用 AlarmManager
            val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val i = Intent(DaemonEnv.sApp, DaemonEnv.sServiceClass)
            sPendingIntent = PendingIntent.getService(DaemonEnv.sApp, Companion.HASH_CODE, i, PendingIntent.FLAG_UPDATE_CURRENT)
            am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + DaemonEnv.getWakeUpInterval(), DaemonEnv.getWakeUpInterval(), sPendingIntent)
        }

        //使用定时 Observable，避免 Android 定制系统 JobScheduler / AlarmManager 唤醒间隔不稳定的情况
        sDisposable = Observable
                .interval(DaemonEnv.getWakeUpInterval(), TimeUnit.MILLISECONDS)
                .subscribe(object : Consumer<Long> {
                    @Throws(Exception::class)
                    override fun accept(aLong: Long?) {
                        DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass!!)
                    }
                }, object : Consumer<Throwable> {
                    @Throws(Exception::class)
                    override fun accept(throwable: Throwable) {
                        throwable.printStackTrace()
                    }
                })

        //守护 Service 组件的启用状态, 使其不被 MAT 等工具禁用
        packageManager.setComponentEnabledSetting(ComponentName(packageName, DaemonEnv.sServiceClass!!.name),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)

        return START_STICKY
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return onStart(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        onStart(intent, 0, 0)
        return null
    }

    protected fun onEnd(rootIntent: Intent?) {
        if (!DaemonEnv.sInitialized) return
        DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass!!)
        DaemonEnv.startServiceMayBind(WatchDogService::class.java)
    }

    /**
     * 最近任务列表中划掉卡片时回调
     */
    override fun onTaskRemoved(rootIntent: Intent) {
        onEnd(rootIntent)
    }

    /**
     * 设置-正在运行中停止服务时回调
     */
    override fun onDestroy() {
        onEnd(null)
    }


    class WatchDogNotificationService : Service() {

        /**
         * 利用漏洞在 API Level 18 及以上的 Android 系统中，启动前台服务而不显示通知
         * 运行在:watch子进程中
         */
        override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
            startForeground(HASH_CODE, Notification())
            stopSelf()
            return START_STICKY
        }

        override fun onBind(intent: Intent): IBinder? {
            return null
        }
    }

    companion object {

        var sDisposable: Disposable? = null
        var sPendingIntent: PendingIntent? = null

        const val HASH_CODE = 2

        /**
         * 用于在不需要服务运行的时候取消 Job / Alarm / Subscription.
         *
         * 因 WatchDogService 运行在 :watch 子进程, 请勿在主进程中直接调用此方法.
         * 而是向 WakeUpReceiver 发送一个 Action 为 WakeUpReceiver.ACTION_CANCEL_JOB_ALARM_SUB 的广播.
         */
        fun cancelJobAlarmSub() {
            if (!DaemonEnv.sInitialized) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val scheduler = DaemonEnv.sApp!!.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                scheduler.cancel(HASH_CODE)
            } else {
                val am = DaemonEnv.sApp!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                if (sPendingIntent != null) am.cancel(sPendingIntent)
            }
            if (sDisposable != null) sDisposable!!.dispose()
        }
    }
}
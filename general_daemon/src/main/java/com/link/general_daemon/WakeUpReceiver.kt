package com.link.general_daemon

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


const val ACTION_CANCEL_JOB="com.link.daemon.CANCEL_JOB"

/**
 * @author WJ
 * @date 2019-08-21
 *
 * 描述：监听系统广播，用于启动service
 */
class WakeUpReceiver: BroadcastReceiver() {

    /**
     * 监听 8 种系统广播 :
     * CONNECTIVITY\_CHANGE, USER\_PRESENT, ACTION\_POWER\_CONNECTED, ACTION\_POWER\_DISCONNECTED,
     * BOOT\_COMPLETED, MEDIA\_MOUNTED, PACKAGE\_ADDED, PACKAGE\_REMOVED.
     * 在网络连接改变, 用户屏幕解锁, 电源连接 / 断开, 系统启动完成, 挂载 SD 卡, 安装 / 卸载软件包时拉起 Service.
     * Service 内部做了判断，若 Service 已在运行，不会重复启动.
     * 运行在:watch子进程中.
     */
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent != null && ACTION_CANCEL_JOB == intent.action) {
            WatchDogService.cancelJobAlarmSub()
            return
        }
        if (!DaemonEnv.sInitialized) return
        DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass!!)
    }


    class WakeUpAutoStartReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (!DaemonEnv.sInitialized) return
            DaemonEnv.startServiceMayBind(DaemonEnv.sServiceClass!!)
        }
    }


}
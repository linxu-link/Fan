package com.link.librarycomponent

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.tasks.*
import com.link.librarymodule.BaseApplication
import com.link.librarymodule.BuildConfig
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.launchstarter.TaskDispatcher
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV
import com.tencent.smtt.sdk.QbSdk

/**
 * @author WJ
 * @date 2019-08-02
 *
 * 描述：项目的基础Application
 */
abstract class FanApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }

    companion object {
        /**
         * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
         *
         * @param application
         */
        @Synchronized
        @JvmStatic
        fun setApplication(@NonNull application: Application) {
            Utils.init(application)

            application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {

                }

                override fun onActivityStarted(activity: Activity?) {

                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

                }

                override fun onActivityStopped(activity: Activity?) {

                }

                override fun onActivityDestroyed(activity: Activity?) {
                    AppManager.instance.removeActivity(activity)
                }

                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    AppManager.instance.addActivity(activity)
                }

            })

            //task初始化调度器
            TaskDispatcher.init(application)
            val dispatcher = TaskDispatcher.createInstance()

            dispatcher.addTask(ARouterTask())
                    .addTask(BmobTask())
                    .addTask(BuglyTask())
                    .addTask(MMKVTask())
                    .addTask(X5WebViewTask())
                    .start()

            dispatcher.await()
        }


    }


}
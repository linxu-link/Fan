package com.link.librarymodule

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils
import com.link.librarymodule.constant.Constant
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV

/**
 * @author WJ
 * @date 2019-08-02
 *
 * 描述：框架提供application基础类
 */
abstract class BaseApplication : Application() {

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
            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
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
        }

    }


    /**
     * Application 初始化
     */
    abstract fun initModuleApp(application: Application)

    /**
     * 所有 Application 初始化后的自定义操作
     */
    abstract fun initModuleData(application: Application)


}
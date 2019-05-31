package com.link.librarymodule

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils

abstract class BaseApplication : Application() {

    lateinit var instance: Application

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    @Synchronized
    fun setApplication(@NonNull application: Application) {
        instance = application
        Utils.init(application)
        initARouter(application)
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

    /**
     * Application 初始化
     */
    abstract fun initModuleApp(application: Application)

    /**
     * 所有 Application 初始化后的自定义操作
     */
    abstract fun initModuleData(application: Application)

    /**
     * 初始化ARouter
     */
    private fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
    }

}
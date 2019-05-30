package com.link.librarymodule

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils

abstract class BaseApplication : Application() {

    lateinit var instance: Application

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
        Utils.init(this)
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    @Synchronized
    fun setApplication(@NonNull application: Application) {
        instance = application
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


}
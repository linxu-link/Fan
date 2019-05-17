package com.link.librarymodule

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.annotation.NonNull

abstract class BaseApplication : Application() {

    private lateinit var instance:Application

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
   /* @Synchronized
    fun setApplication(@NonNull application: Application) {
        sInstance = application
        //初始化工具类
        Utils.init(application)
        //注册监听每个activity的生命周期,便于堆栈式管理
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
                AppManager.getAppManager().addActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {
                AppManager.getAppManager().removeActivity(activity)
            }
        })
    }*/
    @Synchronized
    fun setApplication(@NonNull application: Application){
        instance=application
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityResumed(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityStarted(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityStopped(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityDestroyed(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
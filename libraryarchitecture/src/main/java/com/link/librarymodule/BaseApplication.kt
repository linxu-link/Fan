package com.link.librarymodule

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils
import com.tencent.mmkv.MMKV


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
            initARouter(application)
            initBmobSdk(application.applicationContext)
            initMMKV(application)

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
         * 初始化ARouter
         */
        private fun initARouter(application: Application) {
            if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
                ARouter.openLog();     // 打印日志
                ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            }
            ARouter.init(application); // 尽可能早，推荐在Application中初始化
        }

        /**
         * 初始化Bmob后台服务器的SDK
         */
        private fun initBmobSdk(context: Context) {
            Bmob.initialize(context, Constant.BMOB_ID)
        }

        /**
         * 初始化MMKV（一种腾讯出品的sp替代工具）
         */
        private fun initMMKV(context: Context) {
            MMKV.initialize(context)
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
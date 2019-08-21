package com.link.fan.tinker

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.Keep
import androidx.multidex.MultiDex
import com.link.fan.tasks.*
import com.link.librarycomponent.AppConfig
import com.link.librarycomponent.FanApplication
import com.link.librarymodule.BaseApplication
import com.link.librarymodule.launchstarter.TaskDispatcher
import com.link.librarymodule.utils.Utils
import com.tencent.tinker.anno.DefaultLifeCycle
import com.tencent.tinker.entry.DefaultApplicationLike
import com.tencent.tinker.loader.shareutil.ShareConstants

@DefaultLifeCycle(application = "com.link.fan.tinker.MainTinkerApplication", flags = ShareConstants.TINKER_ENABLE_ALL, loadVerifyFlag = false)
class MainTinkerLike(application: Application, tinkerFlags: Int, tinkerLoadVerifyFlag: Boolean, applicationStartElapsedTime: Long, applicationStartMillisTime: Long, tinkerResultIntent: Intent) : DefaultApplicationLike(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent) {

    override fun onCreate() {
        super.onCreate()
        FanApplication.setApplication(application)
        //task初始化调度器
        TaskDispatcher.init(application)
        val dispatcher = TaskDispatcher.createInstance()

        dispatcher.addTask(ARouterTask())
                .addTask(BmobTask())
                .addTask(BuglyTask())
                .addTask(MMKVTask())
                .addTask(H5Task())
                .addTask(DaemonTask())
                .addTask(UtilTask())
                .start()
        dispatcher.await()

        initModuleApp(application)
        initModuleData(application)

        if (Build.VERSION.SDK_INT >= 28) {
            closeAndroidPDialog()
        }
    }

    override fun onBaseContextAttached(base: Context?) {
        super.onBaseContextAttached(base)
        MultiDex.install(base)
        TinkerManager.installTinker(this)
    }

    private fun initModuleApp(application: Application) {
        for (moduleApp in AppConfig.moduleApps) {
            try {
                val clazz = Class.forName(moduleApp)
                val baseApp = clazz.newInstance() as BaseApplication
                baseApp.initModuleApp(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
    }

    private fun initModuleData(application: Application) {
        for (moduleApp in AppConfig.moduleApps) {
            try {
                val clazz = Class.forName(moduleApp)
                val baseApp = clazz.newInstance() as BaseApplication
                baseApp.initModuleData(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
    }

    private fun closeAndroidPDialog() {
        try {
            val aClass = Class.forName("android.content.pm.PackageParser\$Package")
            val declaredConstructor = aClass.getDeclaredConstructor(String::class.java)
            declaredConstructor.setAccessible(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val cls = Class.forName("android.app.ActivityThread")
            val declaredMethod = cls.getDeclaredMethod("currentActivityThread")
            declaredMethod.isAccessible = true
            val activityThread = declaredMethod.invoke(null)
            val mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown")
            mHiddenApiWarningShown.isAccessible = true
            mHiddenApiWarningShown.setBoolean(activityThread, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}

package com.link.librarycomponent

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.annotation.Keep
import androidx.annotation.NonNull
import com.link.librarymodule.BaseApplication
import com.link.librarymodule.utils.AppManager

/**
 * @author WJ
 * @date 2019-08-02
 *
 * 描述：项目的基础Application
 */
@Keep
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
        }


    }


}
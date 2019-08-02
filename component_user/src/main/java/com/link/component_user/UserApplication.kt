package com.link.component_user

import android.app.Application
import com.link.librarycomponent.FanApplication
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication
/**
 * @author WJ
 * @date 2019-06-03
 *
 * 描述：
 */
class UserApplication : FanApplication() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {
        ServiceFactory.getInstance().userService=UserService()
    }

    override fun initModuleData(application: Application) {

    }
}
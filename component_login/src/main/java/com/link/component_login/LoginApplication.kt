package com.link.component_login

import android.app.Application
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication

class LoginApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {

    }

    override fun initModuleData(application: Application) {
        ServiceFactory.getInstance().loginService = LoginService()
    }


}
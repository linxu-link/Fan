package com.link.component_main

import android.app.Application
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication

class MainApplication : BaseApplication() {
    override fun initModuleApp(application: Application) {
        ServiceFactory.getInstance().mainService = MainService()
    }

    override fun initModuleData(application: Application) {
    }
}
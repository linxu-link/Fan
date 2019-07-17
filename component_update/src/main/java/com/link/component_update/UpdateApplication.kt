package com.link.component_update

import android.app.Application
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication

class UpdateApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initModuleData(this)
    }

    override fun initModuleData(application: Application) {
        ServiceFactory.getInstance().updateService = UpdateService()
    }

    override fun initModuleApp(application: Application) {

    }
}
package com.link.component_find

import android.app.Application
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication

class FindApplication : BaseApplication() {
    override fun initModuleData(application: Application) {
    }

    override fun initModuleApp(application: Application) {
        ServiceFactory.getInstance().findService = FindService()
    }
}
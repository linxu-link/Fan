package com.link.component_catalog

import android.app.Application
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication

class CatalogApplication : BaseApplication() {

    override fun initModuleApp(application: Application) {
        ServiceFactory.getInstance().catalogService = CatalogService()
    }

    override fun initModuleData(application: Application) {
    }
}
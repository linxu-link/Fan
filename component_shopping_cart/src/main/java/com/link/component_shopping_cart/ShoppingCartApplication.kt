package com.link.component_shopping_cart

import android.app.Application
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication

class ShoppingCartApplication : BaseApplication() {


    override fun initModuleApp(application: Application) {

        ServiceFactory.getInstance().shopService = ShoppingCartService()

    }

    override fun initModuleData(application: Application) {
    }
}
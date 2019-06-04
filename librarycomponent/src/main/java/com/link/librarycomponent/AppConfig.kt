package com.link.librarycomponent

open class AppConfig {

    companion object {

        val MAIN_MODULE = "com.link.component_main.MainApplication"

        val LOGIN_MODULE = "com.link.component_login.LoginApplication"
        val SPLASH_MODULE = "com.link.component_splash.SplashApplication"
        val USER_MODULE = "com.link.component_user.UserApplication"
        val SHOP_MODULE = "com.link.component_shopping_cart.ShoppingCartApplication"
        val FIND_MODULE = "com.link.component_find.FindApplication"
        val CATALOG_MODULE = "com.link.component_catalog.CatalogApplication"

        val moduleApps = arrayOf(LOGIN_MODULE, SPLASH_MODULE,
                USER_MODULE,SHOP_MODULE,FIND_MODULE, MAIN_MODULE, CATALOG_MODULE)
    }

}
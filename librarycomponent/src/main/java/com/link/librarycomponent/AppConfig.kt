package com.link.librarycomponent

open class AppConfig {

    companion object {

        val MAIN_MODULE = "com.link.component_main.MainApplication"
        val LOGIN_MODULE = "com.link.component_login.LoginApplication"
        val SPLASH_MODULE = "com.link.component_splash.SplashApplication"
        val USER_MODULE = "com.link.component_user.UserApplication"

        @JvmStatic
        val moduleApps = arrayOf(LOGIN_MODULE, SPLASH_MODULE,
                USER_MODULE, MAIN_MODULE)
    }

}
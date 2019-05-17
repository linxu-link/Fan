package com.link.librarymodule.utils

class AppManager {



    companion object {
        val instance = Inner.appManager
    }

    private object Inner {
        val appManager = AppManager()
    }

}
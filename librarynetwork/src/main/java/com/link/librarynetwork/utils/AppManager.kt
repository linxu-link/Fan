package com.link.librarynetwork.utils

class AppManager {



    companion object {
        val instance = Inner.appManager
    }

    private object Inner {
        val appManager = AppManager()
    }

}
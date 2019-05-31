package com.link.librarycomponent

class ServiceFactory {


    companion object {
            @Volatile
            private var instance: ServiceFactory? = null

            fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: ServiceFactory().also {
                        instance = it
                    }
                }
        }

}
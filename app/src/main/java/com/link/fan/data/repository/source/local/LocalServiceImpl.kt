package com.link.fan.data.repository.source.local

class LocalServiceImpl :ILocalService{

    companion object {
            @Volatile
            private var instance: LocalServiceImpl? = null

            fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: LocalServiceImpl().also {
                        instance = it
                    }
                }
        }
}
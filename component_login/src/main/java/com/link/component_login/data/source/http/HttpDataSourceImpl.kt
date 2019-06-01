package com.link.component_login.data.source.http

class HttpDataSourceImpl constructor(private val service: HttpService) : IHttpDataSource {


    companion object {
        @Volatile
        private var instance: HttpDataSourceImpl? = null

        fun getInstance(service: HttpService) =
            instance ?: synchronized(this) {
                instance ?: HttpDataSourceImpl(service).also {
                    instance = it
                }
            }
    }

}
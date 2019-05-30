package com.link.component_splash.data.source.http

class HttpDataSourceImpl constructor(private val service:SplashHttpService) :IHttpDataSource{

    companion object{

        val instance:HttpDataSourceImpl by lazy(mode=LazyThreadSafetyMode.SYNCHRONIZED){
            HttpDataSourceImpl()
        }

    }


}
package com.link.component_splash.data

import com.link.component_splash.data.source.http.HttpDataSourceImpl
import com.link.component_splash.data.source.http.SplashHttpService
import com.link.component_splash.data.source.local.LocalDataSourceImpl
import com.link.librarymodule.http.RetrofitClient

class Injection {

    companion object {

        fun provideRepository(): SplashRepository {

            val service: SplashHttpService = RetrofitClient.getInstance().create(SplashHttpService::class.java)
            //网络数据源
            val httpService = HttpDataSourceImpl.getInstance(service)
            //本地数据源
            val localService = LocalDataSourceImpl()

            return SplashRepository.getInstance(httpService, localService)
        }

    }
}
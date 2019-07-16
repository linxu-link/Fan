package com.link.component_search.data

import com.link.component_search.data.source.http.HttpDataSourceImpl
import com.link.component_search.data.source.http.HttpService
import com.link.component_search.data.source.local.LocalDataSourceImpl
import com.link.librarymodule.http.RetrofitClient

class Injection {

    companion object {

        fun provideRepository(): SearchRepository {

            val service: HttpService = RetrofitClient.getInstance().create(HttpService::class.java)
            //网络数据源
            val httpService = HttpDataSourceImpl.getInstance(service)
            //本地数据源
            val localService = LocalDataSourceImpl()

            return SearchRepository.getInstance(httpService, localService)
        }

    }
}
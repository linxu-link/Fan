package com.link.fan.data

import android.view.View
import com.link.fan.data.repository.AppRepository
import com.link.fan.data.repository.source.local.LocalServiceImpl
import com.link.fan.data.repository.source.net.NetServiceImpl
import com.link.fan.data.repository.source.net.RetrofitHttpService
import com.link.librarymodule.http.RetrofitClient

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-19:32
 * email:wujia0916@thundersoft.com
 * description:  向外提供数据仓库和viewModel的实例
 */
object InjectorUtils {

    private fun provideRepository(): AppRepository {

        val service: RetrofitHttpService = RetrofitClient.getInstance().create(RetrofitHttpService::class.java)
        //网络数据源
        val httpService = NetServiceImpl.getInstance(service)
        //本地数据源
        val localService = LocalServiceImpl.getInstance()
        //创建数据源归总处理类
        return AppRepository.getInstance(httpService, localService)
    }


    fun loginViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(provideRepository())
    }

    fun homeViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(provideRepository())
    }

    fun communityViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(provideRepository())
    }

    fun searchViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(provideRepository())
    }

}
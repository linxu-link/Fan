package com.link.fan.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.fan.app.login.LoginViewModel
import com.link.fan.app.main.community.CommunityViewModel
import com.link.fan.app.main.home.HomeViewModel
import com.link.fan.data.repository.AppRepository
import java.lang.RuntimeException

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-19:32
 * email:wujia0916@thundersoft.com
 * description:  生产viewModel的工厂
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val repository: AppRepository) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            return CommunityViewModel(repository) as T
        }
        throw RuntimeException("viewModel is null")
    }


}
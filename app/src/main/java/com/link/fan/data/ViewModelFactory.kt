package com.link.fan.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.fan.app.login.LoginViewModel

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-19:32
 * email:wujia0916@thundersoft.com
 * description:  生产viewModel的工厂
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }


}
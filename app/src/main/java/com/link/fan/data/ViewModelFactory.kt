package com.link.fan.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.fan.app.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }


}
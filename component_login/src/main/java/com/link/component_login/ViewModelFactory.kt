package com.link.component_login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_login.app.login.LoginViewModel
import com.link.component_login.data.Injection
import com.link.component_login.data.Repository

class ViewModelFactory private constructor(
    private val repository: Repository
) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).also {
                    instance = it
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
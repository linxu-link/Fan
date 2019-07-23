package com.link.component_login.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_login.app.login.LoginViewModel
import com.link.component_login.app.register.RegisterViewModel
import com.link.component_login.app.resetpwd.ResetPwdViewModel
import com.link.component_login.data.Injection
import com.link.component_login.data.LoginRepository

class ViewModelFactory private constructor(
        private val repository: LoginRepository
) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance
                            ?: ViewModelFactory(Injection.provideRepository()).also {
                        instance = it
                    }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ResetPwdViewModel::class.java)) {
            return ResetPwdViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
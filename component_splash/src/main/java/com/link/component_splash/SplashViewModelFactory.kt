package com.link.component_splash

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_splash.app.SplashViewModel
import com.link.component_splash.data.Injection
import com.link.component_splash.data.SplashRepository
import java.lang.IllegalArgumentException

class SplashViewModelFactory private constructor(
    private val application: Application,
    private val repository: SplashRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: SplashViewModelFactory? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this) {
                instance ?: SplashViewModelFactory(application, Injection.provideRepository()).also {
                    instance = it
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
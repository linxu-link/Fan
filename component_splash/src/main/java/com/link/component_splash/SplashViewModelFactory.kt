package com.link.component_splash

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_splash.app.SplashViewModel
import com.link.component_splash.data.SplashRepository

class SplashViewModelFactory private constructor(
    private val application: Application,
    private val repository: SplashRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    private object Inner {
        //对象表达式不能有构造器，可以使用初始化完成构造器的工作
       private var application: Application?=null
       private var repository: SplashRepository?=null

        fun init(application: Application, repoitory: SplashRepository) {
            this.application=application
            this.repository=repoitory
        }

        val factory = SplashViewModelFactory(application!!, repository!!)
    }

    companion object {
        val instance = Inner.factory
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(application, )
        }

        return super.create(modelClass)
    }


}
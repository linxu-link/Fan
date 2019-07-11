package com.link.component_main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_main.app.catalog.CatalogViewModel
import com.link.component_main.data.Injection
import com.link.component_main.data.MainRepository

class MainViewModelFactory private constructor(
    private val repository: MainRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(Injection.provideRepository()).also {
                    instance = it
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            return CatalogViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
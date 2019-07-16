package com.link.component_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_main.app.catalog.CatalogViewModel
import com.link.component_main.app.catalog.EmptyViewModel
import com.link.component_main.data.Injection
import com.link.component_main.data.MainRepository

class MainViewModelFactory private constructor(
        private val repository: MainRepository
) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        @JvmStatic
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
        } else if (modelClass.isAssignableFrom(EmptyViewModel::class.java)) {
            return EmptyViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
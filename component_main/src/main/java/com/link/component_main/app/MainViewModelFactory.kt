package com.link.component_main.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_main.EmptyViewModel
import com.link.component_main.app.catalog.detail.CatalogDetailViewModel
import com.link.component_main.app.main.recommend.RecommendViewModel
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
                    instance
                            ?: MainViewModelFactory(Injection.provideRepository()).also {
                        instance = it
                    }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogDetailViewModel::class.java)) {
            return CatalogDetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(EmptyViewModel::class.java)) {
            return EmptyViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(RecommendViewModel::class.java)){
            return RecommendViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
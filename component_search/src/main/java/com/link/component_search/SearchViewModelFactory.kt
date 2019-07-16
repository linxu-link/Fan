package com.link.component_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_search.app.search.EmptyViewModel
import com.link.component_search.data.Injection
import com.link.component_search.data.SearchRepository

class SearchViewModelFactory private constructor(
        private val repository: SearchRepository
) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: SearchViewModelFactory? = null

        @JvmStatic
        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: SearchViewModelFactory(Injection.provideRepository()).also {
                        instance = it
                    }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmptyViewModel::class.java)) {
            return EmptyViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
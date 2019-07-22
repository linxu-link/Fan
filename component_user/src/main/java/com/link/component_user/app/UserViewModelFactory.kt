package com.link.component_user.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_user.app.collection.CollectionViewModel
import com.link.component_user.app.footprint.FootPrintViewModel
import com.link.component_user.data.Injection
import com.link.component_user.data.UserRepository

class UserViewModelFactory private constructor(
        private val repository: UserRepository
) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: UserViewModelFactory? = null

        @JvmStatic
        fun getInstance() =
                instance ?: synchronized(this) {
                    instance
                            ?: UserViewModelFactory(Injection.provideRepository()).also {
                                instance = it
                            }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            return CollectionViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FootPrintViewModel::class.java)) {
            return FootPrintViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
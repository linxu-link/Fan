package com.link.component_user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_user.ui.collection.CollectionViewModel
import com.link.component_user.ui.footprint.FootPrintViewModel
import com.link.component_user.ui.personal.PersonalInfoViewModel
import com.link.component_user.ui.user.UserViewModel
import com.link.component_user.data.Injection
import com.link.component_user.data.UserRepository

class ViewModelFactory private constructor(
        private val repository: UserRepository
) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance() =
                instance ?: synchronized(this) {
                    instance
                            ?: ViewModelFactory(Injection.provideRepository()).also {
                                instance = it
                            }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)) {
            return CollectionViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FootPrintViewModel::class.java)) {
            return FootPrintViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(PersonalInfoViewModel::class.java)) {
            return PersonalInfoViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
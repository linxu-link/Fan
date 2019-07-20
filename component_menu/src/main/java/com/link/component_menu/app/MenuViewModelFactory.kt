package com.link.component_menu.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_menu.data.Injection
import com.link.component_menu.data.MenuRepository

class MenuViewModelFactory private constructor(
        private val repository: MenuRepository
) :
        ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MenuViewModelFactory? = null

        @JvmStatic
        fun getInstance() =
                instance ?: synchronized(this) {
                    instance
                            ?: MenuViewModelFactory(Injection.provideRepository()).also {
                                instance = it
                            }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
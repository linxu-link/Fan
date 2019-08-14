package com.link.component_shopping.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.link.component_shopping.data.Injection
import com.link.component_shopping.data.ShoppingRepository
import com.link.component_shopping.ui.shopping.ShoppingViewModel

class ViewModelFactory private constructor(
        private val repository: ShoppingRepository
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
        if (modelClass.isAssignableFrom(ShoppingViewModel::class.java)) {
            return ShoppingViewModel(repository) as T
        }

        throw RuntimeException("unknown mViewModel class:" + modelClass.name)
    }


}
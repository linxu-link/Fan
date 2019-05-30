package com.link.librarymodule.base.mvvm.factory

import androidx.lifecycle.ViewModel

interface IViewModelFactory {

    fun <T : ViewModel> create(modelClazz: Class<T>)

}
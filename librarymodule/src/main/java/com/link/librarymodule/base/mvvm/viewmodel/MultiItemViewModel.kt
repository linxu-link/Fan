package com.link.librarymodule.base.mvvm.viewmodel

open class MultiItemViewModel<VM : BaseViewModel<*>>(viewModel: VM) : ItemViewModel<VM>(viewModel) {

    private var itemType: Any? = null

    fun multiItemType(multiType: Any) {
        this.itemType = multiType
    }

}
package com.link.component_menu.app

import androidx.lifecycle.MutableLiveData
import com.link.component_menu.data.MenuRepository
import com.link.component_menu.data.entity.MenuDetail
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel

class MenuViewModel constructor(repository: MenuRepository) : BaseViewModel<MenuRepository>(repository) {

    val menuDetail = MutableLiveData<MenuDetail>()

    val burden = MutableLiveData<List<String>>()

    val ingredients = MutableLiveData<List<String>>()

}
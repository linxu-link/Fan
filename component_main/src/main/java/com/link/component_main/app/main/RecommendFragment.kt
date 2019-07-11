package com.link.component_main.app.main

import com.link.component_main.MainViewModelFactory
import com.link.component_main.R
import com.link.component_main.app.catalog.EmptyViewModel
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment

class RecommendFragment(override var mLayoutId: Int = R.layout.common_recycleview_refresh) : BaseMvvmFragment<EmptyViewModel>() {
    override fun initViewModel(): EmptyViewModel {
        return MainViewModelFactory.getInstance().create(EmptyViewModel::class.java)
    }

    override fun initView() {
        super.initView()


    }
}
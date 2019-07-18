package com.link.component_search

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity

@Route(path = RouterConstant.SEARCH)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_navigation_container)
    }
}

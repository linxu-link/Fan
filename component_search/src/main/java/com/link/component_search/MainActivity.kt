package com.link.component_search

import android.os.Bundle
import androidx.navigation.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.base.ContainerActivity

@Route(path = RouterConstant.SEARCH)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_navigation_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container).navigateUp()
    }
}

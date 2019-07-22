package com.link.component_login

import android.os.Bundle
import androidx.navigation.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity

@Route(path = RouterConstant.LOGIN)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_navigation_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container).navigateUp()
    }


}

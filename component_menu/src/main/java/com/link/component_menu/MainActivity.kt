package com.link.component_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.link.component_menu.app.MenuFragment
import com.link.component_menu.data.entity.MenuDetail
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.base.ContainerActivity
import com.link.librarymodule.constant.Constant

@Route(path = RouterConstant.MENU)
class MainActivity : ContainerActivity() {

    private lateinit var menuDetail: MenuDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        menuDetail = Gson().fromJson(intent.getStringExtra("MenuDetail"),MenuDetail::class.java)
        super.onCreate(savedInstanceState)
        fullScreen(this)
    }

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return MenuFragment.newInstance(menuDetail)
    }
}

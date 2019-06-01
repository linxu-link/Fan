package com.link.component_login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.component_login.app.login.LoginFragment
import com.link.component_login.app.register.RegisterFragment
import com.link.librarycomponent.router.RouterConstant
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

@Route(path = RouterConstant.LOGIN)
class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, LoginFragment.newInstance())
        fragmentTransaction.commit()
    }
}

package com.link.petshop

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.router.RouterConstant
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().build(RouterConstant.SPLASH).navigation()
        finish()
    }

}

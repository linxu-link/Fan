package com.link.component_splash.app

import android.os.Bundle
import com.link.component_splash.R
import com.link.component_splash.databinding.ActivitySplashBinding
import com.link.librarymodule.base.mvvm.view.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun setLayout(savedInstanceState: Bundle?): Int {
       return R.layout.activity_splash
    }

    override fun initVariableId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

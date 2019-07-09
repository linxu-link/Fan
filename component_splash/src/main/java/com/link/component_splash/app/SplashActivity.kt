package com.link.component_splash.app

import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.link.component_splash.R
import com.link.component_splash.SplashViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.mvvm.view.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author WJ
 * @date 2019-05-29
 *
 * 描述：闪屏页
 */
@Route(path = RouterConstant.SPLASH)
class SplashActivity(override var mLayoutId: Int = R.layout.activity_splash) : BaseMvvmActivity<SplashViewModel>() {


    override fun initViewModel(): SplashViewModel? {
        val factory = SplashViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
    }


    override fun initViewObservable() {
        super.initViewObservable()
        jump_button.setOnClickListener {
            ARouter.getInstance().build(RouterConstant.APP).navigation()
            finish()
        }
    }
}

package com.link.component_splash.app

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.link.component_splash.SplashViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.mvvm.view.BaseMvvmActivity
import com.link.librarymodule.utils.ToastUtils
import kotlinx.android.synthetic.main.splash_activity_splash.*
import java.util.*


/**
 * @author WJ
 * @date 2019-05-29
 *
 * 描述：闪屏页
 */
@Route(path = RouterConstant.SPLASH)
class SplashActivity(override var mLayoutId: Int = com.link.component_splash.R.layout.splash_activity_splash) : BaseMvvmActivity<SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        fullScreen(this)
        super.onCreate(savedInstanceState)

        splash.animate().setDuration(5500)
                .scaleX(1.6f)
                .scaleY(1.6f)
                .withEndAction {
                    ARouter.getInstance().build(RouterConstant.APP).navigation()
                    finish()
                }
                .start()


        tv_splash.rotationX = 90f
        tv_splash.alpha = 0f

        tv_splash.animate().setDuration(4000)
                .rotationX(0f)
                .alpha(0.8f)
                .start()
    }

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

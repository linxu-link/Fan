package com.link.component_splash.app

import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.link.component_splash.data.SplashRepository
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.router.SchemaFilterActivity
import com.link.librarymodule.base.mvvm.binding.command.BindingAction
import com.link.librarymodule.base.mvvm.binding.command.BindingCommand
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel

class SplashViewModel constructor(app:Application,model: SplashRepository) : BaseViewModel<SplashRepository>(app,model) {


    //登录按钮的点击事件
    var onClickCommand = BindingCommand<Void>(object : BindingAction {
        override fun call() {
            ARouter.getInstance().build(RouterConstant.LOGIN).navigation()
        }
    })


}
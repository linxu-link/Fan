package com.link.component_login

import android.app.Application
import cn.bmob.v3.Bmob
import com.link.librarymodule.BaseApplication
import com.link.librarymodule.constant.Constant

class LoginApplication : BaseApplication() {

    override fun initModuleApp(application: Application) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initModuleData(application: Application) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(this,Constant.BMOB_ID)
    }

}
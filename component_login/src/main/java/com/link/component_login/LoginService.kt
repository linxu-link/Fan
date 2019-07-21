package com.link.component_login

import cn.bmob.v3.BmobUser
import com.link.librarycomponent.service.login.ILoginService

class LoginService : ILoginService {

    init {
    }

    override fun isLogin(): Boolean {
        return BmobUser.isLogin()
    }


}
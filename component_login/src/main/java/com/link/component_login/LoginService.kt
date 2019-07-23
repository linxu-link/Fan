package com.link.component_login

import cn.bmob.v3.BmobUser
import com.link.librarycomponent.service.login.ILoginService

class LoginService : ILoginService {

    init {
    }

    //判断用户是否已经登录，将这段代码写在login组件中
    override fun isLogin(): Boolean {
        return BmobUser.isLogin()
    }


}
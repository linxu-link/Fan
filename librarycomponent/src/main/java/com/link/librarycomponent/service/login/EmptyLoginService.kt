package com.link.librarycomponent.service.login

class EmptyLoginService : ILoginService {
    override fun isLogin(): Boolean {
        return false
    }
}
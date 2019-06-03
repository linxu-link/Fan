package com.link.librarycomponent

import com.link.librarycomponent.service.user.EmptyUserService
import com.link.librarycomponent.service.user.IUserService

class ServiceFactory {

    var userService: IUserService? = null
        get() {
            if (field == null) {
                return EmptyUserService()
            }
            return field
        }

    companion object {
        @Volatile
        private var instance: ServiceFactory? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: ServiceFactory().also {
                        instance = it
                    }
                }
    }


}
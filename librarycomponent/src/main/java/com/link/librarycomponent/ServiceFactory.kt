package com.link.librarycomponent

import com.link.librarycomponent.service.main.EmptyMainService
import com.link.librarycomponent.service.main.IMainService
import com.link.librarycomponent.service.update.IUpdateService
import com.link.librarycomponent.service.user.EmptyUserService
import com.link.librarycomponent.service.user.IUserService

class ServiceFactory {

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

    var userService: IUserService? = null
        get() {
            if (field == null) {
                return EmptyUserService()
            }
            return field
        }


    var mainService: IMainService? = null
        get() {
            if (field == null) {
                return EmptyMainService()
            }
            return field
        }

    var updateService: IUpdateService? = null


}
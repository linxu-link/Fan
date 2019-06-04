package com.link.librarycomponent

import com.link.librarycomponent.service.catalog.EmptyCatalogService
import com.link.librarycomponent.service.catalog.ICatalogService
import com.link.librarycomponent.service.find.EmptyFindService
import com.link.librarycomponent.service.find.IFindService
import com.link.librarycomponent.service.main.EmptyMainService
import com.link.librarycomponent.service.main.IMainService
import com.link.librarycomponent.service.shoping_cart.EmptyShoppingCartService
import com.link.librarycomponent.service.shoping_cart.IShoppingCartService
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

    var shopService: IShoppingCartService? = null
        get() {
            if (field == null) {
                return EmptyShoppingCartService()
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

    var findService: IFindService? = null
        get() {
            if (field == null) {
                return EmptyFindService()
            }
            return field
        }

    var catalogService: ICatalogService? = null
        get() {
            if (field == null) {
                return EmptyCatalogService()
            }
            return field
        }


}
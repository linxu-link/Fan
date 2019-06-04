package com.link.librarycomponent.service.shoping_cart

import android.os.Bundle
import androidx.fragment.app.Fragment

class EmptyShoppingCartService : IShoppingCartService {

    override fun newShoppingCartFragment(bundle: Bundle?): Fragment? {
        return null
    }

}
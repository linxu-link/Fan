package com.link.component_shopping_cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_shopping_cart.app.ShoppingCartFragment
import com.link.librarycomponent.service.shoping_cart.IShoppingCartService

class ShoppingCartService : IShoppingCartService {
    override fun newShoppingCartFragment(bundle: Bundle?): Fragment? {
        return ShoppingCartFragment.newInstance()
    }
}
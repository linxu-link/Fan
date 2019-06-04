package com.link.component_shopping_cart

import android.content.Intent
import androidx.fragment.app.Fragment
import com.link.component_shopping_cart.app.ShoppingCartFragment
import com.link.librarymodule.base.ContainerActivity

class MainActivity : ContainerActivity() {

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return ShoppingCartFragment.newInstance()
    }

}
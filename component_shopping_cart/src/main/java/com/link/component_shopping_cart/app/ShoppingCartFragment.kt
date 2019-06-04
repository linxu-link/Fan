package com.link.component_shopping_cart.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_shopping_cart.R

class ShoppingCartFragment:Fragment() {

    companion object {
            @JvmStatic
            fun newInstance() =
                ShoppingCartFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.shop_fragment_shopping_cart,container,false)
    }

}
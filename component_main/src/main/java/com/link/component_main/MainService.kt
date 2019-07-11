package com.link.component_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_main.app.catalog.CatalogFragment
import com.link.component_main.app.FindFragment
import com.link.component_main.app.MainFragment
import com.link.component_main.app.ShoppingCartFragment
import com.link.librarycomponent.service.main.IMainService

class MainService : IMainService {
    override fun newShoppingFragment(bundle: Bundle?): Fragment? {
        return ShoppingCartFragment.newInstance()
    }

    override fun newCatalogFragment(bundle: Bundle?): Fragment? {
        return CatalogFragment.newInstance()
    }

    override fun newFindFragment(bundle: Bundle?): Fragment? {
        return FindFragment.newInstance()
    }

    override fun newMainFragment(bundle: Bundle?): Fragment? {
        return MainFragment.newInstance()
    }
}
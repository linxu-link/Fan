package com.link.component_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_main.app.find.FindFragment
import com.link.component_main.app.main.MainFragment
import com.link.component_main.app.catalog.CatalogFragment
import com.link.librarycomponent.service.main.IMainService

class MainService : IMainService {

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
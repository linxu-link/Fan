package com.link.component_catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_catalog.app.CatalogFragment
import com.link.librarycomponent.service.catalog.ICatalogService

class CatalogService : ICatalogService {
    override fun newCatalogFragment(bundle: Bundle?): Fragment? {
        return CatalogFragment.newInstance()
    }
}
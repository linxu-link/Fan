package com.link.librarycomponent.service.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment

class EmptyCatalogService : ICatalogService {
    override fun newCatalogFragment(bundle: Bundle?): Fragment? {
        return null
    }
}
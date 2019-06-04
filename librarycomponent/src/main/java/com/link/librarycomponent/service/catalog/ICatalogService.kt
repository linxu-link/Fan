package com.link.librarycomponent.service.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment

interface ICatalogService {

    fun newCatalogFragment(bundle: Bundle?): Fragment?

}
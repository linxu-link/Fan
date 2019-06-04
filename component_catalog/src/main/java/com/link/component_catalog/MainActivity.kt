package com.link.component_catalog

import android.content.Intent
import androidx.fragment.app.Fragment
import com.link.component_catalog.app.CatalogFragment
import com.link.librarymodule.base.ContainerActivity

class MainActivity:ContainerActivity() {

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return CatalogFragment.newInstance()
    }

}
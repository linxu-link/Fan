package com.link.component_catalog.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_catalog.R
import com.link.librarymodule.widgets.navgation.CircleView


class CatalogFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                CatalogFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.catalog_fragment_catlog, container, false)
//        val bar=view.findViewById<CircleView>(R.id.bottom)

        return view
    }
}
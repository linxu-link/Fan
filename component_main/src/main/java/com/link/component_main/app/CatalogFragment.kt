package com.link.component_main.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_main.R


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
        val view=inflater.inflate(R.layout.main_fragment_catlog, container, false)
        return view
    }
}
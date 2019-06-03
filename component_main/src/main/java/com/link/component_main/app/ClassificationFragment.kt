package com.link.component_main.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_main.R

class ClassificationFragment : Fragment() {

    companion object {
            @JvmStatic
            fun newInstance() =
                ClassificationFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find,container,false)
    }
}
package com.link.fan.app.mall


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.link.fan.R

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class MallFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mall, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                MallFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}

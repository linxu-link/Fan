package com.link.component_user.app.collection


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController

import com.link.component_user.R
import com.link.librarymodule.base.BaseFragment


class CollectionFragment(override var layoutId: Int = R.layout.user_fragment_collection) : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                CollectionFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = mRootView!!.findViewById<TextView>(R.id.title)
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        title.text = "我的收藏"
        back.setOnClickListener {
            findNavController().navigateUp()
        }


    }

}

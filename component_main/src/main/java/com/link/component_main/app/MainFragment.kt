package com.link.component_main.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
import kotlinx.android.synthetic.main.main_fragment_main.*

class MainFragment : Fragment() {

    companion object {

        const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance() =
                MainFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf<String>()
        for (index in 0 until 20) {
            list.add("ele:$index")
        }

        rv_list.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.main_item_main_layout, list) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.title, item!!)
            }

        }
    }
}
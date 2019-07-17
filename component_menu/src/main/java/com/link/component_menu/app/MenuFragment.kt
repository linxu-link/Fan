package com.link.component_menu.app


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.link.component_menu.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.widgets.recyclerview.DividerItemDecoration
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.menu_fragment_menu.*

class MenuFragment(override var layoutId: Int = R.layout.menu_fragment_menu) : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                MenuFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    private lateinit var mAdapter: MenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf<String>()
        for (index in 0 until 5) {
            list.add("ele:$index")
        }
        mAdapter = MenuAdapter(R.layout.menu_item, list)
        rvList.addItemDecoration(ItemDecoration(0,5,5,0))
        rvList.adapter = mAdapter
        initHeaderView()
    }

    private fun initHeaderView() {

        val headerView = LayoutInflater.from(context).inflate(R.layout.menu_item_header, null)
        mAdapter.addHeaderView(headerView)

    }

}

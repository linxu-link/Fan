package com.link.component_search.app.detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.link.component_search.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.search_fragment_search.*

/**
 * @author WJ
 * @date 2019-07-16
 *
 * 描述：
 */
class SearchDetailFragment(override var layoutId: Int = R.layout.search_fragment_search) : BaseFragment() {

    private lateinit var mAdapter: SearchDetailAdapter


    companion object {
        @JvmStatic
        fun newInstance(id: String?) =
                SearchDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(id, Constant.ID)
                    }
                }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf<String>()
        for (index in 0 until 10) {
            list.add("ele:$index")
        }

        mAdapter = SearchDetailAdapter(R.layout.search_item_detail, list)
        rvList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvList.addItemDecoration(ItemDecoration(5, 5, 5, 5))
        rvList.adapter = mAdapter

    }

}
package com.link.component_search.app.search

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.link.component_search.R
import com.link.component_search.SearchViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.recyclerview.DividerItemDecoration
import kotlinx.android.synthetic.main.search_fragment_search.*
/**
 * @author WJ
 * @date 2019-07-16
 *
 * 描述：
 */
class SearchFragment(override var layoutId: Int = R.layout.search_fragment_search) : BaseMvvmFragment<EmptyViewModel>() {

    override fun initViewModel(): EmptyViewModel {
        return SearchViewModelFactory.getInstance().create(EmptyViewModel::class.java)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                SearchFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    private lateinit var mAdapter: SearchAdapter

    override fun initView() {
        super.initView()

        val list = arrayListOf<String>()
        for (index in 0 until 5) {
            list.add("ele:$index")
        }


        mAdapter = SearchAdapter(R.layout.search_item, list)

        rvList.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        rvList.adapter = mAdapter

        initHeader()

    }

    fun initHeader(){

        val header=LayoutInflater.from(context).inflate(R.layout.search_item_head,null)

        mAdapter.addHeaderView(header)

    }

    override fun initViewObservable() {
        super.initViewObservable()
    }

}
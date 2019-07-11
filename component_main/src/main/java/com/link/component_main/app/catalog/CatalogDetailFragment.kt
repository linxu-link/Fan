package com.link.component_main.app.catalog

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.link.component_main.MainViewModelFactory
import com.link.component_main.R
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.ItemDecoration
import kotlinx.android.synthetic.main.main_fragment_catalog_detail.*


class CatalogDetailFragment(override var mLayoutId: Int = R.layout.main_fragment_catalog_detail) :
        BaseMvvmFragment<CatalogViewModel>() {

    override fun initViewModel(): CatalogViewModel {
        return MainViewModelFactory.getInstance().create(CatalogViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                CatalogDetailFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    private lateinit var mLeftAdapter: LeftCatalogAdapter
    private lateinit var mRightAdapter: RightCatalogAdapter

    override fun initView() {
        super.initView()
        mLeftAdapter = LeftCatalogAdapter(R.layout.main_item_catalog_left, null)
        mRightAdapter = RightCatalogAdapter(R.layout.main_item_catalog_right, null)

        rv_catalog.adapter = mLeftAdapter
        rv_catalog_right.adapter = mRightAdapter
        rv_catalog_right.layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        rv_catalog_right.addItemDecoration(ItemDecoration(3, 3, 3, 3))

        mLeftAdapter.setOnItemClickListener { adapter, view, position ->
            mRightAdapter.setNewData(mViewModel.cataLog.value!![position].list)
            for (result in mLeftAdapter.data) {
                result.click = false
            }
            mLeftAdapter.data[position].click = true
            mLeftAdapter.notifyDataSetChanged()

        }

        mViewModel.cataLog.observe(this, Observer {
            mLeftAdapter.setNewData(it)
            mRightAdapter.setNewData(mViewModel.cataLog.value!![0].list)
        })

    }

    override fun initData() {
        super.initData()
        mViewModel.getCatalogData()
    }

}
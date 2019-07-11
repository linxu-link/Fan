package com.link.component_main.app.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.MainViewModelFactory
import com.link.component_main.R
import com.link.component_main.data.entity.CategoryResult
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import kotlinx.android.synthetic.main.main_fragment_catlog.*


class CatalogFragment(override var mLayoutId: Int = R.layout.main_fragment_catlog) :
        BaseMvvmFragment<CatalogViewModel>() {

    override fun initViewModel(): CatalogViewModel {
        return MainViewModelFactory.getInstance().create(CatalogViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                CatalogFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    private lateinit var mLeftAdapter: CatalogAdapter

    override fun initView() {
        super.initView()
        mLeftAdapter=CatalogAdapter(R.layout.main_item_catalog_left,null)
    }

    override fun initData() {
        super.initData()
        mViewModel.getCatalogData()
    }

    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel.cataLog.observe(this, Observer {
            mLeftAdapter.setNewData(it)
        })
    }


}
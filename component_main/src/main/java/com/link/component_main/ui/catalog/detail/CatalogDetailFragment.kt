package com.link.component_main.ui.catalog.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.link.component_main.ui.MainViewModelFactory
import com.link.component_main.R
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.main_fragment_catalog_detail.*

const val INDEX = "index"

class CatalogDetailFragment(override var layoutId: Int = R.layout.main_fragment_catalog_detail) :
        BaseMvvmFragment<CatalogDetailViewModel>() {

    override fun getViewModel(): CatalogDetailViewModel {
        return MainViewModelFactory.getInstance().create(CatalogDetailViewModel::class.java)
    }


    private var index: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(index: Int) =
                CatalogDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(INDEX, index)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            index = arguments!!.getInt(INDEX)
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


        if (index == 0) {

            mLeftAdapter.setOnItemClickListener { _, _, position ->
                mRightAdapter.setNewData(mViewModel.cataLog.value!![position].list)
                for (result in mLeftAdapter.data) {
                    result.click = false
                }
                mLeftAdapter.data[position].click = true
                mLeftAdapter.notifyDataSetChanged()

            }
        } else {
            mLeftAdapter.setOnItemClickListener { _, _, position ->
                mRightAdapter.setNewData(mViewModel.ingredients.value!![position].list)
                for (result in mLeftAdapter.data) {
                    result.click = false
                }
                mLeftAdapter.data[position].click = true
                mLeftAdapter.notifyDataSetChanged()

            }
        }

        mRightAdapter.setOnItemClickListener { _, _, position ->
            if (mRightAdapter.getItem(position)!=null) {
                val name = mRightAdapter.getItem(position)!!.name
                val id = mRightAdapter.getItem(position)!!.id

                ARouter.getInstance()
                        .build(RouterConstant.SEARCH_DETAIL)
                        .withString(Constant.ID,id)
                        .withString(Constant.NAME,name)
                        .navigation()
            }
        }

    }

    override fun initViewObservable() {
        super.initViewObservable()
        if (index==0){
            mViewModel.cataLog.observe(this, Observer {
                mLeftAdapter.setNewData(it)
                mRightAdapter.setNewData(mViewModel.cataLog.value!![0].list)
            })
        }else{
            mViewModel.ingredients.observe(this, Observer {
                mLeftAdapter.setNewData(it)
                mRightAdapter.setNewData(mViewModel.ingredients.value!![0].list)
            })
        }
    }

    override fun getData() {
        super.getData()
        if (index == 0) {
            mViewModel.getCatalogData()
        } else {
            mViewModel.getIngredients()
        }
    }

}
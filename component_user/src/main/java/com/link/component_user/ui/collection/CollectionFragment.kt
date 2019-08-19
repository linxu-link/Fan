package com.link.component_user.ui.collection


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.link.component_user.R
import com.link.component_user.ui.ViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.user_fragment_collection.*

/**
 * @author WJ
 * @date 2019-07-21
 *
 * 描述：我的收藏
 */
class CollectionFragment(override var layoutId: Int = R.layout.user_fragment_collection)
    : BaseMvvmFragment<CollectionViewModel>() {

    override fun getViewModel(): CollectionViewModel {
        return ViewModelFactory.getInstance().create(CollectionViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                CollectionFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    private lateinit var mAdapter: CollectionAdapter

    override fun initView() {
        super.initView()
        refresh.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary))
        refresh.setOnRefreshListener {
            loadData()
        }
        val title = mRootView!!.findViewById<TextView>(R.id.title)
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        title.text = "我的收藏"
        back.setOnClickListener {
            mActivity!!.onBackPressed()
        }

        mAdapter = CollectionAdapter(R.layout.user_item_collection, null)
        rv_list.adapter = mAdapter
        rv_list.addItemDecoration(ItemDecoration(0, 10, 0, 10))
    }

    override fun initViewObservable() {
        super.initViewObservable()
        mViewModel.mCollectionData.observe(this, Observer {
            mAdapter.setNewData(it)
            refresh.isRefreshing = false
        })
    }

    override fun loadData() {
        super.loadData()
        refresh.isRefreshing = true
        mViewModel.getCollectionData()
    }

}

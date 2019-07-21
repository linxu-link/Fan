package com.link.component_user.app.collection


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.link.component_user.R
import com.link.component_user.app.UserViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import kotlinx.android.synthetic.main.user_fragment_collection.*

/**
 * @author WJ
 * @date 2019-07-21
 *
 * 描述：我的收藏
 */
class CollectionFragment(override var layoutId: Int = R.layout.user_fragment_collection)
    : BaseMvvmFragment<CollectionViewModel>() {

    override fun initViewModel(): CollectionViewModel {
        return UserViewModelFactory.getInstance().create(CollectionViewModel::class.java)
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
            getData()
        }
        val title = mRootView!!.findViewById<TextView>(R.id.title)
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        title.text = "我的收藏"
        back.setOnClickListener {
            findNavController().navigateUp()
        }
        mAdapter=CollectionAdapter(R.layout.user_item_collection,null)
        rv_list.adapter
        rv_list.layoutManager=LinearLayoutManager(context,RecyclerView.VERTICAL,false)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        mViewModel.mCollectionData.observe(this, Observer {
            mAdapter.setNewData(it)
        })
    }

    override fun getData() {
        super.getData()
        refresh.isRefreshing = true
        mViewModel.getCollectionData()

    }

}

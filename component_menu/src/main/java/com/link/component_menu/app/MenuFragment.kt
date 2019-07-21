package com.link.component_menu.app


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.link.component_menu.R
import com.link.component_menu.data.entity.MenuDetail
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.menu_fragment_menu.*

class MenuFragment(override var layoutId: Int = R.layout.menu_fragment_menu) : BaseMvvmFragment<MenuViewModel>() {
    override fun initViewModel(): MenuViewModel {
        return MenuViewModelFactory.getInstance().create(MenuViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance(menuDetail: MenuDetail) =
                MenuFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(MenuDetail::class.java.canonicalName, menuDetail)
                    }
                }
    }

    override fun initData() {
        super.initData()
        if (arguments != null) {
            mViewModel.menuDetail.value = arguments!!.getParcelable(MenuDetail::class.java.canonicalName)
        }
    }

    private lateinit var mAdapter: MenuAdapter
    private lateinit var mHeader1Adapter: HeaderAdapter
    private lateinit var mHeader2Adapter: HeaderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = MenuAdapter(R.layout.menu_item, null)
        rvList.addItemDecoration(ItemDecoration(0, 5, 5, 0))
        rvList.adapter = mAdapter
        initHeaderView()

        mViewModel.menuDetail.observe(this, Observer {
            mAdapter.setNewData(it.steps)
            Glide.with(context!!).load(it.albums[0]).into(cover)
            toolbar.title = it.title
        })




    }

    private fun initHeaderView() {
        mHeader1Adapter=HeaderAdapter(android.R.layout.simple_list_item_activated_1,null)
        mHeader2Adapter=HeaderAdapter(android.R.layout.simple_list_item_activated_1,null)

        val headerView = LayoutInflater.from(context).inflate(R.layout.menu_item_header, null)

        val name = headerView.findViewById<TextView>(R.id.name)
        val content = headerView.findViewById<TextView>(R.id.content)

        val ingredients=headerView.findViewById<RecyclerView>(R.id.ingredients)
        ingredients.adapter=mHeader1Adapter

        val burden=headerView.findViewById<RecyclerView>(R.id.burden)
        burden.adapter=mHeader2Adapter

        mViewModel.menuDetail.observe(this, Observer {
            name.text = it.title
            content.text = it.imtro
            mViewModel.ingredients.value = it.ingredients.split(";")
            mViewModel.burden.value = it.burden.split(";")
        })



        mViewModel.ingredients.observe(this, Observer {

            mHeader1Adapter.setNewData(it)

        })

        mViewModel.burden.observe(this, Observer {

            mHeader2Adapter.setNewData(it)
        })

        mAdapter.addHeaderView(headerView)


    }


}

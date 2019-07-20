package com.link.component_menu.app


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mViewModel.menuDetail.value = arguments!!.getParcelable(MenuDetail::class.java.canonicalName)
        }
    }

    private lateinit var mAdapter: MenuAdapter

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
        val headerView = LayoutInflater.from(context).inflate(R.layout.menu_item_header, null)

        val name = headerView.findViewById<TextView>(R.id.name)
        val content = headerView.findViewById<TextView>(R.id.content)

        mViewModel.menuDetail.observe(this, Observer {
            name.text = it.title
            content.text = it.imtro
            mViewModel.ingredients.value = it.ingredients.split(";")
            mViewModel.burden.value = it.burden.split(";")
        })



        mViewModel.burden.observe(this, Observer {


        })

        mViewModel.ingredients.observe(this, Observer {


        })

        mAdapter.addHeaderView(headerView)


    }


}

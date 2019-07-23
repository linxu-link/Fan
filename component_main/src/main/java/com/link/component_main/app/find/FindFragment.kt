package com.link.component_main.app.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.link.component_main.app.MainViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.libraryflipview.flip.FlipViewController
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment

class FindFragment(override var layoutId: Int = 0) : BaseMvvmFragment<FindViewModel>() {


    private lateinit var flipView: FlipViewController
    private lateinit var mAdapter: FindAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
                FindFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        flipView = FlipViewController(context)
        mAdapter = FindAdapter(context!!)
        flipView.adapter = mAdapter
        return flipView
    }


    override fun initView() {
        super.initView()
        mAdapter.onItemClickListener = object : FindAdapter.onBtnClickListener {
            override fun onItemClick(position: Int) {
                ARouter.getInstance()
                        .build(RouterConstant.MENU)
                        .withString("MenuDetail", Gson().toJson(mAdapter.getItem(position)))
                        .navigation()
            }

        }

    }

    override fun getData() {
        super.getData()
        mViewModel.getData()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        mViewModel.data.observe(this, Observer {
            mAdapter.setData(it)
        })
    }

    override fun getViewModel(): FindViewModel {
        return MainViewModelFactory.getInstance().create(FindViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        flipView.onResume()
    }

    override fun onPause() {
        super.onPause()
        flipView.onPause()
    }

}
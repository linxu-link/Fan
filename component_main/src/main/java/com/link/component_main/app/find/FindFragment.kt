package com.link.component_main.app.find

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.link.component_main.app.MainViewModelFactory
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
        mViewModel.data.observe(this, Observer {
            mAdapter.setData(it)
        })

    }

    override fun initData() {
        super.initData()
        mViewModel.getData()
    }

    override fun initViewModel(): FindViewModel {
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
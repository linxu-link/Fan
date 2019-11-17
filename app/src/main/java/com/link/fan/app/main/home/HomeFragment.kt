package com.link.fan.app.main.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentHomeBinding
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-11:03
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    private val viewModel: HomeViewModel by viewModels {
        InjectorUtils.homeViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = viewLifecycleOwner

            refresh.setOnRefreshListener {
                this@HomeFragment.viewModel.requestData()
            }
        }

        if (CommonUtil.getStatusBarHeight() >= 0) {
            val layout = FrameLayout.LayoutParams(binding.root.layoutParams)
            layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
            binding.root.layoutParams = layout
        }

        val adapter = HomeAdapters(R.layout.list_item_home, null)
        addHeaderView(adapter, binding)
        binding.menuList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun addHeaderView(adapter: HomeAdapters, binding: FragmentHomeBinding) {
        val headerView = layoutInflater.inflate(R.layout.layout_home_recommond_head, binding.root as ViewGroup, false)

        val headerView2=layoutInflater.inflate(R.layout.layout_home_header,binding.root as ViewGroup,false)

        adapter.addHeaderView(headerView2)
        adapter.addHeaderView(headerView)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestData()
    }

    private fun subscribeUi(adapter: HomeAdapters) {
        viewModel.menuResult.observe(viewLifecycleOwner) { menuResult ->
            menuResult?.run {
                adapter.setNewData(data)
            }
            refresh.isRefreshing = false
        }
    }


}



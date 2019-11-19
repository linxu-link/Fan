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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.R
import com.link.fan.adapters.CardPagerAdapter
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentHomeBinding
import com.link.fan.databinding.LayoutHomeHeaderBinding
import com.link.fan.databinding.LayoutHomeRecommendHeadBinding
import com.link.fan.widgets.card.PanCardTransformer
import com.link.fan.widgets.card.ShadowTransformer
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_home_header.*

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

        val adapter = HomeAdapter(R.layout.list_item_home, null)
        addHeaderView(adapter, binding)
        binding.menuList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private var transformer: ShadowTransformer? = null

    private fun addHeaderView(adapter: HomeAdapter, binding: FragmentHomeBinding) {
        val headBinding = DataBindingUtil.inflate<LayoutHomeHeaderBinding>(LayoutInflater.from(requireContext()),
                R.layout.layout_home_header,
                binding.root as ViewGroup, false)
        val pageAdapter = CardPagerAdapter()
        headBinding.viewPager.adapter = pageAdapter

        headBinding.viewPager.setPageTransformer(false, transformer)
        adapter.addHeaderView(headBinding.root)

        transformer = ShadowTransformer(headBinding.viewPager, pageAdapter)

        val recommendHead = DataBindingUtil.inflate<LayoutHomeRecommendHeadBinding>(
                LayoutInflater.from(requireContext()),
                R.layout.layout_home_recommend_head,
                binding.root as ViewGroup, false)
        val recommendAdapter = RecommendAdapter()
        val lastAdapter = RecommendAdapter()
        recommendHead.recommendList.adapter = recommendAdapter
        recommendHead.recommendList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recommendHead.lastList.adapter = lastAdapter
        recommendHead.lastList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        subscribeUi(recommendAdapter, lastAdapter, pageAdapter)
        adapter.addHeaderView(recommendHead.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestData()
        refresh.isRefreshing = true
    }

    private fun subscribeUi(adapter: HomeAdapter) {
        viewModel.masterLiveData.observe(viewLifecycleOwner) { menuResult ->
            menuResult?.run {
                adapter.setNewData(data)
            }
            refresh.isRefreshing = false
        }
    }

    private fun subscribeUi(recommendAdapter: RecommendAdapter,
                            lastAdapter: RecommendAdapter,
                            pageAdapter: CardPagerAdapter) {
        viewModel.lastMenuLiveData.observe(viewLifecycleOwner) { menuResult ->
            menuResult?.run {
                lastAdapter.submitList(data)
            }
            refresh.isRefreshing = false
        }
        viewModel.recommendLiveData.observe(viewLifecycleOwner) { menuResult ->
            menuResult?.run {
                recommendAdapter.submitList(data)
            }
            refresh.isRefreshing = false
        }

        viewModel.bannerLiveData.observe(viewLifecycleOwner) { menuResult ->
            menuResult?.run {
                for (menu in data) {
                    pageAdapter.setDataList(data)
                    viewPager.currentItem = 1
                    viewPager.offscreenPageLimit = data.size
                    transformer?.enableScaling(true)
                }

            }
        }


    }


}



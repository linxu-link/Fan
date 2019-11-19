package com.link.fan.app.main.community


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentCommunityBinding
import com.link.librarymodule.base.adapter.FixPagerAdapter
import java.util.ArrayList

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class CommunityFragment : Fragment() {

    private val viewModel: CommunityViewModel by viewModels {
        InjectorUtils.communityViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentCommunityBinding>(LayoutInflater.from(requireContext()),
                R.layout.fragment_community, container, false).apply {
            viewModel = this@CommunityFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        val titles = arrayOf("最新活动", "食物杂谈")
        val fragmentList = ArrayList<Fragment>(2)
        fragmentList.add(CommunityListFragment.newInstance(7))
        fragmentList.add(CommunityListFragment.newInstance(12))
        val bottomPagerAdapter = FixPagerAdapter(childFragmentManager)
        bottomPagerAdapter.setFragments(fragmentList)
        bottomPagerAdapter.setTitles(titles)
        binding.bottomViewPager.adapter = bottomPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.bottomViewPager)
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                CommunityFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}

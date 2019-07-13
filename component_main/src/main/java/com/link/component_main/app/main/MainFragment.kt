package com.link.component_main.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_main.R
import com.link.component_main.app.main.recommend.RecommendFragment
import com.link.librarymodule.base.adapter.FixPagerAdapter
import kotlinx.android.synthetic.main.main_fragment_main.tabLayout
import kotlinx.android.synthetic.main.main_fragment_main.viewPager

class MainFragment : Fragment() {

    companion object {

        const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance() =
                MainFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mFragmentList = arrayListOf<Fragment>()
        val titles = arrayOf("今日推荐", "早餐达人", "健身增肌","休闲下午茶","美味午餐")
        mFragmentList.add(RecommendFragment.newInstance())
        mFragmentList.add(RecommendFragment.newInstance())
        mFragmentList.add(RecommendFragment.newInstance())
        mFragmentList.add(RecommendFragment.newInstance())
        mFragmentList.add(RecommendFragment.newInstance())
        val mPagerAdapter = FixPagerAdapter(childFragmentManager)
        mPagerAdapter.setFragments(mFragmentList)
        mPagerAdapter.setTitles(titles)
        viewPager.adapter = mPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
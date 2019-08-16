package com.link.component_main.ui.main

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.link.component_main.R
import com.link.component_main.ui.main.recommend.RecommendFragment
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.router.StartRouter
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.base.adapter.FixPagerAdapter
import kotlinx.android.synthetic.main.main_fragment_main.*

class MainFragment(override var layoutId: Int = R.layout.main_fragment_main) : BaseFragment() {

    companion object {

        @JvmStatic
        fun newInstance() =
                MainFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mFragmentList = arrayListOf<Fragment>()
        val titles = arrayOf("夏日推荐", "早餐达人", "丰盛午餐", "美味晚餐", "创意甜点")
        mFragmentList.add(RecommendFragment.newInstance(0))
        mFragmentList.add(RecommendFragment.newInstance(1))
        mFragmentList.add(RecommendFragment.newInstance(2))
        mFragmentList.add(RecommendFragment.newInstance(3))
        mFragmentList.add(RecommendFragment.newInstance(4))
        val mPagerAdapter = FixPagerAdapter(childFragmentManager)
        mPagerAdapter.setFragments(mFragmentList)
        mPagerAdapter.setTitles(titles)
        viewPager.adapter = mPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        val searchBar=mRootView!!.findViewById<LinearLayout>(R.id.search_bar)
        searchBar.setOnClickListener {
            StartRouter.navigation(RouterConstant.SEARCH)
        }


    }

}
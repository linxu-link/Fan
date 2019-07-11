package com.link.component_main.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
import com.link.component_main.app.catalog.CatalogDetailFragment
import com.link.librarymodule.base.adapter.FixPagerAdapter
import kotlinx.android.synthetic.main.main_fragment_catalog.*
import kotlinx.android.synthetic.main.main_fragment_main.*
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
        val titles = arrayOf("推荐", "早餐", "健身餐")
        mFragmentList.add(RecommendFragment.newInstance())
        mFragmentList.add(RecommendFragment.newInstance())
        val mPagerAdapter = FixPagerAdapter(childFragmentManager)
        mPagerAdapter.setFragments(mFragmentList)
        mPagerAdapter.setTitles(titles)
        viewPager.adapter = mPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
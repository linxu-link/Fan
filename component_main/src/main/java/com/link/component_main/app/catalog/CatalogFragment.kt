package com.link.component_main.app.catalog

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.link.component_main.R
import com.link.component_main.app.catalog.detail.CatalogDetailFragment
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.router.StartRouter
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.base.adapter.FixPagerAdapter
import kotlinx.android.synthetic.main.main_fragment_catalog.*

class CatalogFragment(override var layoutId: Int = R.layout.main_fragment_catalog) : BaseFragment() {


    companion object {
            @JvmStatic
            fun newInstance() =
                CatalogFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mFragmentList = arrayListOf<Fragment>()
        val titles = arrayOf("分类", "食材")
        mFragmentList.add(CatalogDetailFragment.newInstance(0))
        mFragmentList.add(CatalogDetailFragment.newInstance(1))
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
package com.link.component_main.app.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_main.MainViewModelFactory
import com.link.component_main.R
import com.link.librarymodule.base.adapter.FixPagerAdapter
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import kotlinx.android.synthetic.main.main_fragment_catalog.*

class CatalogFragment(override var layoutId: Int = R.layout.main_fragment_catalog) : BaseMvvmFragment<EmptyViewModel>() {


    companion object {
            @JvmStatic
            fun newInstance() =
                CatalogFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }

    override fun initViewModel(): EmptyViewModel {
        return MainViewModelFactory.getInstance().create(EmptyViewModel::class.java)
    }


    override fun initView() {
        super.initView()
        val mFragmentList = arrayListOf<Fragment>()
        val titles = arrayOf("分类", "食材")
        mFragmentList.add(CatalogDetailFragment.newInstance())
        mFragmentList.add(CatalogDetailFragment.newInstance())
        val mPagerAdapter = FixPagerAdapter(childFragmentManager)
        mPagerAdapter.setFragments(mFragmentList)
        mPagerAdapter.setTitles(titles)
        viewPager.adapter = mPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

}
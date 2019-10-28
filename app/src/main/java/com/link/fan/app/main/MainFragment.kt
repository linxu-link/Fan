package com.link.fan.app.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.link.fan.R
import com.link.fan.app.main.community.CommunityFragment
import com.link.fan.app.main.home.HomeFragment
import com.link.fan.app.main.mall.MallFragment
import com.link.fan.app.main.mine.MineFragment
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.widgets.navgation.BottomNavigationBar
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
class MainFragment(override var layoutId: Int = R.layout.fragment_main) : BaseFragment(), BottomNavigationBar.OnClickListener {

    private var mFragmentList = arrayListOf<Fragment>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigation_bar.setOnItemClickListener(this)
        mFragmentList.add(HomeFragment.newInstance())
        mFragmentList.add(CommunityFragment.newInstance())
        mFragmentList.add(MallFragment.newInstance())
        mFragmentList.add(MineFragment.newInstance())

        val transaction = childFragmentManager.beginTransaction()
        mCurrent = mFragmentList[0]

        transaction.replace(R.id.content, mCurrent!!)
        transaction.commit()

    }

    private var mCurrent: Fragment? = null

    override fun onBottomItemClickListener(checkedId: Int) {
        val transaction = childFragmentManager.beginTransaction()
        if (checkedId == R.id.home) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[0]

        } else if (checkedId == R.id.community) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[1]
        } else if (checkedId == R.id.mall) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[2]
        } else if (checkedId == R.id.mine) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[3]
        }else if (checkedId==R.id.add_menu){
            ToastUtils.showLong("add")
        }

        if (mCurrent!!.isAdded) {
            transaction.show(mCurrent!!)
        } else {
            transaction.add(R.id.content, mCurrent!!)
        }
        transaction.commit()
    }


}
package com.link.fan.app.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.widgets.navgation.BottomNavigationBar
import com.link.fan.R
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.service.update.IUpdateService
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment(override var layoutId: Int= R.layout.fragment_main)
    : BaseFragment(), BottomNavigationBar.OnClickListener {

    private var mFragmentList = arrayListOf<Fragment>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigation_bar.setOnItemClickListener(this)
        mFragmentList.add(ServiceFactory.getInstance().mainService!!.newMainFragment(bundle = null)!!)
        mFragmentList.add(ServiceFactory.getInstance().mainService!!.newCatalogFragment(bundle = null)!!)
        mFragmentList.add(ServiceFactory.getInstance().mainService!!.newFindFragment(bundle = null)!!)
        mFragmentList.add(ServiceFactory.getInstance().userService!!.newUserFragment(bundle = null)!!)


        val transaction = childFragmentManager.beginTransaction()
        mCurrent = mFragmentList[0]

        transaction.replace(R.id.content, mCurrent!!)
        transaction.commit()

    }

    private var mCurrent: Fragment? = null

    override fun onItemClickListener(checkedId: Int) {
        val transaction = childFragmentManager.beginTransaction()
        if (checkedId == R.id.home) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[0]

        } else if (checkedId == R.id.classification) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[1]
        } else if (checkedId == R.id.find) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[2]
        } else if (checkedId == R.id.mine) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = mFragmentList[3]
        }
        if (mCurrent!!.isAdded) {
            transaction.show(mCurrent!!)
        } else {
            transaction.add(R.id.content, mCurrent!!)
        }
        transaction.commit()
    }


}
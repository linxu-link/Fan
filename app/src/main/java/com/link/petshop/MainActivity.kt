package com.link.petshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.librarycomponent.ServiceFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.widgets.navgation.BottomNavigationBar
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterConstant.APP)
class MainActivity : BaseActivity(), BottomNavigationBar.OnClickListener {

    private var mFragmentList = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        fullScreen(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation_bar.setOnItemClickListener(this)

        mFragmentList.add(ServiceFactory.getInstance().mainService!!.newMainFragment(bundle = null)!!)
        mFragmentList.add(ServiceFactory.getInstance().mainService!!.newCatalogFragment(bundle = null)!!)
        mFragmentList.add(ServiceFactory.getInstance().mainService!!.newFindFragment(bundle = null)!!)
        mFragmentList.add(ServiceFactory.getInstance().userService!!.newUserFragment(bundle = null)!!)


        val transaction = supportFragmentManager.beginTransaction()
        mCurrent = mFragmentList[0]

        transaction.replace(R.id.content, mCurrent!!)
        transaction.commit()
    }


    private var mCurrent: Fragment? = null

    override fun onItemClickListener(checkedId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
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

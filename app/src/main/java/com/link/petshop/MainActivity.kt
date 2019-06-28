package com.link.petshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.librarycomponent.ServiceFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.widgets.navgation.BottomNavigationBar

@Route(path = RouterConstant.APP)
class MainActivity : AppCompatActivity(), BottomNavigationBar.OnClickListener {

    private var navigationBar: BottomNavigationBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationBar = findViewById(R.id.bottom_navigation_bar)
        navigationBar!!.setOnItemClickListener(this)

        val transaction = supportFragmentManager.beginTransaction()
        mCurrent = ServiceFactory.getInstance().mainService!!.newMainFragment(bundle = null)
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
            mCurrent = ServiceFactory.getInstance().mainService!!.newMainFragment(bundle = null)

        } else if (checkedId == R.id.classification) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = ServiceFactory.getInstance().catalogService!!.newCatalogFragment(bundle = null)
        } else if (checkedId == R.id.find) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = ServiceFactory.getInstance().findService!!.newFindFragment(bundle = null)
        } else if (checkedId == R.id.shopping_cart) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = ServiceFactory.getInstance().shopService!!.newShoppingCartFragment(bundle = null)
        } else if (checkedId == R.id.mine) {
            if (mCurrent != null) {
                transaction.hide(mCurrent!!)
            }
            mCurrent = ServiceFactory.getInstance().userService!!.newUserFragment(bundle = null)
        }
        if (mCurrent!!.isAdded) {
            transaction.show(mCurrent!!)
        } else {
            transaction.add(R.id.content, mCurrent!!)
        }
        transaction.commit()
    }
}

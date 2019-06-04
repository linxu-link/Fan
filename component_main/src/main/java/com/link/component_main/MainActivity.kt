package com.link.component_main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.link.component_main.app.ClassificationFragment
import com.link.component_main.app.FindFragment
import com.link.component_main.app.MainFragment
import com.link.component_main.app.ShoppingCartFragment
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.widgets.BottomNavigationBar

class MainActivity : AppCompatActivity(), BottomNavigationBar.OnClickListener {


    private var navigationBar: BottomNavigationBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_main)
        navigationBar = findViewById(R.id.bottom_navigation_bar)
        navigationBar!!.setOnItemClickListener(this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, MainFragment.newInstance(), MainFragment.TAG)
        transaction.commit()
    }


    private var mCurrent: Fragment? = null

    override fun onItemClickListener(checkedId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        if (checkedId == R.id.home) {
            if (mCurrent!=null){
                transaction.hide(mCurrent!!)
            }
            mCurrent = MainFragment.newInstance()

        } else if (checkedId == R.id.classification) {
            if (mCurrent!=null){
                transaction.hide(mCurrent!!)
            }
            mCurrent = ClassificationFragment.newInstance()
        } else if (checkedId == R.id.find) {
            if (mCurrent!=null){
                transaction.hide(mCurrent!!)
            }
            mCurrent = FindFragment.newInstance()
        } else if (checkedId == R.id.shopping_cart) {
            if (mCurrent!=null){
                transaction.hide(mCurrent!!)
            }
            mCurrent = ShoppingCartFragment.newInstance()
        } else if (checkedId == R.id.mine) {
            if (mCurrent!=null){
                transaction.hide(mCurrent!!)
            }
            mCurrent = ServiceFactory.getInstance().userService!!.newUserFragment(null)
        }
        if (mCurrent!!.isAdded){
            transaction.show(mCurrent!!)
        }else {
            transaction.add(R.id.content,mCurrent!!)
        }
        transaction.commit()
    }


}

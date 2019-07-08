package com.link.librarymodule.base

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.link.librarymodule.R
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.lang.ref.WeakReference


open class ContainerActivity : RxAppCompatActivity() {

    companion object {
        @JvmStatic
        val FRAGMENT = "FRAGMENT"
        @JvmStatic
        val BUNDLE = "BUNDLE"
        @JvmStatic
        val FRAGMENT_TAG = "content_fragment_tag"
    }

    protected var mFragment: WeakReference<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        if (savedInstanceState != null) {
            fragment = fm.getFragment(savedInstanceState, FRAGMENT_TAG)
        }
        if (fragment == null) {
            fragment = initFragmentFormIntent(intent)
        }
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.content, fragment)
        trans.commitAllowingStateLoss()
        mFragment = WeakReference(fragment)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState!!, FRAGMENT_TAG, mFragment!!.get()!!)
    }

    open fun initFragmentFormIntent(intent: Intent): Fragment {
        try {
            val fragmentName = intent.getStringExtra(FRAGMENT)
            if (TextUtils.isEmpty(fragmentName)) {
                throw IllegalArgumentException("fragmentName is empty")
            }
            val fragmentClass = Class.forName(fragmentName)
            val fragment = fragmentClass.newInstance() as Fragment
            val args = intent.getBundleExtra(BUNDLE)
            fragment.arguments = args
            return fragment
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        throw RuntimeException("fragment need init")
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.content)
        if (fragment is BaseMvvmFragment<*, *>) {
            super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

}
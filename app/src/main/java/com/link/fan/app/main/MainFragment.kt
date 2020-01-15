package com.link.fan.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.link.fan.R
import com.link.fan.databinding.FragmentMainBinding
import com.link.fan.navigation.NavGraphBuilder
import com.link.fan.navigation.NavigationConfig
import kotlinx.android.synthetic.main.fragment_main.*

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class MainFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mNavController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false).apply {
            lifecycleOwner = this@MainFragment.viewLifecycleOwner
            childFragmentManager.findFragmentById(R.id.main_nav_host)?.apply {
                mNavController = NavHostFragment.findNavController(this)
                NavGraphBuilder.build(this, mNavController, this.id)
                mainNavView.setOnNavigationItemSelectedListener(this@MainFragment)
            }
        }
        return binding.root
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        val destinationConfig = NavigationConfig.getDestinationConfig()
        destinationConfig?.apply {
            //遍历 target destination 是否需要登录拦截
//            for ((_, dest) in destinationConfig.entries) {
//                main_nav_view.selectedItemId = menuItem.itemId
//            }
        }
        mNavController.navigate(menuItem.itemId)
        return menuItem.title.isNotEmpty()
    }
}
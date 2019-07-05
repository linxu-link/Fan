package com.link.aac.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.link.aac.R

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val host: NavHostFragment = supportFragmentManager!!.findFragmentById(R.id.content) as NavHostFragment?
                ?: return
        val navController = host.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav?.setupWithNavController(navController)
    }
}

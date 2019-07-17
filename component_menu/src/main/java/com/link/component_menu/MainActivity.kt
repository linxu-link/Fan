package com.link.component_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_menu.app.MenuFragment
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.base.ContainerActivity

class MainActivity : ContainerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(this)
    }

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return MenuFragment.newInstance()
    }
}

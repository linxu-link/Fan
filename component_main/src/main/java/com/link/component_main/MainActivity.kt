package com.link.component_main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_main.app.MainFragment
import com.link.librarymodule.base.ContainerActivity

class MainActivity : ContainerActivity() {

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return MainFragment.newInstance()
    }

}

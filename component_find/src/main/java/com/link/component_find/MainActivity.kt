package com.link.component_find

import android.content.Intent
import androidx.fragment.app.Fragment
import com.link.component_find.app.FindFragment
import com.link.librarymodule.base.ContainerActivity

class MainActivity : ContainerActivity() {

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return FindFragment.newInstance()
    }

}
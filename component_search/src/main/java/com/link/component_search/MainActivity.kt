package com.link.component_search

import android.content.Intent
import androidx.fragment.app.Fragment
import com.link.component_search.app.search.SearchFragment
import com.link.librarymodule.base.ContainerActivity

class MainActivity : ContainerActivity() {

    override fun initFragmentFormIntent(intent: Intent): Fragment {
        return SearchFragment.newInstance()
    }
}

package com.link.component_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_main.app.MainFragment
import com.link.librarycomponent.service.main.IMainService

class MainService : IMainService {
    override fun newMainFragment(bundle: Bundle?): Fragment? {
        return MainFragment.newInstance()
    }
}
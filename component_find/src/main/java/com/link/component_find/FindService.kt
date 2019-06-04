package com.link.component_find

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_find.app.FindFragment
import com.link.librarycomponent.service.find.IFindService

class FindService:IFindService {
    override fun newFindFragment(bundle: Bundle?): Fragment? {
        return FindFragment.newInstance()
    }
}
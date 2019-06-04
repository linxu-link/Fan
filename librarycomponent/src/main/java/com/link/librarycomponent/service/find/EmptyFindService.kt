package com.link.librarycomponent.service.find

import android.os.Bundle
import androidx.fragment.app.Fragment

class EmptyFindService : IFindService {
    override fun newFindFragment(bundle: Bundle?): Fragment? {
        return null
    }
}
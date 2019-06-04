package com.link.librarycomponent.service.main

import android.os.Bundle
import androidx.fragment.app.Fragment

class EmptyMainService : IMainService {
    override fun newMainFragment(bundle: Bundle?): Fragment? {
        return null
    }
}
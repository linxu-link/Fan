package com.link.librarycomponent.service.find

import android.os.Bundle
import androidx.fragment.app.Fragment

interface IFindService {

    fun newFindFragment(bundle: Bundle?): Fragment?

}
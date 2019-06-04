package com.link.librarycomponent.service.user

import android.os.Bundle
import androidx.fragment.app.Fragment

class EmptyUserService :IUserService{


    override fun newUserFragment(bundle: Bundle?): Fragment? {
        return null
    }


}
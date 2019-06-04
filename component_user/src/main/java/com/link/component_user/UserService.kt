package com.link.component_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.link.component_user.app.UserFragment
import com.link.librarycomponent.service.user.IUserService

class UserService : IUserService {

    override fun newUserFragment(bundle: Bundle?): Fragment? {
        return UserFragment.newInstance()
    }

}
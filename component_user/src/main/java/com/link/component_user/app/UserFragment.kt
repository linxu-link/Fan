package com.link.component_user.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_user.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment

/**
 * @author WJ
 * @date 2019-06-03
 *
 * 描述：
 */
class UserFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                UserFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_fragment_user, container, false)
    }


}
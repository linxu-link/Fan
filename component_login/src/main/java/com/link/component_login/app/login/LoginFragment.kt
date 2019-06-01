package com.link.component_login.app.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.link.component_login.R
import com.link.component_login.ViewModelFactory
import com.link.component_login.databinding.FragmentLoginBinding
import com.link.librarymodule.BR
import com.link.librarymodule.base.mvvm.view.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_login
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun initParam() {
        super.initParam()
    }

    override fun initViewModel(): LoginViewModel? {
        return ViewModelFactory.getInstance(activity!!.application).create(LoginViewModel::class.java)
    }




}
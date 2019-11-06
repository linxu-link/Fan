package com.link.fan.app.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentLoginBinding

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-11:45
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
class LoginFragment : Fragment(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.loginViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,
                R.layout.fragment_login, container, false).apply {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            clickListener = this@LoginFragment
        }
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login -> {
                viewModel.login()
            }
            R.id.btn_wb -> {
                viewModel.clickWb()
            }
            R.id.btn_wx -> {
                viewModel.clickWx()
            }
            R.id.btn_qq -> {
                viewModel.clickQQ()
            }
            R.id.btn_send_code -> {
                viewModel.clickSmsCode()
            }
        }
    }

    private fun updateData() {

        with(viewModel) {

        }

    }


}
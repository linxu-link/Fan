package com.link.component_login.app.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import androidx.lifecycle.Observer
import com.link.component_login.R
import com.link.component_login.ViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * @author WJ
 * @date 2019-06-02
 *
 * 描述：登录界面
 */
class LoginFragment(override var mLayoutId: Int = R.layout.fragment_login) : BaseMvvmFragment<LoginViewModel>() {


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

    override fun initViewModel(): LoginViewModel {
        return ViewModelFactory.getInstance(activity!!.application).create(LoginViewModel::class.java)
    }

    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel.phone.observe(this, Observer {
            mViewModel.clearBtnVisibility.value = it.isEmpty()
        })


        clear_phone.setOnClickListener {
            mViewModel.clearPhone()
        }

        login_btn.setOnClickListener {
            mViewModel.login()
        }

        remember_pwd.setOnCheckedChangeListener { _, isChecked ->
            mViewModel.uc.pSwitchEvent.value = isChecked
        }

        mViewModel.uc.pSwitchEvent.observe(this, Observer {
            if (it) {
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
        })

    }


}
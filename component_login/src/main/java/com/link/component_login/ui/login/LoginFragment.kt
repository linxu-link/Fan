package com.link.component_login.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.link.component_login.R
import com.link.component_login.ui.ViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.utils.SimpleTextWatcher
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.login_fragment_login.*

/**
 * @author WJ
 * @date 2019-06-02
 *
 * 描述：登录
 */
class LoginFragment(override var layoutId: Int = R.layout.login_fragment_login) : BaseMvvmFragment<LoginViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() =
                LoginFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun getViewModel(): LoginViewModel {
        return ViewModelFactory.getInstance().create(LoginViewModel::class.java)
    }

    override fun initView() {
        super.initView()
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            activity!!.onBackPressed()
        }

        //登录按钮
        login_btn.setOnClickListener {
            mViewModel.phone.value = et_phone.text.toString()
            mViewModel.password.value = et_password.text.toString()
            mViewModel.login()
        }

        //记住密码按钮
        remember_pwd.setOnCheckedChangeListener { _, isChecked ->
            mViewModel.rememberPwd.value = isChecked
        }
        remember_pwd.isChecked = mViewModel.rememberPwd.value!!


        //清除手机号码
        clear_phone.setOnClickListener {
            mViewModel.clearPhone()
        }

        //控制 清除按钮
        et_phone.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s.toString().isNotEmpty()) {
                    clear_phone.visibility = View.VISIBLE
                } else {
                    clear_phone.visibility = View.INVISIBLE
                }
            }

        })

        swich_pwd.setOnCheckedChangeListener { _, checked ->
            mViewModel.uc.pSwitchEvent.value = checked
        }

        register.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        forget_pwd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.login_action_loginfragment_to_login_resetpwdfragment)
        }

    }

    override fun initViewObservable() {
        super.initViewObservable()
        mViewModel.phone.observe(this, Observer {
            et_phone.setText(it)
        })

        mViewModel.password.observe(this, Observer {
            et_password.setText(it)
        })

        mViewModel.uc.pSwitchEvent.observe(this, Observer {
            if (!it) {
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
        })

        mViewModel.rememberPwd.observe(this, Observer {
            MMKV.defaultMMKV().encode("remember_pwd", it)
        })
    }

    override fun getData() {
        super.getData()
        mViewModel.getUserData()
    }


}
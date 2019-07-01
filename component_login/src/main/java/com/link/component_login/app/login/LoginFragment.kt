package com.link.component_login.app.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.link.component_login.R
import com.link.component_login.ViewModelFactory
import com.link.component_login.databinding.FragmentLoginBinding
import com.link.librarymodule.BR
import com.link.librarymodule.base.mvvm.view.BaseFragment

/**
 * @author WJ
 * @date 2019-06-02
 *
 * 描述：登录界面
 */
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

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel!!.uc.pSwitchEvent.observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if (t!!) {
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    binding!!.ivSwichPasswrod.setImageResource(R.drawable.ic_eye_open)
                    binding!!.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                } else {
                    //密码不可见
                    binding!!.ivSwichPasswrod.setImageResource(R.drawable.ic_eye_close)
                    binding!!.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                }
            }
        })
    }


}
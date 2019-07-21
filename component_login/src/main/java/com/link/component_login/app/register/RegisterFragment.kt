package com.link.component_login.app.register

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.link.component_login.R
import com.link.component_login.ViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import kotlinx.android.synthetic.main.login_fragment_register.*


class RegisterFragment(override var layoutId: Int = R.layout.login_fragment_register)
    : BaseMvvmFragment<RegisterViewModel>() {

    override fun initViewModel(): RegisterViewModel {
        return ViewModelFactory.getInstance().create(RegisterViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                RegisterFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun initView() {
        super.initView()
        val title = mRootView!!.findViewById<TextView>(R.id.title)
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        title.text = "创建账户"
        back.setOnClickListener {
            findNavController().navigateUp()
        }
        login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

    }

    override fun initViewObservable() {
        super.initViewObservable()
    }


}
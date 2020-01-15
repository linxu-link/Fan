package com.link.fan.app.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentLoginBinding
import com.link.fan.utils.StatusBar
import com.link.libraryannotation.ActivityDestination

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
    }

    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.loginViewModelFactory()
    }

    private fun subscribeUi() {

    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
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
    }

    private fun updateData() {

        with(viewModel) {

        }

    }


}

package com.link.component_login.app.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_login.R
import com.trello.rxlifecycle2.components.support.RxFragment


class RegisterFragment: Fragment() {

    companion object {
            @JvmStatic
            fun newInstance() =
                RegisterFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

}
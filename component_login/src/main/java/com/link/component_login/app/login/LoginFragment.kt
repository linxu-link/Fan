package com.link.component_login.app.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.link.component_login.R

class LoginFragment : Fragment() {

   companion object {
           @JvmStatic
           fun newInstance() =
               LoginFragment().apply {
                   arguments = Bundle().apply {

                   }
               }
       }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}
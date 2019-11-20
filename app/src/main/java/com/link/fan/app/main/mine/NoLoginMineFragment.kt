package com.link.fan.app.main.mine


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController

import com.link.fan.R
import com.link.fan.databinding.FragmentNoLoginMineBinding
import com.link.librarymodule.utils.CommonUtil

/**
 * A simple [Fragment] subclass.
 */
class NoLoginMineFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                NoLoginMineFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNoLoginMineBinding>(inflater, R.layout.fragment_no_login_mine, container, false).apply {
            if (CommonUtil.getStatusBarHeight() >= 0) {
                val layout = FrameLayout.LayoutParams(root.layoutParams)
                layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                root.layoutParams = layout
                gifId = R.mipmap.icon_jverfy_guide
            }
            mobileClickListener = View.OnClickListener {
                it.findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }

            wxClickListener = View.OnClickListener {
                findNavController(requireActivity(), it.id).navigate(R.id.action_mainFragment_to_loginFragment)
            }
        }
        return binding.root
    }


}

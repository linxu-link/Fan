package com.link.fan.app.main.mine


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.link.fan.R
import com.link.fan.app.login.LoginActivity
import com.link.fan.databinding.FragmentNoLoginMineBinding
import com.link.fan.navigation.NavigationConfig
import com.link.fan.navigation.mineUrl
import com.link.libraryannotation.FragmentDestination
import com.link.librarymodule.utils.CommonUtil

/**
 * A simple [Fragment] subclass.
 */
@FragmentDestination(pageUrl = mineUrl, needLogin = false, asStarter = false)
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
            }

            gifId = R.mipmap.icon_jverfy_guide

            mobileClickListener = View.OnClickListener {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)

            }

            wxClickListener = View.OnClickListener {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return binding.root
    }


}

package com.link.component_user.app.user

import android.os.Bundle
import android.view.View
import com.link.component_user.R
import com.link.librarymodule.base.BaseFragment
import kotlinx.android.synthetic.main.user_include_user_body.*

/**
 * @author WJ
 * @date 2019-06-03
 *
 * 描述：
 */
class UserFragment(override var layoutId: Int = R.layout.user_fragment_user) : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
                UserFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_about.setOnClickListener {

        }

        img_score.setOnClickListener {

        }

        img_update.setOnClickListener {

        }

        img_share.setOnClickListener {

        }

        img_suggest.setOnClickListener {

        }

    }


}
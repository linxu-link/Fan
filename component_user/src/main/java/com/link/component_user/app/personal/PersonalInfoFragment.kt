package com.link.component_user.app.personal

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.link.component_user.R
import com.link.component_user.app.ViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.router.StartRouter
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.utils.AppManager
import kotlinx.android.synthetic.main.login_fragment_personal_info.*

/**
 * @author WJ
 * @date 2019-07-23
 *
 * 描述：个人信息
 */
class PersonalInfoFragment(override var layoutId: Int = R.layout.login_fragment_personal_info) : BaseMvvmFragment<PersonalInfoViewModel>() {

    override fun getViewModel(): PersonalInfoViewModel {
        return ViewModelFactory.getInstance().create(PersonalInfoViewModel::class.java)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                PersonalInfoFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun initView() {
        super.initView()
        val title = mRootView!!.findViewById<TextView>(R.id.title)
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        val rightIcon = mRootView!!.findViewById<ImageView>(R.id.right_icon)
        title.text = "我的信息"
        back.setOnClickListener {
            activity!!.onBackPressed()
        }
        rightIcon.visibility = View.VISIBLE
        rightIcon.setOnClickListener {
            mViewModel.loginOut()
            AppManager.instance.finishAllActivity()
            StartRouter.navigation(RouterConstant.LOGIN)
        }

        avatar.setOnClickListener {

        }

        btn_confirm.setOnClickListener {
            mViewModel.userEntity.value!!.displayName = et_username.text.toString()
            mViewModel.userEntity.value!!.introduction = et_introduction.text.toString()
            mViewModel.updateUserInfo()
        }


    }

    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel.userEntity.observe(this, Observer {
            et_username.setText(it.username)
            et_introduction.setText(it.introduction)
        })

        mViewModel.uc.pUpdateEvent.observe(this, Observer {
            activity!!.onBackPressed()
        })
    }


}

package com.link.component_user.ui.personal

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.link.component_user.R
import com.link.component_user.ui.ViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.router.StartRouter
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.GlideLoadEngine
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.IncapableCause
import com.zhihu.matisse.internal.entity.Item
import kotlinx.android.synthetic.main.user_fragment_personal_info.*


const val REQUEST_CODE_CHOOSE = 0x1212

/**
 * @author WJ
 * @date 2019-07-23
 *
 * 描述：个人信息
 */
class PersonalInfoFragment(override var layoutId: Int = R.layout.user_fragment_personal_info) : BaseMvvmFragment<PersonalInfoViewModel>() {

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
            Matisse.from(this)
                    .choose(MimeType.ofImage(), false)
                    .countable(true)
                    .maxSelectable(1)
//                    .addFilter(filter)
                    .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.dp_150))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.75f)
                    .imageEngine(GlideLoadEngine())
                    .forResult(REQUEST_CODE_CHOOSE)
        }

        btn_confirm.setOnClickListener {
            mViewModel.userEntity.value!!.displayName = et_username.text.toString()
            mViewModel.userEntity.value!!.introduction = et_introduction.text.toString()
            mViewModel.updateUserInfo()
        }

    }


    private val filter = object : Filter() {

        override fun constraintTypes(): MutableSet<MimeType> {
            return object : HashSet<MimeType>() {
                init {
                    add(MimeType.PNG)
                }
            }
        }

        //过滤条件
        override fun filter(context: Context?, item: Item?): IncapableCause {
            return IncapableCause("无限制")
        }

    }


    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel.userEntity.observe(this, Observer {
            et_username.setText(it.displayName)
            et_introduction.setText(it.introduction)
        })

        mViewModel.uc.pUpdateEvent.observe(this, Observer {
            if (it) {
                activity!!.onBackPressed()
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            val path = Matisse.obtainPathResult(data)[0]
            Glide.with(this)
                    .asBitmap()
                    .load(path)
                    .into(avatar)
            mViewModel.updateAvatar(path)

        }
    }


}

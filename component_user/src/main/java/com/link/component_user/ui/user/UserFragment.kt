package com.link.component_user.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.link.component_user.R
import com.link.component_user.ui.ViewModelFactory
import com.link.component_user.ui.about.AboutFragment
import com.link.component_user.ui.collection.CollectionFragment
import com.link.component_user.ui.footprint.FootPrintFragment
import com.link.component_user.ui.personal.PersonalInfoFragment
import com.link.librarycomponent.ServiceFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.router.StartRouter
import com.link.librarycomponent.service.shopping.IShoppingService
import com.link.librarycomponent.service.update.IUpdateService
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.utils.RxCountDown
import com.link.librarymodule.utils.ToastUtils
import kotlinx.android.synthetic.main.user_fragment_user.*
import kotlinx.android.synthetic.main.user_include_user_body.*
import kotlinx.android.synthetic.main.user_include_user_header.*

/**
 * @author WJ
 * @date 2019-06-03
 *
 * 描述：我的界面
 */
class UserFragment(override var layoutId: Int = R.layout.user_fragment_user) : BaseMvvmFragment<UserViewModel>() {

    override fun getViewModel(): UserViewModel {
        return ViewModelFactory.getInstance().create(UserViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                UserFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun initView() {
        super.initView()
        img_collection.setOnClickListener {
            if (ServiceFactory.getInstance().loginService!!.isLogin()) {
                startContainerActivity(CollectionFragment::class.java.canonicalName!!, null)
            } else {
                StartRouter.navigation(RouterConstant.LOGIN)
            }
        }

        img_footprint.setOnClickListener {
            if (ServiceFactory.getInstance().loginService!!.isLogin()) {
                startContainerActivity(FootPrintFragment::class.java.canonicalName!!, null)
            } else {
                StartRouter.navigation(RouterConstant.LOGIN)
            }
        }

        img_attention.setOnClickListener {
            ToastUtils.showLong("此功能开发中，敬请期待")
        }

        img_note.setOnClickListener {
            ToastUtils.showLong("此功能开发中，敬请期待")
        }

        img_about.setOnClickListener {
            startContainerActivity(AboutFragment::class.java.canonicalName!!, null)
        }

        //应用商店打分
        img_score.setOnClickListener {
            //华为：com.huawei.appmarket
            //oppo：com.oppo.market
            //vivo：com.bbk.appstore
            launchAppDetail("com.xiaomi.market")
        }

        img_update.setOnClickListener {
            val updateService = ARouter.getInstance().build(RouterConstant.UPDATE_SERVICE).navigation() as IUpdateService
            updateService.startUpdateService()
            ToastUtils.showLong("请稍后……")
        }

        img_share.setOnClickListener {
            val intent = Intent()
            intent.type = "text/plain"
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.putExtra(Intent.EXTRA_SUBJECT, "分享")
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.user_app_introduction))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.action = Intent.ACTION_SEND
            startActivity(Intent.createChooser(intent, "分享至"))

        }

        img_suggest.setOnClickListener {
            ARouter.getInstance().build(RouterConstant.SHOPPING).navigation()
        }

        user_view.setOnClickListener {
            if (ServiceFactory.getInstance().loginService!!.isLogin()) {
                startContainerActivity(PersonalInfoFragment::class.java.canonicalName!!, null)
            } else {
                StartRouter.navigation(RouterConstant.LOGIN)
            }
        }

    }

    private fun launchAppDetail(marketPkg: String) {
        try {
            val uri = Uri.parse("market://details?id=com.link.fan")
            val intent = Intent(Intent.ACTION_VIEW, uri)

            if (marketPkg.isNotEmpty()) {
                intent.setPackage(marketPkg)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun initViewObservable() {
        super.initViewObservable()
        mViewModel.userEntity.observe(this, Observer {
            if (it != null) {
                username.text = it.displayName
                user_phone.text = it.mobilePhoneNumber
                if (it.avatar != null) {
                    Glide.with(context!!).load(it.avatar!!.url).into(user_avatar)
                    Log.e("error", it.avatar!!.url + "," + it.avatar!!.fileUrl)
                }
            }
        })

    }


    override fun getData() {
        super.getData()
        if (mViewModel.userEntity.value != null) {
            mViewModel.getUserData()
        }
    }


}

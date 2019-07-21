package com.link.component_user.app.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.link.component_user.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.ToastUtils
import kotlinx.android.synthetic.main.user_include_user_body.*
import kotlinx.android.synthetic.main.user_include_user_header.*


/**
 * @author WJ
 * @date 2019-06-03
 *
 * 描述：我的界面
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_collection.setOnClickListener {
            findNavController().navigate(R.id.user_collectionfragment)
        }

        img_footprint.setOnClickListener {
            findNavController().navigate(R.id.user_footprintfragment)
        }

        img_attention.setOnClickListener {
            ToastUtils.showLong("此功能开发中，敬请期待")
        }

        img_note.setOnClickListener {
            ToastUtils.showLong("此功能开发中，敬请期待")
        }


        img_about.setOnClickListener {
            findNavController().navigate(R.id.user_aboutfragment)
        }

        //应用商店打分
        img_score.setOnClickListener {
            //华为：com.huawei.appmarket
            //oppo：com.oppo.market
            //vivo：com.bbk.appstore
            launchAppDetail("com.xiaomi.market")
        }

        img_update.setOnClickListener {

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
            ToastUtils.showLong("此功能开发中，敬请期待")
        }

    }


    fun launchAppDetail(marketPkg: String) {
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

}

package com.link.fan.app.community.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.link.fan.R
import com.link.fan.databinding.ActivityCommunityDetailBinding
import com.link.fan.utils.StatusBar
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * author:wujia
 * create:2020-02-01-17:34
 * version: 1.0
 * description:
 */
class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        DataBindingUtil
                .setContentView<ActivityCommunityDetailBinding>(this, R.layout.activity_community_detail)
                .apply {
                    if (CommonUtil.getStatusBarHeight() >= 0) {
                        val layout = FrameLayout.LayoutParams(root.layoutParams)
                        layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                        root.layoutParams = layout
                    }
                    toolbar_title.text = "全部评论"
                }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, CommunityDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

}

package com.link.fan.app.publish

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.link.fan.R
import com.link.fan.databinding.ActivityPublishBinding
import com.link.fan.navigation.publishUrl
import com.link.fan.utils.StatusBar
import com.link.libraryannotation.ActivityDestination
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * author:wujia
 * create:2020-02-02-00:04
 * version: 1.0
 * description:
 */
@ActivityDestination(pageUrl = publishUrl, needLogin = true)
class PublishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityPublishBinding>(this, R.layout.activity_publish)
                .apply {
                    if (CommonUtil.getStatusBarHeight() >= 0) {
                        val layout = FrameLayout.LayoutParams(root.layoutParams)
                        layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                        root.layoutParams = layout
                    }
                }
        toolbar_title.text = "发布新鲜事"
    }

}

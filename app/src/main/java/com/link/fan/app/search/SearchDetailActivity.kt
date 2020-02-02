package com.link.fan.app.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.link.fan.R
import com.link.fan.databinding.ActivitySearchDetailBinding
import com.link.fan.utils.StatusBar
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.include_toolbar_search.*

/**
 * author:wujia
 * create:2020-02-01-23:47
 * version: 1.0
 * description:
 */
class SearchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivitySearchDetailBinding>(this, R.layout.activity_search_detail)
                .apply {
                    if (CommonUtil.getStatusBarHeight() >= 0) {
                        val layout = FrameLayout.LayoutParams(root.layoutParams)
                        layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                        root.layoutParams = layout
                    }
                }
        toolbar_left_icon.setImageResource(R.drawable.ic_arrow_back_black)
        toolbar_left_icon.setOnClickListener {
            onBackPressed()
        }
        iv_right_icon.visibility = View.VISIBLE
        iv_right_icon.setImageResource(R.drawable.icon_search_black)
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context,SearchDetailActivity::class.java)
            context.startActivity(intent)
        }

    }
}

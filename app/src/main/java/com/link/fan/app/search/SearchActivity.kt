package com.link.fan.app.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.link.fan.R
import com.link.fan.databinding.ActivitySearchBinding
import com.link.fan.utils.StatusBar
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.include_toolbar_search.*

/**
 * author:wujia
 * create:2020-02-01-22:43
 * version: 1.0
 * description:
 */
class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivitySearchBinding>(this, R.layout.activity_search)
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
        toolbar_right_icon.visibility = View.VISIBLE
        toolbar_right_icon.setImageResource(R.drawable.icon_search_black)
        toolbar_right_icon.setOnClickListener {
            SearchDetailActivity.startActivity(this)
        }
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }

    }

}

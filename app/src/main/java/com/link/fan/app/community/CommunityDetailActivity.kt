package com.link.fan.app.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.link.fan.R
import com.link.fan.app.community.reply.ReplyActivity
import com.link.fan.utils.StatusBar
import kotlinx.android.synthetic.main.fragment_community_detail.*

class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_community_detail)

        reply_layout.setOnClickListener {
            val intent = Intent(this, ReplyActivity::class.java)
            startActivity(intent)
        }
    }
}

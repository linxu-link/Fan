package com.link.component_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        },2000)
    }

    val handler = Handler(Handler.Callback { false })
}

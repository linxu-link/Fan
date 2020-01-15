package com.link.fan.app.publish

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.link.fan.R
import com.link.fan.navigation.publishUrl
import com.link.libraryannotation.ActivityDestination

import kotlinx.android.synthetic.main.activity_publish.*
import retrofit2.http.Url
import java.net.URLConnection

@ActivityDestination(pageUrl = publishUrl, needLogin = true)
class PublishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}

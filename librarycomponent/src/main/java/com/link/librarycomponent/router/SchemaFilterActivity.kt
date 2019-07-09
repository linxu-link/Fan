package com.link.librarycomponent.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author WJ
 * @date 2019-05-30
 *
 * 描述：监听Schame事件的activity
 */
class SchemaFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        ARouter.getInstance()
            .build(uri)
            .navigation()
        finish()
    }


}
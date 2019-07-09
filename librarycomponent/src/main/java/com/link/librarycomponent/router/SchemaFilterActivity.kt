package com.link.librarycomponent.router

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author WJ
 * @date 2019-05-30
 *
 * 描述：监听Schame事件的activity
 */
class SchemaFilterActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        ARouter.getInstance()
            .build(uri)
            .navigation()
        finish()
    }


}
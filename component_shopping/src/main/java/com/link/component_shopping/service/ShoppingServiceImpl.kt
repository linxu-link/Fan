package com.link.component_shopping.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message

/**
 * @author WJ
 * @date 2019-08-13
 *
 * 描述：空白service，用于提前启动：shopping进程
 */
const val INIT_WEB_VIEW=0x1231

class ShoppingServiceImpl: Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                INIT_WEB_VIEW -> initWebView()
                else -> {
                }
            }
        }
    }


    private fun initWebView(){

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mHandler.sendEmptyMessage(INIT_WEB_VIEW)
        return super.onStartCommand(intent, flags, startId)
    }


}
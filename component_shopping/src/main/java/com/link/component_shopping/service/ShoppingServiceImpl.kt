package com.link.component_shopping.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @author WJ
 * @date 2019-08-13
 *
 * 描述：空白service，用于提前启动：shopping进程
 */

class ShoppingServiceImpl: Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
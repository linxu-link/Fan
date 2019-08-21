package com.link.fan.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Message
import com.link.fan.service.ShoppingService
import com.link.general_daemon.DaemonEnv
import com.link.librarymodule.constant.Constant

/**
 * @author WJ
 * @date 2019-08-21
 *
 * 描述：应用广播，用来接收各种应用内发出的自定义广播
 * 1.监听shopping进程关闭的广播
 */

const val START_SHOPPING_SERVICE = 0x3001

class FanReceiver constructor(context: Context) : BroadcastReceiver() {

    init {
        startShoppingService()
        registerNetworkStatusReceiver(context)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.action == Constant.ACTION_SHOPPING) {
            startShoppingService()
        }
    }


    companion object {
        private val mHandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                if (msg == null) return
                when (msg.what) {
                    START_SHOPPING_SERVICE -> {
                        DaemonEnv.startServiceMayBind(ShoppingService::class.java)
                    }
                }
            }
        }
    }

    //延迟两秒启动商城的service
    private fun startShoppingService() {
        mHandler.sendEmptyMessageDelayed(START_SHOPPING_SERVICE, 2000)
    }

    /**
     * 注册广播
     *
     * @param context
     */
    private fun registerNetworkStatusReceiver(context: Context) {
        val filter = IntentFilter()
        filter.addAction(Constant.ACTION_SHOPPING)
        context.registerReceiver(this, filter)
    }

    private fun unregisterNetworkReceiver(context: Context) {
        context.unregisterReceiver(this)
    }

}
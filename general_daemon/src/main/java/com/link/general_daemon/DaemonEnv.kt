package com.link.general_daemon

import android.app.Application
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import java.lang.Exception
import java.util.HashMap

/**
 * @author WJ
 * @date 2019-08-21
 *
 * 描述：用于控制守护进程
 */
const val DEFAULT_WAKE_UP_INTERVAL = 6 * 60 * 1000L
const val MINIMAL_WAKE_UP_INTERVAL = 3 * 60 * 1000L

object DaemonEnv {

    var sApp: Application? = null

    var sServiceClass: Class<out Service>? = null

    var sWakeUpInterval = DEFAULT_WAKE_UP_INTERVAL

    var sInitialized = false

    val BIND_STATE_MAP: MutableMap<Class<out Service>, ServiceConnection> = HashMap()

    fun init(app: Application, serviceClass: Class<out Service>, wakeUpInterval: Long) {
        sApp = app
        sServiceClass = serviceClass
        sWakeUpInterval = wakeUpInterval
        sInitialized = true
    }

    fun startServiceMayBind(serviceClass: Class<out Service>) {
        if (!sInitialized) return

        val intent = Intent(sApp, serviceClass)
        startServiceSafely(intent)

        val bound = BIND_STATE_MAP[serviceClass]
        if (bound == null)
            sApp!!.bindService(intent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName, service: IBinder) {
                    BIND_STATE_MAP[serviceClass] = this
                }

                override fun onServiceDisconnected(name: ComponentName) {
                    BIND_STATE_MAP.remove(serviceClass)
                    startServiceSafely(intent)
                    if (!sInitialized) return
                    sApp!!.bindService(intent, this, Context.BIND_AUTO_CREATE)
                }

                override fun onBindingDied(name: ComponentName) {
                    onServiceDisconnected(name)
                }
            }, Context.BIND_AUTO_CREATE)

    }


    fun startServiceSafely(intent: Intent) {
        if (sInitialized) {
            try {
                sApp!!.startService(intent)
            } catch (e: Exception) {

            }
        }
    }

    fun getWakeUpInterval():Long{
        return sWakeUpInterval
    }


}
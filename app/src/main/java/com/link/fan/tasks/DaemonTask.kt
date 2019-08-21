package com.link.fan.tasks

import android.app.Application
import com.link.fan.service.ShoppingService
import com.link.general_daemon.DEFAULT_WAKE_UP_INTERVAL
import com.link.general_daemon.DaemonEnv
import com.link.librarymodule.launchstarter.task.Task

class DaemonTask : Task() {

    override fun run() {
        DaemonEnv.init(mContext.applicationContext as Application, ShoppingService::class.java, DEFAULT_WAKE_UP_INTERVAL)
        ShoppingService.sShouldStopService = false
        DaemonEnv.startServiceMayBind(ShoppingService::class.java)
    }

}
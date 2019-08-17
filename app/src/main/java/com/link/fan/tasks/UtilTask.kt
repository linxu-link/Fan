package com.link.fan.tasks

import com.link.librarymodule.launchstarter.task.Task
import com.link.librarymodule.utils.Utils

class UtilTask : Task() {

    override fun run() {
        Utils.init(mContext)
    }

    override fun needWait(): Boolean {
        return true
    }

    override fun onlyInMainProcess(): Boolean {
        return false
    }
}
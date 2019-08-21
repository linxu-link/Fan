package com.link.fan.tasks

import com.link.fan.receiver.FanReceiver
import com.link.librarymodule.launchstarter.task.Task

/**
 * @author WJ
 * @date 2019-08-16
 *
 * 描述：用于启动shopping进程的task
 */
class ReceiverTask:Task() {

    override fun run() {

        val receiver=FanReceiver(mContext)

    }


}
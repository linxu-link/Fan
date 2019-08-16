package com.link.fan.tasks

import com.link.fan.tinker.TinkerService
import com.link.librarymodule.launchstarter.task.Task
import com.link.librarymodule.utils.Utils

/**
 * @author WJ
 * @date 2019-08-16
 *
 * 描述：用于启动热修复的task
 */
class HotfixTask: Task() {

    override fun run() {
        //启动热修复service
        TinkerService.runTinkerService(Utils.getContext())
    }


}
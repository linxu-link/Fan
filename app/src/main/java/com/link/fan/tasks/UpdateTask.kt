package com.link.fan.tasks

import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.service.update.IUpdateService
import com.link.librarymodule.launchstarter.task.Task
/**
 * @author WJ
 * @date 2019-08-16
 *
 * 描述：启动更新的task
 */
class UpdateTask:Task() {
    override fun run() {
        //启动更新service
        val updateService = ARouter.getInstance().build(RouterConstant.UPDATE_SERVICE).navigation()!! as IUpdateService
        updateService.startUpdateService()
    }
}
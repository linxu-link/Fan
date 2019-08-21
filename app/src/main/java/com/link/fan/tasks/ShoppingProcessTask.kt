package com.link.fan.tasks

import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.service.shopping.IShoppingService
import com.link.librarymodule.launchstarter.task.Task
/**
 * @author WJ
 * @date 2019-08-16
 *
 * 描述：用于启动shopping进程的task
 */
class ShoppingProcessTask:Task() {

    override fun run() {
        //启动商城的service
        val shoppingService = ARouter.getInstance().build(RouterConstant.SHOPPING_SERVICE).navigation()!! as IShoppingService
        shoppingService.startShoppingService()
    }


}
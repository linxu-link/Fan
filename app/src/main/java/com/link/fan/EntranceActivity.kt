package com.link.fan

import android.os.Bundle
import androidx.navigation.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.fan.tasks.HotfixTask
import com.link.fan.tasks.ReceiverTask
import com.link.fan.tasks.UpdateTask
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.launchstarter.DelayInitDispatcher
import com.link.librarymodule.utils.ToastUtils

/**
 * @author WJ
 * @date 2019-07-21
 *
 * 描述：入口activity
 */
@Route(path = RouterConstant.APP)
class EntranceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //开启全屏占用
        fullScreen(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_container)

        //task延迟初始化调度器
        val dispatcher = DelayInitDispatcher()
        //延迟初始化 热修复检查、启动广播、更新检查
        dispatcher.addTask(HotfixTask())
                .addTask(ReceiverTask())
                .addTask(UpdateTask())
                .start()

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container).navigateUp()
    }


}

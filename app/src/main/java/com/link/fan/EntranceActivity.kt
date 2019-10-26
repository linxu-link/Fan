package com.link.fan

import android.os.Bundle
import androidx.navigation.findNavController
import com.link.fan.tasks.HotfixTask
import com.link.fan.tasks.UpdateTask
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.launchstarter.DelayInitDispatcher

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-07-21-09:45
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
class EntranceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //开启全屏占用
        fullScreen(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_container)

        //task延迟初始化调度器
        val dispatcher = DelayInitDispatcher()
        //延迟初始化 热修复检查、更新检查
        dispatcher.addTask(HotfixTask())
                .addTask(UpdateTask())
                .start()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container).navigateUp()
    }


}

package com.link.fan

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.link.fan.databinding.ActivityMainBinding
import com.link.librarymodule.base.BaseActivity

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-07-21-09:45
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //开启全屏占用
        fullScreen(this)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        //task延迟初始化调度器
//        val dispatcher = DelayInitDispatcher()
//        //延迟初始化 热修复检查、更新检查
//        dispatcher.addTask(HotfixTask())
//                .addTask(UpdateTask())
//                .start()
    }

//
//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.nav_host).navigateUp()
//    }


}

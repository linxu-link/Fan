package com.link.librarycomponent.tasks

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.BuildConfig
import com.link.librarymodule.launchstarter.task.Task
/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：初始化ARouter
 */
class ARouterTask : Task() {
    override fun run() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mContext.applicationContext as Application); // 尽可能早，推荐在Application中初始化
    }

    override fun needWait(): Boolean {
        return true
    }
}
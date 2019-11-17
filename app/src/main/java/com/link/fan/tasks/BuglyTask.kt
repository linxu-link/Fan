package com.link.fan.tasks

import com.link.librarymodule.BuildConfig
import com.link.librarymodule.constant.BUGLY_ID
import com.link.librarymodule.launchstarter.task.Task
import com.tencent.bugly.crashreport.CrashReport
/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：初始化bugly的task
 */
class BuglyTask: Task() {
    override fun run() {
        CrashReport.initCrashReport(mContext, BUGLY_ID, BuildConfig.DEBUG);
    }
}
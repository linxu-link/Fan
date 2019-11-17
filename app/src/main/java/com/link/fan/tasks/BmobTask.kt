package com.link.fan.tasks

import cn.bmob.v3.Bmob
import com.link.librarymodule.constant.BMOB_ID
import com.link.librarymodule.launchstarter.task.Task

/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：初始化bugly的task
 */
class BmobTask : Task() {
    override fun run() {
        Bmob.initialize(mContext, BMOB_ID)
    }
}
package com.link.fan.tasks

import cn.bmob.v3.Bmob
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.launchstarter.task.Task
/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：bmob初始化Task
 */
class BmobTask : Task() {
    override fun run() {
        //初始化Bmob后台服务器的SDK
        Bmob.initialize(mContext, Constant.BMOB_ID)
    }
}
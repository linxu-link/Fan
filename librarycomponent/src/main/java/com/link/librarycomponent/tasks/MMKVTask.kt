package com.link.librarycomponent.tasks

import com.link.librarymodule.launchstarter.task.Task
import com.tencent.mmkv.MMKV
/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：mmkv的初始化
 */
class MMKVTask : Task() {
    override fun run() {
        //初始化MMKV（一种腾讯出品的sp替代工具）
        MMKV.initialize(mContext)
    }
}
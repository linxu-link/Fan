package com.link.fan.tasks

import com.link.librarycomponent.widgets.webview.sonic.SonicRuntimeImpl
import com.link.librarymodule.launchstarter.task.Task
import com.link.librarymodule.utils.Utils
import com.tencent.smtt.sdk.QbSdk
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine

/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：初始化腾讯x5内核的webview，和vasSonic，Webview加速器 的task
 */
class H5Task : Task() {

    override fun run() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        val cb = object : QbSdk.PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

            }

            override fun onCoreInitFinished() {

            }
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(mContext, cb)

    }

    override fun onlyInMainProcess(): Boolean {
        return false
    }
}
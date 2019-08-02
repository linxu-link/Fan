package com.link.librarycomponent

import android.app.Application
import android.content.Context
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.BaseApplication
import com.link.librarymodule.BuildConfig
import com.link.librarymodule.constant.Constant
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV
/**
 * @author WJ
 * @date 2019-08-02
 *
 * 描述：项目的基础Application
 */
abstract class FanApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initARouter(this)
        initBmobSdk(this.applicationContext)
        initMMKV(this.applicationContext)
        initBugly(this.applicationContext)
    }


    /**
     * 初始化ARouter
     */
    private fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
    }

    /**
     * 初始化Bmob后台服务器的SDK
     */
    private fun initBmobSdk(context: Context) {
        Bmob.initialize(context, Constant.BMOB_ID)
    }

    /**
     * 初始化MMKV（一种腾讯出品的sp替代工具）
     */
    private fun initMMKV(context: Context) {
        MMKV.initialize(context)
    }

    /**
     * 初始化bugly
     */
    private fun initBugly(context: Context) {
        CrashReport.initCrashReport(context, Constant.BUGLY_ID, BuildConfig.DEBUG);
    }
}
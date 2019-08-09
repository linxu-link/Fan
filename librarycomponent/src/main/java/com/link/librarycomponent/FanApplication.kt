package com.link.librarycomponent

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.BaseApplication
import com.link.librarymodule.BuildConfig
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV
import com.tencent.smtt.sdk.QbSdk

/**
 * @author WJ
 * @date 2019-08-02
 *
 * 描述：项目的基础Application
 */
abstract class FanApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }

    companion object {
        /**
         * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
         *
         * @param application
         */
        @Synchronized
        @JvmStatic
        fun setApplication(@NonNull application: Application) {
            Utils.init(application)
            initARouter(application)
            initBmobSdk(application.applicationContext)
            initMMKV(application.applicationContext)
            initBugly(application.applicationContext)
            initX5WebView(application.applicationContext)
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

        /**
         * 初始化X5webView
         */
        private fun initX5WebView(context: Context){
            //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
            val cb = object : QbSdk.PreInitCallback {
                override fun onViewInitFinished(arg0: Boolean) {
                    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

                }

                override fun onCoreInitFinished() {

                }
            }
            //x5内核初始化接口
            QbSdk.initX5Environment(context.applicationContext, cb)
        }
    }



}
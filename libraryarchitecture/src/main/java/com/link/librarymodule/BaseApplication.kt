package com.link.librarymodule

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.NonNull
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.Utils


abstract class BaseApplication : Application() {


    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    @Synchronized
    fun setApplication(@NonNull application: Application) {
        instance = application
        Utils.init(application)
        initARouter(application)
        initBmobSdk(application.applicationContext)
//        registerWX()
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {

            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {
                AppManager.instance.removeActivity(activity)
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                AppManager.instance.addActivity(activity)
            }

        })
    }

    /**
     * Application 初始化
     */
    abstract fun initModuleApp(application: Application)

    /**
     * 所有 Application 初始化后的自定义操作
     */
    abstract fun initModuleData(application: Application)

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

    private fun initBmobSdk(context: Context) {
        Bmob.initialize(context, Constant.BMOB_ID)
    }


    // IWXAPI 是第三方app和微信通信的openApi接口
//    private var api: IWXAPI? = null
//
//    //注册
//    private fun registerWX() {
//        // 通过WXAPIFactory工厂，获取IWXAPI的实例
//        api = WXAPIFactory.createWXAPI(this, Constant.WEIXING_ID, true)
//        // 将应用的appId注册到微信
//        api!!.registerApp(Constant.WEIXING_ID)
//        //建议动态监听微信启动广播进行注册到微信
//        registerReceiver(object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                // 将该app注册到微信
//                api!!.registerApp(Constant.WEIXING_ID)
//            }
//        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
//
//    }

}
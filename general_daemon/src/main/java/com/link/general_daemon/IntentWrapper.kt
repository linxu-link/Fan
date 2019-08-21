package com.link.general_daemon

import android.app.Activity
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import java.util.*

class IntentWrapper constructor(var intent: Intent,var type: Int) {


    //Android 7.0+ Doze 模式
    protected val DOZE = 98
    //华为 自启管理
    protected val HUAWEI = 99
    //华为 锁屏清理
    protected val HUAWEI_GOD = 100
    //小米 自启动管理
    protected val XIAOMI = 101
    //小米 神隐模式
    protected val XIAOMI_GOD = 102
    //三星 5.0/5.1 自启动应用程序管理
    protected val SAMSUNG_L = 103
    //魅族 自启动管理
    protected val MEIZU = 104
    //魅族 待机耗电管理
    protected val MEIZU_GOD = 105
    //Oppo 自启动管理
    protected val OPPO = 106
    //三星 6.0+ 未监视的应用程序管理
    protected val SAMSUNG_M = 107
    //Oppo 自启动管理(旧版本系统)
    protected val OPPO_OLD = 108
    //Vivo 后台高耗电
    protected val VIVO_GOD = 109
    //金立 应用自启
    protected val GIONEE = 110
    //乐视 自启动管理
    protected val LETV = 111
    //乐视 应用保护
    protected val LETV_GOD = 112
    //酷派 自启动管理
    protected val COOLPAD = 113
    //联想 后台管理
    protected val LENOVO = 114
    //联想 后台耗电优化
    protected val LENOVO_GOD = 115
    //中兴 自启管理
    protected val ZTE = 116
    //中兴 锁屏加速受保护应用
    protected val ZTE_GOD = 117

    protected var sIntentWrapperList: MutableList<IntentWrapper>? = null

    fun getIntentWrapperList(): List<IntentWrapper> {
        if (sIntentWrapperList == null) {

            if (!DaemonEnv.sInitialized) return ArrayList()

            sIntentWrapperList = ArrayList()

            //Android 7.0+ Doze 模式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val pm = DaemonEnv.sApp!!.getSystemService(Context.POWER_SERVICE) as PowerManager
                val ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(DaemonEnv.sApp!!.getPackageName())
                if (!ignoringBatteryOptimizations) {
                    val dozeIntent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                    dozeIntent.data = Uri.parse("package:" + DaemonEnv.sApp!!.getPackageName())
                    sIntentWrapperList!!.add(IntentWrapper(dozeIntent, DOZE))
                }
            }

            //华为 自启管理
            val huaweiIntent = Intent()
            huaweiIntent.action = "huawei.intent.action.HSM_BOOTAPP_MANAGER"
            sIntentWrapperList!!.add(IntentWrapper(huaweiIntent, HUAWEI))

            //华为 锁屏清理
            val huaweiGodIntent = Intent()
            huaweiGodIntent.component = ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")
            sIntentWrapperList!!.add(IntentWrapper(huaweiGodIntent, HUAWEI_GOD))

            //小米 自启动管理
            val xiaomiIntent = Intent()
            xiaomiIntent.action = "miui.intent.action.OP_AUTO_START"
            xiaomiIntent.addCategory(Intent.CATEGORY_DEFAULT)
            sIntentWrapperList!!.add(IntentWrapper(xiaomiIntent, XIAOMI))

            //小米 神隐模式
            val xiaomiGodIntent = Intent()
            xiaomiGodIntent.component = ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity")
            xiaomiGodIntent.putExtra("package_name", DaemonEnv.sApp!!.getPackageName())
            xiaomiGodIntent.putExtra("package_label", getApplicationName())
            sIntentWrapperList!!.add(IntentWrapper(xiaomiGodIntent, XIAOMI_GOD))

            //三星 5.0/5.1 自启动应用程序管理
            val samsungLIntent = DaemonEnv.sApp!!.getPackageManager().getLaunchIntentForPackage("com.samsung.android.sm")
            if (samsungLIntent != null) sIntentWrapperList!!.add(IntentWrapper(samsungLIntent, SAMSUNG_L))

            //三星 6.0+ 未监视的应用程序管理
            val samsungMIntent = Intent()
            samsungMIntent.component = ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.BatteryActivity")
            sIntentWrapperList!!.add(IntentWrapper(samsungMIntent, SAMSUNG_M))

            //魅族 自启动管理
            val meizuIntent = Intent("com.meizu.safe.security.SHOW_APPSEC")
            meizuIntent.addCategory(Intent.CATEGORY_DEFAULT)
            meizuIntent.putExtra("packageName", DaemonEnv.sApp!!.getPackageName())
            sIntentWrapperList!!.add(IntentWrapper(meizuIntent, MEIZU))

            //魅族 待机耗电管理
            val meizuGodIntent = Intent()
            meizuGodIntent.component = ComponentName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity")
            sIntentWrapperList!!.add(IntentWrapper(meizuGodIntent, MEIZU_GOD))

            //Oppo 自启动管理
            val oppoIntent = Intent()
            oppoIntent.component = ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")
            sIntentWrapperList!!.add(IntentWrapper(oppoIntent, OPPO))

            //Oppo 自启动管理(旧版本系统)
            val oppoOldIntent = Intent()
            oppoOldIntent.component = ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity")
            sIntentWrapperList!!.add(IntentWrapper(oppoOldIntent, OPPO_OLD))

            //Vivo 后台高耗电
            val vivoGodIntent = Intent()
            vivoGodIntent.component = ComponentName("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity")
            sIntentWrapperList!!.add(IntentWrapper(vivoGodIntent, VIVO_GOD))

            //金立 应用自启
            val gioneeIntent = Intent()
            gioneeIntent.component = ComponentName("com.gionee.softmanager", "com.gionee.softmanager.MainActivity")
            sIntentWrapperList!!.add(IntentWrapper(gioneeIntent, GIONEE))

            //乐视 自启动管理
            val letvIntent = Intent()
            letvIntent.component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")
            sIntentWrapperList!!.add(IntentWrapper(letvIntent, LETV))

            //乐视 应用保护
            val letvGodIntent = Intent()
            letvGodIntent.component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity")
            sIntentWrapperList!!.add(IntentWrapper(letvGodIntent, LETV_GOD))

            //酷派 自启动管理
            val coolpadIntent = Intent()
            coolpadIntent.component = ComponentName("com.yulong.android.security", "com.yulong.android.seccenter.tabbarmain")
            sIntentWrapperList!!.add(IntentWrapper(coolpadIntent, COOLPAD))

            //联想 后台管理
            val lenovoIntent = Intent()
            lenovoIntent.component = ComponentName("com.lenovo.security", "com.lenovo.security.purebackground.PureBackgroundActivity")
            sIntentWrapperList!!.add(IntentWrapper(lenovoIntent, LENOVO))

            //联想 后台耗电优化
            val lenovoGodIntent = Intent()
            lenovoGodIntent.component = ComponentName("com.lenovo.powersetting", "com.lenovo.powersetting.ui.Settings\$HighPowerApplicationsActivity")
            sIntentWrapperList!!.add(IntentWrapper(lenovoGodIntent, LENOVO_GOD))

            //中兴 自启管理
            val zteIntent = Intent()
            zteIntent.component = ComponentName("com.zte.heartyservice", "com.zte.heartyservice.autorun.AppAutoRunManager")
            sIntentWrapperList!!.add(IntentWrapper(zteIntent, ZTE))

            //中兴 锁屏加速受保护应用
            val zteGodIntent = Intent()
            zteGodIntent.component = ComponentName("com.zte.heartyservice", "com.zte.heartyservice.setting.ClearAppSettingsActivity")
            sIntentWrapperList!!.add(IntentWrapper(zteGodIntent, ZTE_GOD))
        }
        return sIntentWrapperList!!
    }

    protected var sApplicationName: String? = null

    fun getApplicationName(): String? {
        if (sApplicationName == null) {
            if (!DaemonEnv.sInitialized) return ""
            val pm: PackageManager
            val ai: ApplicationInfo
            try {
                pm = DaemonEnv.sApp!!.getPackageManager()
                ai = pm.getApplicationInfo(DaemonEnv.sApp!!.getPackageName(), 0)
                sApplicationName = pm.getApplicationLabel(ai).toString()
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                sApplicationName = DaemonEnv.sApp!!.getPackageName()
            }

        }
        return sApplicationName
    }

    /**
     * 处理白名单.
     * @return 弹过框的 IntentWrapper.
     */
    fun whiteListMatters(a: Activity, reason: String?): List<IntentWrapper> {
        var s = reason
        val showed = ArrayList<IntentWrapper>()
        if (s == null) s = "核心服务的持续运行"
        val intentWrapperList = getIntentWrapperList()
        loop@ for (iw in intentWrapperList) {
            //如果本机上没有能处理这个Intent的Activity，说明不是对应的机型，直接忽略进入下一次循环。
            if (!iw.doesActivityExists()) continue
            when (iw.type) {
                DOZE -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val pm = a.getSystemService(Context.POWER_SERVICE) as PowerManager
                    if (pm.isIgnoringBatteryOptimizations(a.packageName)) break@loop
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要忽略 " + getApplicationName() + " 的电池优化")
                            .setMessage(s + "需要 " + getApplicationName() + " 加入到电池优化的忽略名单。\n\n" +
                                    "请点击『确定』，在弹出的『忽略电池优化』对话框中，选择『是』。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                HUAWEI -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 自动启动")
                            .setMessage(s + "需要允许 " + getApplicationName() + " 的自动启动。\n\n" +
                                    "请点击『确定』，在弹出的『自启管理』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                ZTE_GOD, HUAWEI_GOD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(getApplicationName()!! + " 需要加入锁屏清理白名单")
                            .setMessage(s + "需要 " + getApplicationName() + " 加入到锁屏清理白名单。\n\n" +
                                    "请点击『确定』，在弹出的『锁屏清理』列表中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                XIAOMI_GOD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要关闭 " + getApplicationName() + " 的神隐模式")
                            .setMessage(s + "需要关闭 " + getApplicationName() + " 的神隐模式。\n\n" +
                                    "请点击『确定』，在弹出的 " + getApplicationName() + " 神隐模式设置中，选择『无限制』，然后选择『允许定位』。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                SAMSUNG_L -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage(s + "需要 " + getApplicationName() + " 在屏幕关闭时继续运行。\n\n" +
                                    "请点击『确定』，在弹出的『智能管理器』中，点击『内存』，选择『自启动应用程序』选项卡，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                SAMSUNG_M -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage(s + "需要 " + getApplicationName() + " 在屏幕关闭时继续运行。\n\n" +
                                    "请点击『确定』，在弹出的『电池』页面中，点击『未监视的应用程序』->『添加应用程序』，勾选 " + getApplicationName() + "，然后点击『完成』。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                MEIZU -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 保持后台运行")
                            .setMessage(s + "需要允许 " + getApplicationName() + " 保持后台运行。\n\n" +
                                    "请点击『确定』，在弹出的应用信息界面中，将『后台管理』选项更改为『保持后台运行』。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                MEIZU_GOD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(getApplicationName()!! + " 需要在待机时保持运行")
                            .setMessage(s + "需要 " + getApplicationName() + " 在待机时保持运行。\n\n" +
                                    "请点击『确定』，在弹出的『待机耗电管理』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                ZTE, LETV, XIAOMI, OPPO, OPPO_OLD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage(s + "需要 " + getApplicationName() + " 加入到自启动白名单。\n\n" +
                                    "请点击『确定』，在弹出的『自启动管理』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                COOLPAD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage(s + "需要允许 " + getApplicationName() + " 的自启动。\n\n" +
                                    "请点击『确定』，在弹出的『酷管家』中，找到『软件管理』->『自启动管理』，取消勾选 " + getApplicationName() + "，将 " + getApplicationName() + " 的状态改为『已允许』。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                VIVO_GOD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 的后台运行")
                            .setMessage(s + "需要允许 " + getApplicationName() + " 在后台高耗电时运行。\n\n" +
                                    "请点击『确定』，在弹出的『后台高耗电』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                GIONEE -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(getApplicationName()!! + " 需要加入应用自启和绿色后台白名单")
                            .setMessage(s + "需要允许 " + getApplicationName() + " 的自启动和后台运行。\n\n" +
                                    "请点击『确定』，在弹出的『系统管家』中，分别找到『应用管理』->『应用自启』和『绿色后台』->『清理白名单』，将 " + getApplicationName() + " 添加到白名单。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                LETV_GOD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要禁止 " + getApplicationName() + " 被自动清理")
                            .setMessage(s + "需要禁止 " + getApplicationName() + " 被自动清理。\n\n" +
                                    "请点击『确定』，在弹出的『应用保护』中，将 " + getApplicationName() + " 对应的开关关闭。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                LENOVO -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要允许 " + getApplicationName() + " 的后台运行")
                            .setMessage(s + "需要允许 " + getApplicationName() + " 的后台自启、后台 GPS 和后台运行。\n\n" +
                                    "请点击『确定』，在弹出的『后台管理』中，分别找到『后台自启』、『后台 GPS』和『后台运行』，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
                LENOVO_GOD -> {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle("需要关闭 " + getApplicationName() + " 的后台耗电优化")
                            .setMessage(s + "需要关闭 " + getApplicationName() + " 的后台耗电优化。\n\n" +
                                    "请点击『确定』，在弹出的『后台耗电优化』中，将 " + getApplicationName() + " 对应的开关关闭。")
                            .setPositiveButton("确定") { d, w -> iw.startActivitySafely(a) }
                            .show()
                    showed.add(iw)
                }
            }
        }
        return showed
    }

    /**
     * 防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀
     */
    fun onBackPressed(a: Activity) {
        val launcherIntent = Intent(Intent.ACTION_MAIN)
        launcherIntent.addCategory(Intent.CATEGORY_HOME)
        a.startActivity(launcherIntent)
    }

    /**
     * 判断本机上是否有能处理当前Intent的Activity
     */
    protected fun doesActivityExists(): Boolean {
        if (!DaemonEnv.sInitialized) return false
        val pm = DaemonEnv.sApp!!.packageManager
        val list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list != null && list.size > 0
    }

    /**
     * 安全地启动一个Activity
     */
    protected fun startActivitySafely(activityContext: Activity) {
        try {
            activityContext.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
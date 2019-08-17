package com.link.librarymodule.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import android.os.Process
import android.text.TextUtils
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader


class Utils private constructor() {


    companion object {

        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        /**
         * 初始化工具类
         *
         * @param context 上下文
         */
        @JvmStatic
        fun init(context: Context) {
            this.context = context
        }

        /**
         * 获取ApplicationContext
         *
         * @return ApplicationContext
         */
        @JvmStatic
        fun getContext(): Context {
            if (context != null) {
                return context!!
            }
            throw NullPointerException("should be initialized in application")
        }

        private var sCurProcessName: String? = null

        @JvmStatic
        fun isMainProcess(context: Context): Boolean {
            val processName = getCurProcessName(context)
            return if (processName != null && processName.contains(":")) {
                false
            } else processName != null && processName == context.packageName
        }

        @JvmStatic
        fun isNetworkConnected(context: Context?): Boolean {
            if (context != null) {
                // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
                val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                // 获取NetworkInfo对象
                val networkInfo = manager.activeNetworkInfo
                //判断NetworkInfo对象是否为空
                if (networkInfo != null)
                    return networkInfo.isAvailable
            }
            return false
        }

        @JvmStatic
        fun getCurProcessName(context: Context): String? {
            val procName = sCurProcessName
            if (!TextUtils.isEmpty(procName)) {
                return procName
            }
            try {
                val pid = Process.myPid()
                val mActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                for (appProcess in mActivityManager.runningAppProcesses) {
                    if (appProcess.pid == pid) {
                        sCurProcessName = appProcess.processName
                        return sCurProcessName
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            sCurProcessName = getCurProcessNameFromProc()
            return sCurProcessName
        }

        @JvmStatic
        private fun getCurProcessNameFromProc(): String? {
            var cmdlineReader: BufferedReader? = null
            try {
                cmdlineReader = BufferedReader(InputStreamReader(
                        FileInputStream(
                                "/proc/" + Process.myPid() + "/cmdline"),
                        "iso-8859-1"))
                val c: Int = cmdlineReader.read()
                val processName = StringBuilder()
                while (c> 0) {
                    processName.append(c.toChar())
                }
                return processName.toString()
            } catch (e: Throwable) {
                // ignore
            } finally {
                if (cmdlineReader != null) {
                    try {
                        cmdlineReader.close()
                    } catch (e: Exception) {
                        // ignore
                    }

                }
            }
            return null
        }
    }


}
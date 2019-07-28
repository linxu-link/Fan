package com.link.librarymodule.utils

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by goldze on 2017/5/14.
 * 常用工具类
 */
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
            Companion.context = context.applicationContext
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
    }


}
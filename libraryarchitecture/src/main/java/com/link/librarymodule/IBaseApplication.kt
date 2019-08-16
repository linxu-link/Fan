package com.link.librarymodule

import android.app.Application

interface IBaseApplication {

    /**
     * Application 初始化
     */
     fun initModuleApp(application: Application)

    /**
     * 所有 Application 初始化后的自定义操作
     */
     fun initModuleData(application: Application)
}
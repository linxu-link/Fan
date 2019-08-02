package com.link.component_search

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.link.component_search.data.source.local.SearchDataBase
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.BaseApplication
import androidx.sqlite.db.SupportSQLiteDatabase
import com.link.librarycomponent.FanApplication


class SearchApplication : FanApplication() {


    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initModuleData(this)
    }

    override fun initModuleApp(application: Application) {


    }



    override fun initModuleData(application: Application) {


    }


}
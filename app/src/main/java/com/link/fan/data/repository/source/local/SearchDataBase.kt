package com.link.fan.data.repository.source.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.link.fan.app.search.HistoryEntity
import com.link.librarymodule.utils.ApplicationUtil

const val DATABASE_NAME = "searchBase"

@Database(entities = [HistoryEntity::class], version = 1,exportSchema = true)
abstract class SearchDataBase : RoomDatabase() {

    abstract fun getDao(): SearchHistoryDao

    companion object {

        private var mCacheDataBase: SearchDataBase = Room
                .databaseBuilder(ApplicationUtil.getApplication(), SearchDataBase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()

        fun get(): SearchDataBase {
            return mCacheDataBase
        }

    }

}

package com.link.librarynetwork.cache

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.link.librarynetwork.ApplicationUtil

const val cacheName = "link_cache"

@Database(entities = [Cache::class], version = 1, exportSchema = true)
abstract class CacheDataBase : RoomDatabase() {

    abstract fun getDao(): CacheDao

    companion object {

        private var mCacheDataBase: CacheDataBase = Room
                .databaseBuilder(ApplicationUtil.getApplication(), CacheDataBase::class.java, cacheName)
                .allowMainThreadQueries()
                .build()

        fun get(): CacheDataBase {
            return mCacheDataBase
        }
    }


}
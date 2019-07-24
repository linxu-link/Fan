package com.link.component_search.data.source.local

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase

import com.link.component_search.data.entity.HistoryEntity

const val DATABASE_NAME = "fan_room.db"

@Database(entities = [HistoryEntity::class], version = 1)
abstract class SearchDataBase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        @Volatile
        private var instance: SearchDataBase? = null

        fun getInstance(context: Context): SearchDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): SearchDataBase {
            return Room.databaseBuilder(context, SearchDataBase::class.java, DATABASE_NAME)
                    .build()
        }
    }

}

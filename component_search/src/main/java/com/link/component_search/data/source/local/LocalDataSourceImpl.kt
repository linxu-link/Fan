package com.link.component_search.data.source.local

import com.link.component_search.data.entity.HistoryEntity
import com.link.librarybase.Utils
import io.reactivex.Flowable

class LocalDataSourceImpl constructor(val searchDataBase: SearchDataBase = SearchDataBase.getInstance(Utils.getContext())) : ILocalDataSource {


    override fun getSearchHistory(): Flowable<List<HistoryEntity>> {
        return searchDataBase.searchHistoryDao().getHistory()
    }

    override fun insertSearchData(searchWord: HistoryEntity) {
        searchDataBase.searchHistoryDao().insert(searchWord)
    }

    override fun clearSearchHistory(list: List<HistoryEntity>) {
        searchDataBase.searchHistoryDao().deleteAll(list)
    }


    //    /**
//     * 数据库版本 1->2 user表格新增了age列
//     */
//    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE User ADD COLUMN age integer")
//        }
//    }
//
//    /**
//     * 数据库版本 2->3 新增book表格
//     */
//    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)")
//        }
//    }


}
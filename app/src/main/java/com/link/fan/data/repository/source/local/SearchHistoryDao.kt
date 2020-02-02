package com.link.fan.data.repository.source.local

import androidx.room.*
import com.link.fan.app.search.HistoryEntity
import io.reactivex.Flowable

@Dao
interface SearchHistoryDao {

    @Query("select * from search_history")
    fun getHistory(): Flowable<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchWord: HistoryEntity)

    @Delete
    fun deleteAll(list: List<HistoryEntity>)


}
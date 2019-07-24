package com.link.component_search.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.link.component_search.data.entity.HistoryEntity
import io.reactivex.Flowable

@Dao
interface SearchHistoryDao {

    @Query("select * from search_history")
    fun getHistory(): Flowable<List<HistoryEntity>>

    @Insert
    fun insert(searchWord: HistoryEntity)

    @Delete
    fun deleteAll(list: List<HistoryEntity>)


}
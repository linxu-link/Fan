package com.link.component_search.data.source.local

import com.link.component_search.data.entity.HistoryEntity
import io.reactivex.Flowable

interface ILocalDataSource {

    /**
     * 获取搜索历史
     */
    fun getSearchHistory(): Flowable<List<HistoryEntity>>

    /**
     * 插入搜索历史
     */
    fun insertSearchData(searchWord: HistoryEntity)

    /**
     * 清空搜索历史
     */
    fun clearSearchHistory(list: List<HistoryEntity>)


}
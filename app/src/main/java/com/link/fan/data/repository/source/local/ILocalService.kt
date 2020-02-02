package com.link.fan.data.repository.source.local

import com.link.fan.app.search.HistoryEntity
import io.reactivex.Flowable

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-18:52
 * email:wujia0916@thundersoft.com
 * description:  定义本地数据源的操作
 */
interface ILocalService {

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
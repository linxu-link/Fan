package com.link.fan.data.repository.source.local

import com.link.fan.data.bean.HistoryEntity
import io.reactivex.Flowable

class LocalServiceImpl constructor(val searchDataBase: SearchDataBase = SearchDataBase.get()) : ILocalService {

    companion object {
        @Volatile
        private var instance: LocalServiceImpl? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: LocalServiceImpl().also {
                        instance = it
                    }
                }
    }

    override fun getSearchHistory(): Flowable<List<HistoryEntity>> {
        return searchDataBase.getDao().getHistory()
    }

    override fun insertSearchData(searchWord: HistoryEntity) {
        searchDataBase.getDao().insert(searchWord)
    }

    override fun clearSearchHistory(list: List<HistoryEntity>) {
        searchDataBase.getDao().deleteAll(list)
    }
}
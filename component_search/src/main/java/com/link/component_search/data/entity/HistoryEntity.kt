package com.link.component_search.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
class HistoryEntity {

    @PrimaryKey
    @ColumnInfo(name = "content")
    lateinit var content: String

}
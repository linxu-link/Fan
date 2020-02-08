package com.link.fan.data.bean

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
class HistoryEntity {

    @JvmField
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0

    @ColumnInfo(name = "content")
    var content: String? = null
}
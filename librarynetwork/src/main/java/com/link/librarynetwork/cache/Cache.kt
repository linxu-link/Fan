package com.link.librarynetwork.cache

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cache")
class Cache : Serializable {
    //PrimaryKey 必须要有,且不为空,autoGenerate 主键的值是否由Room自动生成,默认false
    @JvmField
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var key: String? = null
    //@ColumnInfo(name = "_data"),指定该字段在表中的列的名字
    @JvmField
    var data: ByteArray? = null

    //@Embedded 对象嵌套,ForeignTable对象中所有字段 也都会被映射到cache表中,
    //同时也支持ForeignTable 内部还有嵌套对象
    //public ForeignTable foreignTable;
}
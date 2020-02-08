package com.link.fan.data.bean

data class BaseEntity<T>(
        val status: Int,
        val msg: String,
        val result: T,
        val data: T
)
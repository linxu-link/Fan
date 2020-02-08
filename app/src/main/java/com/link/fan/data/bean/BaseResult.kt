package com.link.fan.data.bean

data class BaseResult<T>(
        val total: Int,
        val num: Int,
        val list: T,
        val data: T
)
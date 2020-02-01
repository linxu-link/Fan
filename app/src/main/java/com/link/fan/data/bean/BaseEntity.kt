package com.link.fan.data.bean

data class BaseEntity<T>(
        val status: Int,
        val message: String,
        var data: T)
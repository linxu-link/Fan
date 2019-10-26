package com.link.fan.data.bean

data class BaseEntity<T>(val resultcode: String, val reason: String,
                         val error_code: Int, var result: T) {

}
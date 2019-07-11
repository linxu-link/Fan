package com.link.librarycomponent.entity.base

data class BaseEntity<T>(val resultcode: String, val reason: String,
                         val error_code: Int, var result: T) {

}
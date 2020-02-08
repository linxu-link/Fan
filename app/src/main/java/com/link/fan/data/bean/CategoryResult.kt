package com.link.fan.data.bean

data class CategoryResult(
        var click: Boolean = false,
        val parentid: String,
        val name: String,
        val list: List<CategoryDetail>)
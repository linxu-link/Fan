package com.link.fan.data.bean

data class CategoryResult(
        var click: Boolean = false,
        val parentId: String,
        val name: String,
        val list: List<CategoryDetail>)
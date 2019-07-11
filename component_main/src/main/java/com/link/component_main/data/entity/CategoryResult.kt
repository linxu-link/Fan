package com.link.component_main.data.entity

data class CategoryResult(var click: Boolean=false, val parentId: String,
                          val name: String, val list: List<CategoryDetail>) {
}
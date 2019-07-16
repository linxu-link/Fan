package com.link.component_search.data.entity

import com.link.component_search.data.entity.CategoryDetail

data class CategoryResult(var click: Boolean=false, val parentId: String,
                          val name: String, val list: List<CategoryDetail>) {
}
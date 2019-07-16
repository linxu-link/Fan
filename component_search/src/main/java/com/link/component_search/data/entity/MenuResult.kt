package com.link.component_search.data.entity

import com.link.component_search.data.entity.MenuDetail

data class MenuResult(val totalNum:String, val pn:String, val rn:String, val data:List<MenuDetail>) {
}
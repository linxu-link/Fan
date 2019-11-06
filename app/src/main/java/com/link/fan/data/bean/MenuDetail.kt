package com.link.fan.data.bean

data class MenuDetail(
        val id: String,
        val title: String,
        val tags: String,
        val imtro: String,
        val ingredients: String,
        val burden: String,
        val albums: List<String>,
        val steps: List<StepsBean>)
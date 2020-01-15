package com.link.fan.navigation

data class Destination(
        var isFragment: Boolean,
        var asStarter: Boolean,
        var needLogin: Boolean,
        var pageUrl: String,
        var className: String,
        var id: Int
)
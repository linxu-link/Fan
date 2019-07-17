package com.link.component_update.data.entity

import cn.bmob.v3.BmobObject

data class UpdateEntity(val versionCode:String,
                        val updateContent:String,
                        val forceUpdate:Boolean,
                        val downloadUrl:String):BmobObject() {
}
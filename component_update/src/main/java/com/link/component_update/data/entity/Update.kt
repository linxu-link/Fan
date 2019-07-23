package com.link.component_update.data.entity

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile

data class Update(var versionCode:String,
                  var updateContent:String,
                  var forceUpdate:Boolean,
                  var apk:BmobFile): BmobObject() {
}
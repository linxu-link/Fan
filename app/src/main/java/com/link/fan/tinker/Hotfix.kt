package com.link.fan.tinker

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile

data class Hotfix(var versionName: String,
                  var patchCode: String,
                  var patchMD5: String,
                  var patch: BmobFile) : BmobObject() {
}
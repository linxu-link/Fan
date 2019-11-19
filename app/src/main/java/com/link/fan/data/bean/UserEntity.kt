package com.link.fan.data.bean

import cn.bmob.v3.BmobUser


class UserEntity : BmobUser() {

    var introduction: String? = null

    var displayName: String? = null

    var avatar: String? = null
}
package com.link.librarycomponent.entity.user

import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile

class UserEntity : BmobUser() {

    var pwd: String? = null

    var avatar: BmobFile? = null

    var introduction: String? = null

    var displayName: String? = null

}
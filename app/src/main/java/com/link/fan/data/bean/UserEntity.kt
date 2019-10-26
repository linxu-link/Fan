package com.link.fan.data.bean

import cn.bmob.v3.BmobInstallation
import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile


class UserEntity : BmobUser() {


    var pwd: String? = null


    var avatar: BmobFile? = null


    var introduction: String? = null


    var displayName: String? = null


    var device: BmobInstallation? = null

    //用户使用的设备
    class Installation : BmobInstallation() {
        var deviceOS: String? = null
    }

}
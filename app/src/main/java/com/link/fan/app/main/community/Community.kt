package com.link.fan.app.main.community

import cn.bmob.v3.BmobObject
import cn.bmob.v3.datatype.BmobFile
import com.link.fan.data.bean.UserEntity

class Community : BmobObject() {

    var content: String? = null
    var title: String? = null
    var author: UserEntity? = null
    var img: BmobFile? = null
    var type: Int? = null

}
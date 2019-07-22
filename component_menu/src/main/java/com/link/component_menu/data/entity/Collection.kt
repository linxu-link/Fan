package com.link.component_menu.data.entity

import cn.bmob.v3.BmobObject
import java.io.Serializable

class Collection : BmobObject() {

    var userId: String? = null
    var id: String? = null
    var title: String? = null
    var tags: String? = null
    var imtro: String? = null
    var ingredients: String? = null
    var burden: String? = null
    var albums: List<String>? = null
    var steps: List<MenuDetail.StepsBean>? = null
//    var album: List<String>? = null
//    var step: List<MenuDetail.StepsBean>? = null

    class StepsBean : Serializable {
        /**
         * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_3adcc38469c97069.jpg
         * step : 1.用烤纸叠好28cmX28cm的方盒，还可以叠的再深一点哦!后面会比较方便.低粉和可可粉混合过筛。蛋黄和蛋白分开
         */
        var img: String? = null
        var step: String? = null
    }
}
package com.link.component_user.data.entity

import cn.bmob.v3.BmobObject
import java.io.Serializable

data class Collection(val id: String,
                      val title: String,
                      val tags: String,
                      val imtro: String,
                      val ingredients: String,
                      val burden: String,
                      val albums: String,
                      val steps: String,
                      var album: List<String>,
                      var step: List<StepsBean>
) : BmobObject() {

    class StepsBean : Serializable {
        /**
         * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_3adcc38469c97069.jpg
         * step : 1.用烤纸叠好28cmX28cm的方盒，还可以叠的再深一点哦!后面会比较方便.低粉和可可粉混合过筛。蛋黄和蛋白分开
         */
        var img: String? = null
        var step: String? = null
    }
}
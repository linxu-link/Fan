package com.link.component_main.ui.main.recommend.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
/**
 * copyright:TS
 * author:wujia
 * create:2019-11-06-17:08
 * email:wujia0916@thundersoft.com
 * description:
 */
class RecommendAdapter(layoutResId: Int, data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: String?) {
        helper.setText(R.id.name, item)
    }


}
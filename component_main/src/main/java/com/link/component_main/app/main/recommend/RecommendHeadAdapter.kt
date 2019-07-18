package com.link.component_main.app.main.recommend

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
import com.link.component_main.data.entity.MenuDetail

class RecommendHeadAdapter(layoutResId: Int, data: MutableList<MenuDetail>?) : BaseQuickAdapter<MenuDetail, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: MenuDetail) {
        helper.setText(R.id.name,item.title)
    }

}
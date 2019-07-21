package com.link.component_search.app.detail

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_search.R
import com.link.component_search.data.entity.MenuDetail

class SearchDetailAdapter(layoutResId: Int, data: List<MenuDetail>?): BaseQuickAdapter<MenuDetail, BaseViewHolder>(layoutResId,data) {
    override fun convert(helper: BaseViewHolder?, item: MenuDetail) {
        helper!!.setText(R.id.name,item.title)
        Glide.with(mContext).load(item.albums!![0]).into(helper.getView(R.id.cover))
    }
}
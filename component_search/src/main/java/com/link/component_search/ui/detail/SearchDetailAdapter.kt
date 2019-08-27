package com.link.component_search.ui.detail

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_search.R
import com.link.component_search.data.entity.MenuDetail
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy

class SearchDetailAdapter(layoutResId: Int, data: List<MenuDetail>?) : BaseQuickAdapter<MenuDetail, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: MenuDetail) {
        helper!!.setText(R.id.name, item.title)
        ImageLoader.getInstance().with(mContext).load(item.albums!![0]).build(GlideStrategy()).into(helper.getView(R.id.cover))
    }
}
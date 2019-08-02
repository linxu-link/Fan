package com.link.component_main.ui.main.recommend.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
import com.link.component_main.data.entity.MenuDetail

class RecommendHeadAdapter(layoutResId: Int, data: MutableList<MenuDetail>?) : BaseQuickAdapter<MenuDetail, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: MenuDetail) {
        helper.setText(R.id.name,item.title)
        Glide.with(mContext).load(item.albums[0]).into(helper.getView(R.id.img) as ImageView)
    }

}
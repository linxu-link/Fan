package com.link.component_user.ui.footprint

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_user.R
import com.link.component_user.data.entity.FootPrint
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy

class FootPrintAdapter(layoutId: Int, data: List<FootPrint>?)
    : BaseQuickAdapter<FootPrint, BaseViewHolder>(layoutId, data) {


    override fun convert(helper: BaseViewHolder, item: FootPrint?) {
        helper.setText(R.id.name, item!!.title)
        ImageLoader.getInstance().with(mContext).load(item.albums!![0]).build(GlideStrategy()).into(helper.getView(R.id.cover))
    }


}
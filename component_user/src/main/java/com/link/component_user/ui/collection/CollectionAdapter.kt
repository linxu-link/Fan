package com.link.component_user.ui.collection

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_user.R
import com.link.component_user.data.entity.Collection
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy

class CollectionAdapter(layoutId: Int, data: List<Collection>?)
    : BaseQuickAdapter<Collection, BaseViewHolder>(layoutId, data) {


    override fun convert(helper: BaseViewHolder, item: Collection?) {
        helper.setText(R.id.name, item!!.title)
        ImageLoader.getInstance().with(mContext).load(item.albums!![0]).build(GlideStrategy()).into(helper.getView(R.id.cover))
    }


}
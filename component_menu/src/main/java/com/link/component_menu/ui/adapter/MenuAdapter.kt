package com.link.component_menu.ui.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_menu.R
import com.link.component_menu.data.entity.StepsBean
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy

class MenuAdapter(layoutId: Int, data: List<StepsBean>?) : BaseQuickAdapter<StepsBean, BaseViewHolder>(layoutId, data) {

    override fun convert(helper: BaseViewHolder, item: StepsBean?) {
        helper.setText(R.id.content, item!!.step)
        ImageLoader.getInstance().with(mContext).load(item.img).build(GlideStrategy()).into(helper.getView(R.id.img))
        helper.setText(R.id.position, "步骤${helper.adapterPosition}")
    }

}
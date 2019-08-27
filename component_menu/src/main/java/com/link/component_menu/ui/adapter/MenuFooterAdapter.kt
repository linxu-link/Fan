package com.link.component_menu.ui.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_menu.R
import com.link.component_menu.data.entity.MenuDetail
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy

class MenuFooterAdapter(layoutId:Int,data:List<MenuDetail>?):BaseQuickAdapter<MenuDetail,BaseViewHolder>(layoutId,data) {

    override fun convert(helper: BaseViewHolder, item: MenuDetail?) {
        helper.setText(R.id.name,item!!.title)
        ImageLoader.getInstance().with(mContext).load(item.albums[0]).build(GlideStrategy()).into(helper.getView(R.id.img))
    }
}
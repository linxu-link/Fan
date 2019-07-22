package com.link.component_menu.app

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_menu.R
import com.link.component_menu.data.entity.MenuDetail

class MenuFooterAdapter(layoutId:Int,data:List<MenuDetail>?):BaseQuickAdapter<MenuDetail,BaseViewHolder>(layoutId,data) {

    override fun convert(helper: BaseViewHolder, item: MenuDetail?) {
        helper.setText(R.id.name,item!!.title)
        Glide.with(mContext).load(item.albums[0]).into(helper.getView(R.id.img))
    }
}
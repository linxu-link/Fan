package com.link.component_menu.app

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_menu.R
import com.link.component_menu.data.entity.MenuDetail
import com.link.component_menu.data.entity.StepsBean
import kotlinx.android.synthetic.main.menu_item.view.*

class MenuAdapter(layoutId: Int, data: List<StepsBean>?) : BaseQuickAdapter<StepsBean, BaseViewHolder>(layoutId, data) {

    override fun convert(helper: BaseViewHolder, item: StepsBean?) {
        helper.setText(R.id.content, item!!.step)
        Glide.with(mContext).load(item.img).into(helper.getView(R.id.img))
        helper.setText(R.id.position, "步骤${helper.adapterPosition}")
    }

}
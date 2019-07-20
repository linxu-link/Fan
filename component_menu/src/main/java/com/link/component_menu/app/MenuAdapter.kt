package com.link.component_menu.app

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_menu.data.entity.MenuDetail

class MenuAdapter(layoutId: Int, data: List<MenuDetail.StepsBean>?) : BaseQuickAdapter<MenuDetail.StepsBean, BaseViewHolder>(layoutId, data) {

    override fun convert(helper: BaseViewHolder?, item: MenuDetail.StepsBean) {

    }

}
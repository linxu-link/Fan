package com.link.fan.app.menu.catalog

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.fan.R
import com.link.fan.data.bean.CategoryDetail

class RightCatalogAdapter(layoutResId: Int, data: MutableList<CategoryDetail>?) : BaseQuickAdapter<CategoryDetail, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CategoryDetail) {
        helper.setText(R.id.tv_catalog_name, item.name)
    }
}

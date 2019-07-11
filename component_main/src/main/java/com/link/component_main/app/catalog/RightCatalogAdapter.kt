package com.link.component_main.app.catalog

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
import com.link.component_main.data.entity.CategoryDetail
import com.link.component_main.data.entity.CategoryResult

class RightCatalogAdapter(layoutResId: Int, data: MutableList<CategoryDetail>?) : BaseQuickAdapter<CategoryDetail, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CategoryDetail) {
        helper.setText(R.id.tv_catalog_name, item.name)
    }
}

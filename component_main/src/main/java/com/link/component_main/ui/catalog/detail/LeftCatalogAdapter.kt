package com.link.component_main.ui.catalog.detail

import android.graphics.Typeface
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_main.R
import com.link.component_main.data.entity.CategoryResult

class LeftCatalogAdapter(layoutResId: Int, data: MutableList<CategoryResult>?) : BaseQuickAdapter<CategoryResult, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CategoryResult) {
        helper.setText(R.id.tv_catalog_name, item.name)
        helper.setVisible(R.id.line,item.click)
        if (item.click) {
            helper.setBackgroundColor(R.id.root_view, mContext.resources.getColor(android.R.color.white))
            helper.getView<TextView>(R.id.tv_catalog_name).setTextColor(mContext.resources.getColor(R.color.colorPrimary))
            helper.getView<TextView>(R.id.tv_catalog_name).setTypeface(Typeface.DEFAULT_BOLD)
        } else {
            helper.setBackgroundColor(R.id.root_view, mContext.resources.getColor(R.color.grey_light))
            helper.getView<TextView>(R.id.tv_catalog_name).setTextColor(mContext.resources.getColor(R.color.font_color))
            helper.getView<TextView>(R.id.tv_catalog_name).setTypeface(Typeface.DEFAULT)
        }
    }

}

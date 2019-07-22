package com.link.component_search.app.search

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.component_search.R

class SearchAdapter(layoutResId: Int, data: MutableList<String>?): BaseQuickAdapter<String, BaseViewHolder>(layoutResId,data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper!!.setText(android.R.id.text1,item!!)
        helper.setTextColor(android.R.id.text1,ContextCompat.getColor(mContext,R.color.text_light))
    }
}
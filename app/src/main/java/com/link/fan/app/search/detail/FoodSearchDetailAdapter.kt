package com.link.fan.app.search.detail

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.link.fan.R
import com.link.fan.data.bean.JuHeMenuResult
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy

class FoodSearchDetailAdapter(layoutResId: Int, data: List<JuHeMenuResult>?) : BaseQuickAdapter<JuHeMenuResult, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: JuHeMenuResult) {
        helper.setText(R.id.title, item.name)
        helper.setText(R.id.label, item.tag)
        ImageLoader.getInstance().with(mContext)
                .load(item.pic)
                .build(GlideStrategy())
                .into(helper.getView(R.id.iv_cover))

    }
}
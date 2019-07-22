package com.link.component_menu.app

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MenuHeaderAdapter(layoutId:Int, list:List<String>?): BaseQuickAdapter<String,BaseViewHolder>(layoutId,list) {

    override fun convert(helper: BaseViewHolder, item: String?) {
        helper.setText(android.R.id.text1,item)
    }

}
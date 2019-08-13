package com.link.component_shopping.service

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.service.shopping.IShoppingService
import com.link.librarymodule.utils.Utils

/**
 * @author WJ
 * @date 2019-08-13
 *
 * 描述：用于启动真正的shoppingService
 */
@Route(path = RouterConstant.SHOPPING_SERVICE)
class ShoppingService:IShoppingService {

    override fun startShoppingService() {
        val intent = Intent()
        intent.setClass(Utils.getContext(), ShoppingServiceImpl::class.java)
        Utils.getContext().startService(intent)
    }

    override fun init(context: Context?) {

    }


}
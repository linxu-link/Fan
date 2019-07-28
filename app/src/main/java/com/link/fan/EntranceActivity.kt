package com.link.fan

import android.os.Bundle
import androidx.navigation.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.link.fan.tinker.TinkerService
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.Utils
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.service.update.IUpdateService
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.constant.Constant

/**
 * @author WJ
 * @date 2019-07-21
 *
 * 描述：入口activity
 */
@Route(path = RouterConstant.APP)
class EntranceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //开启全屏占用
        fullScreen(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_container)

        if (Constant.BMOB_ID.isEmpty()){
            ToastUtils.showLong("请完善BMOB_ID")
            return
        }else if (Constant.JUHE_KEY.isEmpty()){
            ToastUtils.showLong("请完善聚合数据的key")
            return
        }

        //启动热修复service
        TinkerService.runTinkerService(Utils.getContext())

        //启动更新service
        val updateService = ARouter.getInstance().build(RouterConstant.UPDATE).navigation()!! as IUpdateService
        updateService.startUpdateService()

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container).navigateUp()
    }


}

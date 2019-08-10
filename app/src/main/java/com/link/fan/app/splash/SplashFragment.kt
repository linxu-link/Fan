package com.link.fan.app.splash


import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.link.fan.R
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_splash.*
/**
 * @author WJ
 * @date 2019-07-10
 *
 * 描述：闪屏
 */
class SplashFragment(override var layoutId: Int = R.layout.fragment_splash) : BaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //把文字设为透明，翻转90度
        tv_splash.rotationX = 90f
        tv_splash.alpha = 0f

        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(Consumer {
                    if (it) {
                        startAnimation()
                    } else {
                        ToastUtils.showLong("您已禁止此应用读取存储设备")
                        AppManager.instance.AppExit()
                    }
                })
    }


    private fun startAnimation() {
        splash.animate().setDuration(3500)
                .scaleX(1.8f)
                .scaleY(1.8f)
                .withEndAction {
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.mainFragment)
                }
                .start()

        tv_splash.animate().setDuration(3500)
                .rotationX(0f)
                .alpha(0.8f)
                .start()
    }



}

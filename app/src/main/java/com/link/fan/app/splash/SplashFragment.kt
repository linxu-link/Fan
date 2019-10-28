package com.link.fan.app.splash


import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.os.TraceCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.link.fan.R
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.base.BaseStateFragment
import com.link.librarymodule.utils.RxCountDown
import com.link.librarymodule.utils.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_splash.*

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-28-19:54
 *  email:wujia0916@thundersoft.com
 *  description:  闪屏
 * <pre>
 */
class SplashFragment(override var layoutId: Int = R.layout.fragment_splash) : BaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(Consumer {
                    if (it) {
                        RxCountDown.countdown(4).subscribe(Consumer {
                            if (it == 0) {
                                Navigation.findNavController(activity!!,R.id.root_view).navigate(R.id.action_splashFragment_to_loginFragment)
//                                Navigation.findNavController(activity!!,R.id.root_view).popBackStack()
                            }
                        })
                    } else {
                        ToastUtils.showLong("您已禁止此应用读取存储设备")
                        AppManager.instance.AppExit()
                    }
                })
    }



}

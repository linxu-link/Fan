package com.link.fan.app.splash


import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.link.fan.R
import com.link.fan.databinding.FragmentSplashBinding
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.RxCountDown
import com.link.librarymodule.utils.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-28-19:54
 *  email:wujia0916@thundersoft.com
 *  description:  闪屏
 * <pre>
 */
class SplashFragment : Fragment() {

    private var mDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil
                .inflate<FragmentSplashBinding>(inflater, R.layout.fragment_splash, container, false)
                .apply {

                }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val rxPermissions = RxPermissions(this)

        mDisposable = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(Consumer { it ->
                    if (it) {
                        RxCountDown.countdown(1).subscribe(Consumer {
                            if (it == 0) {
                                Navigation.findNavController(requireActivity(), R.id.root_view).navigate(R.id.action_splashFragment_to_mainFragment)
                            }
                        })
                    } else {
                        ToastUtils.showLong("您已禁止此应用读取存储设备")
                        AppManager.instance.AppExit()
                    }
                })
    }

    override fun onStop() {
        super.onStop()
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }


}

package com.link.librarycomponent.router

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

class StartRouter {

    companion object {

        @JvmStatic
        fun navigation(url: String) {
            ARouter.getInstance()
                    .build(url)
                    .navigation()
        }

        @JvmStatic
        fun navigation(url: String, bundle: Bundle) {
            ARouter.getInstance()
                    .build(url)
                    .with(bundle)
                    .navigation()
        }
    }

}
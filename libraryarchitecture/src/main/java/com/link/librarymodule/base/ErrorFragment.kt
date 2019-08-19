package com.link.librarymodule.base

import android.os.Bundle
import com.link.librarymodule.R

class ErrorFragment(override var layoutId: Int= R.layout.layout_error) : BaseFragment() {

    companion object {
            @JvmStatic
            fun newInstance() =
                ErrorFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
        }

}
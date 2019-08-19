package com.link.general_statelayout.listener

import android.view.View

interface OnShowOrHideListener {
    fun onViewShow(view: View, id: Int)

    fun onViewHide(view: View, id: Int)
}
package com.link.general_statelayout

interface ILayoutState {

    fun isLoading():Boolean

    fun showLoading()

    fun showNetworkError()

    fun showEmptyData(iconImage: Int, textTip: String)

    fun showContent()

    fun showError(iconImage: Int, textTip: String)

}
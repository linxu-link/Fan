package com.link.librarymodule.base.mvvm.view

interface IBaseView {

    /**
     * 接收上一个页面传入的数据
     */
    fun initParam()

    /**
     * 请求网络接口
     */
    fun getData();

    /**
     *
     */
    fun initViewObservable()


    /**
     * 数据载入中
     */
    fun onLoading()

    /**
     * 网络异常
     */
    fun onNetworkError()

    /**
     * 页面无数据
     */
    fun onDataEmpty()

    /**
     * 数据载入成功
     */
    fun onSuccess()

}
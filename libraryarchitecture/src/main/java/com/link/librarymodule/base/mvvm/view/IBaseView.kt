package com.link.librarymodule.base.mvvm.view

interface IBaseView {

    /**
     * 接收上一个页面传入的数据
     */
    fun initParam()

    /**
     * 请求网络接口
     */
    fun loadData();

    /**
     *
     */
    fun initViewObservable()

}
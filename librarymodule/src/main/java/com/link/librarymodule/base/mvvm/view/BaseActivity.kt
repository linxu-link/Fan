package com.link.librarymodule.base.mvvm.view

import androidx.databinding.ViewDataBinding
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>>:RxAppCompatActivity(),IBaseView {
}
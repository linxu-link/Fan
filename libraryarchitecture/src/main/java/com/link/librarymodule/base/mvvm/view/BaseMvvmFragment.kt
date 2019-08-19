package com.link.librarymodule.base.mvvm.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.link.general_statelayout.StateLayoutManager
import com.link.general_statelayout.listener.OnNetworkListener
import com.link.general_statelayout.listener.OnRetryListener
import com.link.librarymodule.base.BaseStateFragment
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.receiver.NetworkConnectChangedReceiver
import java.lang.reflect.ParameterizedType

/**
 * @author WJ
 * @date 2019-05-29
 *
 * 描述：Mvvm架构下Fragment基类
 */
abstract class BaseMvvmFragment<VM : BaseViewModel<*>> : BaseStateFragment(), IBaseView, NetworkConnectChangedReceiver.NetworkChangeListener {

    protected lateinit var mViewModel: VM

    //监听网络状态的广播
    protected var mNetworkReceiver: NetworkConnectChangedReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注册广播
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            mNetworkReceiver = NetworkConnectChangedReceiver(context)
            mNetworkReceiver!!.setNetworkChangeListener(this)
        }
        initViewModel()
        initParam()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //解除ViewModel生命周期感应
        lifecycle.removeObserver(mViewModel)
        mViewModel.removeRxBus()
        //注销广播
        if (mNetworkReceiver != null) {
            mNetworkReceiver!!.unregisterNetworkReceiver(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //私有的初始化Databinding和ViewModel方法
        initView()
        //私有的ViewModel与View的契约事件回调逻辑
        registorUIChangeLiveDataCallBack()
        //页面数据初始化方法
        loadData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        mViewModel.registerRxBus()
    }

    fun initViewModel() {
        mViewModel = getViewModel()
        if (mViewModel == null) {
            val modelClass: Class<*>
            val type = javaClass.genericSuperclass
            if (type is ParameterizedType) {
                modelClass = type.actualTypeArguments[1] as Class<VM>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel::class.java as Class<VM>
            }
            mViewModel = createViewModel(this, modelClass)
        }
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(mViewModel!!)
        //注入RxLifecycle生命周期
        //        mViewModel.injectLifecycleProvider(this);
    }


    /**
     * =====================================================================
     */
    //注册ViewModel与View的契约UI回调事件
    protected fun registorUIChangeLiveDataCallBack() {
        //跳入新页面
        mViewModel.uc.startActivityEvent.observe(this, Observer { params ->
            val clazz = params!![BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
            startActivity(clazz, bundle)
        })
        //跳入ContainerActivity
        mViewModel.uc.startContainerActivityEvent.observe(this, Observer { params ->
            val canonicalName = params!![BaseViewModel.ParameterField.CANONICAL_NAME] as String
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            startContainerActivity(canonicalName, bundle)
        })
        //关闭界面
        mViewModel.uc.finishEvent.observe(this, Observer { mActivity!!.finish() })
        //关闭上一层
        mViewModel.uc.onBackPressedEvent.observe(this, Observer { mActivity!!.onBackPressed() })
    }


    /**
     * =====================================================================
     */

    override fun initParam() {

    }

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    abstract fun getViewModel(): VM


    /**
     *加载数据时，自动展示加载中的页面，对于一些页面包含了下拉刷新的，则需要重写loadData且不能使用super
     */
    override fun loadData() {
        showLoading()
    }

    override fun initViewObservable() {

    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T : ViewModel> createViewModel(fragment: Fragment, cls: Class<T>): T {
        return ViewModelProviders.of(fragment).get(cls)
    }

    /**
     * 结束当前fragment
     */
    fun finish() {
        mActivity!!.finish()
    }

    /**
     * 网络发生变化时回调
     */
    override fun onNetworkChange(isNetConnect: Boolean) {
        if (!isNetConnect) {
            showNetWorkError()
        } else {
            showContent()
            loadData()
        }
    }

    /*=================================状态切换===========================================*/


    override fun initStatusLayout() {
        mStatusLayoutManager = StateLayoutManager.newBuilder(mActivity as Context)
                .contentView(layoutId)
                .emptyDataView(mEmptyLayoutId)
                .errorView(mErrorLayoutId)
                .loadingView(mLoadingLayoutId)
                .networkErrorView(mNetworkErrorLayoutId)
                .onRetryListener(object : OnRetryListener {
                    override fun onRetry() {
                        //点击重试
                        showLoading()
                        loadData()
                    }
                })
                .onNetworkListener(object : OnNetworkListener {
                    override fun onNetwork() {
                        //网络异常，点击重试
                        showLoading()
                        loadData()
                    }
                })
                .build()
    }


}

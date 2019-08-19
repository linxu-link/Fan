package com.link.librarymodule.base.mvvm.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.link.general_statelayout.StateLayoutManager
import com.link.general_statelayout.listener.OnNetworkListener
import com.link.general_statelayout.listener.OnRetryListener
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.base.BaseStateActivity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * @author WJ
 * @date 2019-05-29
 *
 * 描述：Mvvm架构下Activity基类
 */
abstract class BaseMvvmActivity<VM : BaseViewModel<*>> : BaseStateActivity(), IBaseView {

    protected var mViewModel: VM? = null
    abstract var mLayoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //页面接受的参数方法
        initParam()
        //初始化DataBinging和ViewModel
        initViewModel()
        //ViewModel与View的契约事件回掉逻辑
        registorUIChangeLiveDataCallBack()
        //页面事件监听方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        mViewModel!!.registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel!!)
        if (mViewModel != null) {
            mViewModel!!.registerRxBus()
        }
    }


    /**
     * 初始化view
     */
    fun initViewModel() {
        mViewModel = getViewModel()
        if (mViewModel == null) {
            val modelClass: Class<VM>
            val type = javaClass.genericSuperclass
            if (type is ParameterizedType) {
                modelClass = type.actualTypeArguments[1] as Class<VM>
            } else {
                modelClass = BaseViewModel::class.java as Class<VM>
            }
            mViewModel = createViewModel(this, modelClass)
        }
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(mViewModel!!)
    }

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    fun registorUIChangeLiveDataCallBack() {
        //跳入新页面
        mViewModel!!.uc.startActivityEvent.observe(this, Observer { params ->
            val clazz = params!![BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
            startActivity(clazz, bundle)
        })
        //跳入ContainerActivity
        mViewModel!!.uc.startContainerActivityEvent.observe(this, Observer { params ->
            val canonicalName = params!![BaseViewModel.ParameterField.CANONICAL_NAME] as String
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            startContainerActivity(canonicalName, bundle)
        })
        //关闭界面
        mViewModel!!.uc.finishEvent.observe(this, Observer { finish() })
        //关闭上一层
        mViewModel!!.uc.onBackPressedEvent.observe(this, Observer { onBackPressed() })
    }

    /**
     * 获取数据
     */
    override fun loadData() {

    }

    /**
     * 页面接受的参数方法
     */
    override fun initParam() {

    }

    /**
     *  页面事件监听的方法，一般根据ViewModel的数据监听，操作View
     */
    override fun initViewObservable() {

    }

    /**
     * 初始化ViewModel
     */
    abstract fun getViewModel(): VM

    /**
     * 创建ViewModel
     */
    fun <T : ViewModel> createViewModel(activity: FragmentActivity, clazz: Class<T>): T {
        return ViewModelProviders.of(activity).get(clazz)
    }

    /*=================================状态切换===========================================*/

    override fun initStatusLayout() {
        mStatusLayoutManager = StateLayoutManager.newBuilder(this)
                .contentView(mLayoutId)
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
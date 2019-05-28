package com.link.librarymodule.base.mvvm.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.link.librarymodule.base.ContainerActivity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.bus.Messenger
import com.link.librarymodule.utils.MaterialDialogUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>> : RxAppCompatActivity(), IBaseView {

    protected var mBinding: V? = null
    protected var mViewModel: VM? = null
    protected var mViewModelId: Int = 0
    protected var dialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
        //初始化DataBinging和ViewModel
        initViewDataBinding(savedInstanceState)
        //ViewModel与View的契约事件回掉逻辑
        registorUIChangeLiveDataCallBack()
        //页面数据初始化
        initData()
        //页面事件监听方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        mViewModel!!.registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        Messenger.getDefault().unregister(mViewModel)
        lifecycle.removeObserver(mViewModel!!)
        if (mViewModel != null) {
            mViewModel!!.registerRxBus()
        }
        if (mBinding != null) {
            mBinding!!.unbind()
        }
    }


    fun initViewDataBinding(savedInstanceState: Bundle?) {

        mBinding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        mViewModelId = initVariableId()
        mViewModel = initViewModel()
        if (mViewModel == null) {
            val modelClass: Class<*>
            val type = javaClass.genericSuperclass
            if (type is ParameterizedType) {
                modelClass = type.actualTypeArguments[1] as Class<*>
            } else {
                modelClass = BaseViewModel::class.java
            }
            mViewModel = createViewModel(this, modelClass) as VM
        }
        //关联viewModel
        mBinding!!.setVariable(mViewModelId, mViewModel)
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(mViewModel!!)
        //注入RxLifecycle生命周期
        mViewModel!!.injectLifecycleProvider(this)
    }

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    fun registorUIChangeLiveDataCallBack() {
        //加载载入框
        mViewModel!!.uc.showDialogEvent.observe(this, Observer { title -> showDialog(title) })
        //取消载入框
        mViewModel!!.uc.dismissDialogEvent.observe(this, Observer { dismissDialog() })
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

    override fun initData() {

    }

    override fun initParam() {

    }

    override fun initViewObservable() {

    }


    abstract fun initContentView(savedInstanceState: Bundle?): Int

    /**
     * 初始化viewModel的ID
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     */
    fun initViewModel(): VM? {
        return null
    }

    /**
     * 创建ViewModel
     */
    fun <T : ViewModel> createViewModel(activity: FragmentActivity, clazz: Class<T>): T {
        return ViewModelProviders.of(activity).get(clazz)
    }

    /*======================================================================================*/
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>) {
        startActivity(Intent(this, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    @JvmOverloads
    fun startContainerActivity(canonicalName: String, bundle: Bundle? = null) {
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName)
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle)
        }
        startActivity(intent)
    }

    /**
     * 显示载入框
     */
    fun showDialog(title: String?) {
        if (dialog != null) {
            dialog!!.show()
        } else {
            val builder = MaterialDialogUtils.showIndeterminateProgressDialog(this, title, true)
            dialog = builder.show()
        }
    }

    /**
     * 取消载入框
     */
    fun dismissDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    fun refreshLayout() {
        if (mViewModel != null) {
            mBinding!!.setVariable(mViewModelId, mViewModel)
        }
    }

}
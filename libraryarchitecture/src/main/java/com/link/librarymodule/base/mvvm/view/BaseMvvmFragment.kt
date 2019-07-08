package com.link.librarymodule.base.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.link.librarymodule.base.ContainerActivity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.trello.rxlifecycle2.components.support.RxFragment
import java.lang.reflect.ParameterizedType
import com.link.librarymodule.bus.Messenger
import com.link.librarymodule.utils.MaterialDialogUtils

abstract class BaseMvvmFragment<V : ViewDataBinding, VM : BaseViewModel<*>> : RxFragment(), IBaseView {
    protected var binding: V? = null
    protected var viewModel: VM? = null
    private var viewModelId: Int = 0
    private var dialog: MaterialDialog? = null

    val isBackPressed: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            initContentView(inflater, container, savedInstanceState),
            container,
            false
        )
        return binding!!.getRoot()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel)
        //解除ViewModel生命周期感应
        lifecycle.removeObserver(viewModel!!)
        if (viewModel != null) {
            viewModel!!.removeRxBus()
        }
        if (binding != null) {
            binding!!.unbind()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        //私有的ViewModel与View的契约事件回调逻辑
        registorUIChangeLiveDataCallBack()
        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        viewModel!!.registerRxBus()
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding() {
        viewModelId = initVariableId()
        viewModel = initViewModel()
        if (viewModel == null) {
            val modelClass: Class<VM>
            val type = javaClass.genericSuperclass
            if (type is ParameterizedType) {
                modelClass = type.actualTypeArguments[1] as Class<VM>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel::class.java as Class<VM>
            }
            viewModel = createViewModel(this, modelClass)
        }
        binding!!.setVariable(viewModelId, viewModel)
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel!!)
        //注入RxLifecycle生命周期
        viewModel!!.injectLifecycleProvider(this)
    }

    /**
     * =====================================================================
     */
    //注册ViewModel与View的契约UI回调事件
    protected fun registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel!!.uc.getShowDialogEvent().observe(this,
            Observer<String> { title -> showDialog(title) })
        //加载对话框消失
        viewModel!!.uc.getDismissDialogEvent().observe(this,
            Observer<Void> { dismissDialog() })
        //跳入新页面
        viewModel!!.uc.getStartActivityEvent().observe(this,
            Observer<Map<String, Any>> { params ->
                val clz = params[BaseViewModel.ParameterField.CLASS] as Class<*>
                val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
                startActivity(clz, bundle)
            })
        //跳入ContainerActivity
        viewModel!!.getUC().getStartContainerActivityEvent().observe(this,
            Observer<Map<String, Any>> { params ->
                val canonicalName = params[BaseViewModel.ParameterField.CANONICAL_NAME] as String
                val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
                startContainerActivity(canonicalName, bundle)
            })
        //关闭界面
        viewModel!!.getUC().getFinishEvent().observe(this,
            Observer<Void> { activity!!.finish() })
        //关闭上一层
        viewModel!!.getUC().getOnBackPressedEvent().observe(this,
            Observer<Void> { activity!!.onBackPressed() })
    }

    fun showDialog(title: String) {
        if (dialog != null) {
            dialog!!.show()
        } else {
            val builder = MaterialDialogUtils.showIndeterminateProgressDialog(activity, title, true)
            dialog = builder.show()
        }
    }

    fun dismissDialog() {
        if (dialog != null && dialog!!.isShowing()) {
            dialog!!.dismiss()
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>) {
        startActivity(Intent(context, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clz)
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
        val intent = Intent(context, ContainerActivity::class.java)
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName)
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle)
        }
        startActivity(intent)
    }

    /**
     * =====================================================================
     */

    //刷新布局
    fun refreshLayout() {
        if (viewModel != null) {
            binding!!.setVariable(viewModelId, viewModel)
        }
    }

    override fun initParam() {

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    open fun initViewModel(): VM? {
        return null
    }

    override fun initData() {

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
}

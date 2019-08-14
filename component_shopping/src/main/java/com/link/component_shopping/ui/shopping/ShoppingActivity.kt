package com.link.component_shopping.ui.shopping

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.link.component_shopping.R
import com.link.component_shopping.jsinterface.JavaScriptInterface
import com.link.component_shopping.ui.ViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.widgets.webview.X5WebView
import com.link.librarymodule.base.mvvm.view.BaseMvvmActivity
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.utils.ToastUtils
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import com.tencent.smtt.sdk.WebView
import kotlinx.android.synthetic.main.shopping_activity_main.*

/**
 * @author WJ
 * @date 2019-08-10
 *
 * 描述：购物商城
 */
@Route(path = RouterConstant.SHOPPING)
class ShoppingActivity(override var mLayoutId: Int = R.layout.shopping_activity_main) : BaseMvvmActivity<ShoppingViewModel>() {

    override fun getViewModel(): ShoppingViewModel {
        return ViewModelFactory.getInstance().create(ShoppingViewModel::class.java)
    }

    private var mX5WebView: X5WebView? = null

    private var mJavaScriptInterface: JavaScriptInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(this)
        if (mX5WebView == null) {
            mX5WebView = X5WebView(this, null)
            findViewById<FrameLayout>(R.id.content).addView(mX5WebView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        mX5WebView!!.loadUrl(Constant.BASE_SHOPPING_WEB_URL)
        mJavaScriptInterface = JavaScriptInterface(this)
        mX5WebView!!.addJavascriptInterface(mJavaScriptInterface, "androidJSBridge")
        mX5WebView!!.setOnWebViewListener(object : X5WebView.OnWebViewListener {

            override fun shouldInterceptRequest(request: WebResourceRequest?, url: String?): WebResourceResponse? {
//                Log.d("TAG", "${request!!.url};\n$url")
                return null
            }

            override fun onProgressChanged(webView: WebView?, progress: Int) {

            }
        })


    }

    override fun getData() {
        super.getData()
        mViewModel!!.getRemoteData()
    }

    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel!!.goodsData.observe(this, Observer {
            mJavaScriptInterface!!.goodsData = it
        })

        mViewModel!!.secondData.observe(this, Observer {
            mJavaScriptInterface!!.secondsData =it
        })

    }

    /**
     * 监听 android 后退按钮点击事件。
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        //        监听 android 后退按钮点击事件。
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //1、首先判断当前网页是否还可以进行后退页面的操作，如果可以的话那么就后退网页。
            if (mX5WebView!!.canGoBack() && !Constant.BASE_SHOPPING_WEB_URL.equals(mX5WebView!!.getUrl())) {
                mX5WebView!!.goBack()
                return true
            } else {
                finish()
                return true
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<FrameLayout>(R.id.content).removeAllViews()
        mX5WebView!!.destroy()
        mX5WebView = null
//        android.os.Process.killProcess(android.os.Process.myPid())
    }
}

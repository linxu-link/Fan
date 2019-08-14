package com.link.component_shopping.ui.shopping

import android.os.Bundle
import android.view.KeyEvent
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.component_shopping.jsinterface.JavaScriptInterface
import com.link.component_shopping.ui.ViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.widgets.webview.X5WebView
import com.link.librarycomponent.widgets.webview.sonic.SonicRuntimeImpl
import com.link.librarycomponent.widgets.webview.sonic.SonicSessionClientImpl
import com.link.librarymodule.base.mvvm.view.BaseMvvmActivity
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.utils.Utils
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import com.tencent.smtt.sdk.WebView
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSession
import com.tencent.sonic.sdk.SonicSessionConfig


/**
 * @author WJ
 * @date 2019-08-10
 *
 * 描述：购物商城
 */
@Route(path = RouterConstant.SHOPPING)
class ShoppingActivity(override var mLayoutId: Int = com.link.component_shopping.R.layout.shopping_activity_main) : BaseMvvmActivity<ShoppingViewModel>() {

    override fun getViewModel(): ShoppingViewModel {
        return ViewModelFactory.getInstance().create(ShoppingViewModel::class.java)
    }

    private var mX5WebView: X5WebView? = null

    private var mJavaScriptInterface: JavaScriptInterface? = null

    private var sonicSession: SonicSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(this)

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(SonicRuntimeImpl(Utils.getContext()), SonicConfig.Builder().build())
        }

        val sonicSessionClient: SonicSessionClientImpl
        sonicSession = SonicEngine.getInstance().createSession(Constant.BASE_SHOPPING_WEB_URL, SonicSessionConfig.Builder().build())
        if (null != sonicSession) {
            sonicSessionClient = SonicSessionClientImpl()
            sonicSession!!.bindClient(sonicSessionClient)
        } else {
            throw UnknownError("create session fail!")
        }

        if (mX5WebView == null) {
            mX5WebView = X5WebView(this, null)
            findViewById<FrameLayout>(com.link.component_shopping.R.id.content).addView(mX5WebView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        mX5WebView!!.loadUrl(Constant.BASE_SHOPPING_WEB_URL)
        mJavaScriptInterface = JavaScriptInterface(this)
        mX5WebView!!.addJavascriptInterface(mJavaScriptInterface, "androidJSBridge")

        mX5WebView!!.setOnWebViewListener(object : X5WebView.OnWebViewListener {
            override fun onPageFinished(url: String?) {
                if (sonicSession != null) {
                    sonicSession!!.sessionClient.pageFinish(url);
                }
            }

            override fun shouldInterceptRequest(request: WebResourceRequest?, url: String?): WebResourceResponse? {

                return null
            }

            override fun onProgressChanged(webView: WebView?, progress: Int) {

            }
        })

        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mX5WebView!!)
            sonicSessionClient.clientReady()
        } else { // default mode
            mX5WebView!!.loadUrl(Constant.BASE_SHOPPING_WEB_URL)
        }


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
            mJavaScriptInterface!!.secondsData = it
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
        findViewById<FrameLayout>(com.link.component_shopping.R.id.content).removeAllViews()
        mX5WebView!!.destroy()
        mX5WebView = null

        if (null != sonicSession) {
            sonicSession!!.destroy()
            sonicSession = null
        }

    }
}

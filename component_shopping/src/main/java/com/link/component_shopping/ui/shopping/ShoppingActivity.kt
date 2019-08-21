package com.link.component_shopping.ui.shopping

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.KeyEvent
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.component_shopping.R
import com.link.component_shopping.jsinterface.JavaScriptInterface
import com.link.component_shopping.ui.ViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.widgets.webview.X5WebView
import com.link.librarycomponent.widgets.webview.sonic.SonicRuntimeImpl
import com.link.librarycomponent.widgets.webview.sonic.SonicSessionClientImpl
import com.link.librarymodule.base.mvvm.view.BaseMvvmActivity
import com.link.librarymodule.constant.Constant
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import com.tencent.sonic.sdk.*


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

    private var mSonicSession: SonicSession? = null

    private var mSonicSessionClient: SonicSessionClientImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(this)

        initSonic()
        initWebView()

        loadData()
    }

    private fun initSonic(){
        val sessionConfigBuilder = SonicSessionConfig.Builder()
        sessionConfigBuilder.setSessionMode(SonicConstants.SESSION_MODE_DEFAULT)
        //初始化vasSonic
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(SonicRuntimeImpl(this), SonicConfig.Builder().build())
        }
        mSonicSession = SonicEngine.getInstance().createSession(Constant.BASE_SHOPPING_WEB_URL, sessionConfigBuilder.build())
        if (null != mSonicSession) {
            mSonicSessionClient = SonicSessionClientImpl()
            mSonicSession!!.bindClient(mSonicSessionClient)
        } else {
//            throw UnknownError("create session fail!")
        }


    }

    private fun initWebView(){
        if (mX5WebView == null) {
            mX5WebView = X5WebView(this, null)
            findViewById<FrameLayout>(R.id.content).addView(mX5WebView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        mJavaScriptInterface = JavaScriptInterface(this)
        mX5WebView!!.addJavascriptInterface(mJavaScriptInterface, "androidJSBridge")

        mX5WebView!!.setOnWebViewListener(object : X5WebView.OnWebViewListener {
            override fun onPageFinished(url: String?) {
                if (mSonicSession != null) {
                    mSonicSession!!.sessionClient.pageFinish(url);
                }
            }

            override fun shouldInterceptRequest(request: WebResourceRequest?, url: String?): WebResourceResponse? {
                if (mSonicSession != null) {
                    return mSonicSession!!.sessionClient.requestResource(url) as WebResourceResponse?
                }
                return null
            }

            override fun onProgressChanged(progress: Int) {

            }
        })
    }

    override fun loadData() {
        super.loadData()
        mViewModel!!.getRemoteData()
    }

    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel!!.goodsData.observe(this, Observer {
            mJavaScriptInterface!!.goodsData = it
            showContent()
        })

        mViewModel!!.secondData.observe(this, Observer {
            mJavaScriptInterface!!.secondsData = it

            //网络数据加载完毕后，webview开始加载对应的URL
            if (mSonicSessionClient != null) {
                mSonicSessionClient!!.bindWebView(mX5WebView!!)
                mSonicSessionClient!!.clientReady()
            } else {
                //不采用sonic加载
                mX5WebView!!.loadUrl(Constant.BASE_SHOPPING_WEB_URL)
            }

        })

    }

    /**
     * 监听 android 后退按钮点击事件。
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //1、首先判断当前网页是否还可以进行后退页面的操作，如果可以的话那么就后退网页。
            Log.e("TAG", "${mX5WebView!!.canGoBack()} ");

            if (mX5WebView!!.canGoBack() && Constant.BASE_SHOPPING_WEB_URL != mX5WebView!!.url) {
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
        if (null != mSonicSession) {
            mSonicSession!!.destroy()
            mSonicSession = null
        }
        super.onDestroy()
        sendBroadcast(Intent(Constant.ACTION_SHOPPING))
        //关闭当前进程，清理内存
        Process.killProcess(Process.myPid())
    }
}

package com.link.librarycomponent.widgets.webview


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import com.link.librarymodule.utils.ToastUtils
import com.tencent.smtt.export.external.interfaces.JsResult
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.link.general_network.utils.NetworkUtil.url


class X5WebView : WebView {

    //    上下文
    private var mContext: Context? = null


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, i: Int) : super(context, attributeSet, i) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, i: Int, b: Boolean) : super(context, attributeSet, i, b) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, i: Int, map: Map<String, Any>, b: Boolean) : super(context, attributeSet, i, map, b) {
        init(context)
    }

    private fun init(context: Context) {
        this.mContext = context
        //打开此代码可使移动设备链接 chrome 调试
        setWebContentsDebuggingEnabled(true)

        //设置 jsBridge
        //addJavascriptInterface(new JavaScriptInterface(mContext), "androidJSBridge");

        //webView 设置
        initWebViewSettings()
        //webClient 设置
        initWebViewClient()
        //chromeClient 设置
        initChromeClient()
    }

    /**
     * webView 设置
     */
    private fun initWebViewSettings() {
        val webSettings = settings
        //        允许运行 js 代码
        webSettings.javaScriptEnabled = true
        //        不可缩放
        webSettings.setSupportZoom(false)
        webSettings.builtInZoomControls = false
        webSettings.displayZoomControls = true

        //不允许缓存
//        webSettings.setAppCacheEnabled(false)
        //设置缓存策略
//        webSettings.cacheMode = WebSettings.LOAD_DEFAULT

        removeJavascriptInterface("searchBoxJavaBridge_")

        webSettings.allowContentAccess = true
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.savePassword = false
        webSettings.saveFormData = false
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true

    }

    /**
     * webClient 设置
     */
    private fun initWebViewClient() {
        //设置网页在APP 内部打开，而不是用外部浏览器
        webViewClient = object : WebViewClient() {

            /**
             * 拦截WebView请求的,改为用原生方法请求
             */
            override fun shouldInterceptRequest(p0: WebView?, request: WebResourceRequest?, p2: Bundle?): WebResourceResponse? {
                return if (onWebViewListener != null) {
                    onWebViewListener!!.shouldInterceptRequest(request)
                } else {
                    null
                }

            }

            override fun shouldInterceptRequest(webView: WebView?, url: String?): WebResourceResponse? {
                return if (onWebViewListener != null) {
                    onWebViewListener!!.shouldInterceptRequest(url = url)
                } else {
                    null
                }
            }

            override fun shouldInterceptRequest(p0: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                return if (onWebViewListener != null) {
                    onWebViewListener!!.shouldInterceptRequest(request)
                } else {
                    null
                }
            }

            override fun onPageFinished(p0: WebView?, url: String?) {
                super.onPageFinished(p0, url)
                if (onWebViewListener != null) {
                    onWebViewListener!!.onPageFinished(url)
                }
            }
        }
    }

    /**
     * chromeClient 设置
     */
    private fun initChromeClient() {
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(webView: WebView?, i: Int) {
                super.onProgressChanged(webView, i)
                //回调网页加载状态
                if (onWebViewListener != null) {
                    onWebViewListener!!.onProgressChanged(i)
                }
//                Log.e("TAG", "${i}")
            }


            /**
             * 监听alert弹出框，使用原生弹框代替alert。
             */
            override fun onJsAlert(webView: WebView?, s: String?, s1: String?, jsResult: JsResult?): Boolean {

                val builder = AlertDialog.Builder(mContext)
                builder.setMessage(s1)
                builder.setNegativeButton("确定", null)
                builder.create().show()
                jsResult!!.confirm()

                return true
            }
        }
    }


    //回调接口
    private var onWebViewListener: OnWebViewListener? = null

    fun setOnWebViewListener(onWebViewListener: OnWebViewListener) {
        this.onWebViewListener = onWebViewListener
    }

    interface OnWebViewListener {
        fun onProgressChanged( progress: Int)

        fun shouldInterceptRequest(request: WebResourceRequest? = null, url: String? = null): WebResourceResponse?

        fun onPageFinished(url:String?)
    }

}

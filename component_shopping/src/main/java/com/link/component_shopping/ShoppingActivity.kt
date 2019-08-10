package com.link.component_shopping

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.link.component_shopping.jsinterface.JavaScriptInterface
import com.link.librarycomponent.router.RouterConstant
import com.link.librarycomponent.widgets.webview.X5WebView
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.constant.Constant
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
/**
 * @author WJ
 * @date 2019-08-10
 *
 * 描述：购物商城
 */
@Route(path = RouterConstant.SHOPPING)
class ShoppingActivity : BaseActivity() {

    private var mX5WebView: X5WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen(this)
        setContentView(R.layout.shopping_activity_main)
        if (mX5WebView == null) {
            mX5WebView = X5WebView(this, null)
            findViewById<FrameLayout>(R.id.content).addView(mX5WebView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }

        mX5WebView!!.loadUrl(Constant.BASE_WEB_URL)
        mX5WebView!!.addJavascriptInterface(JavaScriptInterface(this),"androidJSBridge")
        mX5WebView!!.setOnWebViewListener { webView, progress ->

        }
    }


    /**
     * 监听 android 后退按钮点击事件。
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        //        监听 android 后退按钮点击事件。
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //            1、首先判断当前网页是否还可以进行后退页面的操作，如果可以的话那么就后退网页。
            if (mX5WebView!!.canGoBack() && !Constant.BASE_WEB_URL.equals(mX5WebView!!.getUrl())) {
                mX5WebView!!.goBack()
                return true
            }else{
                finish()
                return true
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}

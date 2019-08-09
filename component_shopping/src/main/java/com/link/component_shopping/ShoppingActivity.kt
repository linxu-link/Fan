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
     * 记录用户点击后退按钮的时间差
     */
    private var endTime: Long = 0

    /**
     * 监听 android 后退按钮点击事件。
     * 1、首先判断当前网页是否还可以进行后退页面的操作，如果可以的话那么就后退网页。
     * 2、如果网页已经不可以进行后退操作了（即：网页在首页中，虚拟任务栈中，只包含了 imooc 。）
     * 在这种情况下，则会提示 "再按一次退出程序" ， 用户 两秒内再次点击后退按钮，则退出应用
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
            //            2、如果网页已经不可以进行后退操作了（即：网页在首页中，虚拟任务栈中，只包含了 imooc 。）
            //          在这种情况下，则会提示 "再按一次退出程序" ， 用户 两秒内再次点击后退按钮，则退出应用
//            if (System.currentTimeMillis() - endTime > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
//                endTime = System.currentTimeMillis()
//            } else {
//                finish()
//            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        android.os.Process.killProcess(android.os.Process.myPid())
    }
}

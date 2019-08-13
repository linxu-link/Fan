package com.link.librarycomponent.widgets.webview;


import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Map;


public class X5WebView extends WebView {

//    上下文
    private Context mContext;
//    回调接口
    private OnWebViewListener onWebViewListener;

    public void setOnWebViewListener(OnWebViewListener onWebViewListener) {
        this.onWebViewListener = onWebViewListener;
    }

    public X5WebView(Context context) {
        super(context);
        init(context);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        init(context);
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
        init(context);
    }

    private void init (Context context) {
        this.mContext = context;
//      打开此代码可使移动设备链接 chrome 调试
        setWebContentsDebuggingEnabled(true);

//        设置 jsBridge
//        addJavascriptInterface(new JavaScriptInterface(mContext), "androidJSBridge");

//        webView 设置
        initWebViewSettings();
//        webClient 设置
        initWebViewClient();
//        chromeClient 设置
        initChromeClient();
    }

    /**
     * webView 设置
     */
    private void initWebViewSettings () {
        WebSettings webSettings = getSettings();
//        允许运行 js 代码
        webSettings.setJavaScriptEnabled(true);
//        不可缩放
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(true);
//        设置缓存策略
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    /**
     * webClient 设置
     */
    private void initWebViewClient () {
//        设置网页在APP 内部打开，而不是用外部浏览器
        setWebViewClient(new WebViewClient(){
        });
    }

    /**
     * chromeClient 设置
     */
    private void initChromeClient () {
        setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);

//                回调网页加载状态
                if (onWebViewListener != null) {
                    onWebViewListener.onProgressChanged(webView, i);
                }
            }

            /**
             * 监听alert弹出框，使用原生弹框代替alert。
             */
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage(s1);
                builder.setNegativeButton("确定", null);
                builder.create().show();
                jsResult.confirm();

                return true;
            }
        });
    }

    public interface OnWebViewListener {
        void onProgressChanged(WebView webView, int progress);
    }
}

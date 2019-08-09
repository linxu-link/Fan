package com.link.component_shopping.jsinterface;


import android.content.Context;
import android.webkit.JavascriptInterface;

import com.link.component_pay.wxapi.helpers.WXPayHelper;


public class JavaScriptInterface {

//    微信支付帮助类
    private WXPayHelper mWxPayHelper;

    public JavaScriptInterface(Context context) {
//        初始化微信帮助类
        mWxPayHelper = new WXPayHelper(context);
//        初始化微信支付服务
        mWxPayHelper.initWXPay();
    }


    /**
     * 微信支付
     * @param payJson 支付商品信息
     */
    @JavascriptInterface
    public void wxPay (String payJson) {
        mWxPayHelper.pay(payJson);
    }

}

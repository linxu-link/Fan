package com.link.component_shopping.jsinterface;


import android.content.Context;
import android.webkit.JavascriptInterface;

import com.link.component_pay.alipay.AliPayHelper;
import com.link.component_pay.wxapi.helpers.WXPayHelper;


public class JavaScriptInterface {

    //    微信支付帮助类
    private WXPayHelper mWxPayHelper;
    //   支付宝支付帮助类
    private AliPayHelper mAliPayHelper;

    public JavaScriptInterface(Context context) {
        mWxPayHelper = new WXPayHelper(context);
        mAliPayHelper = new AliPayHelper(context);

    }


    /**
     * 微信支付
     *
     * @param payJson 支付商品信息
     */
    @JavascriptInterface
    public void wxPay(String payJson) {
        mWxPayHelper.pay(payJson);
    }

    /**
     * 支付宝支付
     * @param payJson 支付商品信息
     */
    @JavascriptInterface
    public void aliPay(String payJson) {
        mAliPayHelper.pay(payJson);
    }
}

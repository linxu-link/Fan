package com.link.fan.utils;


import android.content.Context;
import android.webkit.JavascriptInterface;

import com.link.fan.utils.alipay.AliPayHelper;
import com.link.fan.utils.wxapi.helpers.WXPayHelper;


public class JavaScriptInterface {

    //微信支付帮助类
    private WXPayHelper mWxPayHelper;
    //支付宝支付帮助类
    private AliPayHelper mAliPayHelper;

    //商品列表 数据、秒杀列表 数据
    private String mGoodsData, mSecondsData;

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
     *
     * @param payJson 支付商品信息
     */
    @JavascriptInterface
    public void aliPay(String payJson) {
        mAliPayHelper.pay(payJson);
    }

    @JavascriptInterface
    public String getGoodsData() {
        return mGoodsData;
    }

    @JavascriptInterface
    public String getSecondsData() {
        return mSecondsData;
    }

    public void setGoodsData(String goodsData) {
        mGoodsData = goodsData;
    }

    public void setSecondsData(String secondsData) {
        mSecondsData = secondsData;
    }
}

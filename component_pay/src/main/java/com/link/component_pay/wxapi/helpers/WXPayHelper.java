package com.link.component_pay.wxapi.helpers;

import android.content.Context;

import com.link.component_pay.wxapi.constants.WXConstants;
import com.link.component_pay.wxapi.utils.WXHttpUtils;
import com.link.component_pay.wxapi.utils.WXPayUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.TreeMap;

public class WXPayHelper {

    private IWXAPI mWXApi;
    private Context mContext;
    private JSONObject payObj;

    public WXPayHelper (Context context) {
        this.mContext = context;
    }

    /**
     * 初始化微信支付服务
     */
    public void initWXPay () {
        mWXApi = WXAPIFactory.createWXAPI(mContext, null);
        // 将该app注册到微信
        mWXApi.registerApp(WXConstants.WX_APP_ID);
    }

    /**
     * 立即支付
     * @param payJson
     */
    public void pay (String payJson) {
        try {
            payObj = new JSONObject(payJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        prepaidForm();
    }


    /**
     * 生成预支付订单.
     * 所有的微信支付需要先生成预支付订单，然后在下单调起微信支付
     */
    private void prepaidForm () {

        HashMap<String, String> advanceMap = getAdvanceMap();
        WXHttpUtils.httpPost(
                WXConstants.WX_PAY_BASE_URL
                        + WXConstants.UNIFIED_ORDER_URL,
                WXPayUtils.mapToXml(advanceMap),
                new WXHttpUtils.HttpCallBack() {
                    @Override
                    public void onCallBack(byte[] bytes) {
                        String str = new String(bytes);
                        try {
                            JSONObject jsonObject = WXPayUtils.xmlToJSON(str);
                            payReqWx(jsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 调起微信支付
     * @param json
     * @throws JSONException
     */
    private void payReqWx (JSONObject json) throws JSONException {
        PayReq req = new PayReq();
//        微信开放平台审核通过的应用APPID
        req.appId			= json.getString("appid");
//        微信支付分配的商户号
        req.partnerId		= json.getString("mch_id");
//        微信返回的支付交易会话ID
        req.prepayId		= json.getString("prepay_id");
//        随机字符串
        req.nonceStr		= json.getString("nonce_str");
//        时间戳 以秒为单位
        req.timeStamp		= System.currentTimeMillis() / 1000 + "";
//        填写固定值Sign=WXPay
        req.packageValue	= "Sign=WXPay";
//        签名
        req.sign			= getPaySing(req);
// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        boolean b = mWXApi.sendReq(req);
//        b == true , 表示可以调起微信支付
    }


    /**
     * 返回预支付订单请求数据
     * @return
     */
    private HashMap<String,String> getAdvanceMap() {
        HashMap<String,String> map = new HashMap<>();
        //    应用ID
        map.put("appid", WXConstants.WX_APP_ID);
        //    商户号
        map.put("mch_id", WXConstants.WX_MCH_ID);
        //    商品描述
        try {
            map.put("body", payObj.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //    随机字符串
        map.put("nonce_str", WXPayUtils.getNonceStr());
        //    接收微信支付异步通知回调地址
        map.put("notify_url", "http://www.weixin.qq.com/wxpay/pay.php");
        //    商户订单号
        map.put("out_trade_no", WXPayUtils.getNonceStr());
        //    终端IP
        map.put("spbill_create_ip", WXPayUtils.getIpAddress(mContext));
        //    总金额 单位 分
        map.put("total_fee", "1");
        //    支付类型
        map.put("trade_type", "APP");
        //    签名
        map.put("sign", WXPayUtils.createSign(new TreeMap<>(map)));

        return map;
    }

    /**
     * 返回支付订单重新签名的数据
     * @return
     */
    private String getPaySing(PayReq req){

        HashMap<String,String> map = new HashMap<>();
        map.put("appid", req.appId);
        map.put("partnerid", req.partnerId);
        map.put("prepayid", req.prepayId);
        map.put("noncestr", req.nonceStr);
        map.put("timestamp", req.timeStamp);
        map.put("package", req.packageValue);

        return WXPayUtils.createSign(new TreeMap<>(map));
    }


}

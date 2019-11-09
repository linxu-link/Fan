package com.link.fan.utils.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.alipay.sdk.app.PayTask;
import com.link.fan.utils.alipay.utils.OrderInfoUtil2_0;
import com.link.fan.utils.alipay.utils.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * 重要说明：
 * <p>
 * 本 代码 只是为了方便直接向商户展示支付宝的整个支付流程，所以将加签过程直接放在客户端完成
 * （包括 OrderInfoUtil2_0_HK 和 OrderInfoUtil2_0）。
 * <p>
 * 在真实 App 中，私钥（如 RSA_PRIVATE 等）数据严禁放在客户端，同时加签过程务必要放在服务端完成，
 * 否则可能造成商户私密数据泄露或被盗用，造成不必要的资金损失，面临各种安全风险。
 * <p>
 * Warning:
 * <p>
 * For demonstration purpose, the assembling and signing of the request parameters are done on
 * the client side in this demo application.
 * <p>
 * However, in practice, both assembling and signing must be carried out on the server side.
 */
public class AliPayHelper {
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。对应商户的UID
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。商户标识该次用户授权请求的ID，该值在商户端应保持唯一
     */
    public static final String TARGET_ID = "imooc2019";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;

    private Context mContext;
    private JSONObject payObj;

    public AliPayHelper(Context context) {
        this.mContext = context;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            //                    跳转到处理支付宝回调的 Activity
            if (msg.what == SDK_PAY_FLAG) {
                @SuppressWarnings("unchecked")
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                Intent intent = new Intent(mContext, AliPayResultActivity.class);
                intent.putExtra("PayResult", payResult);
                mContext.startActivity(intent);
            }
        }

        ;
    };


    /**
     * 支付宝支付业务示例
     */
    public void pay(String payJson) {
        try {
            payObj = new JSONObject(payJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, payObj);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}

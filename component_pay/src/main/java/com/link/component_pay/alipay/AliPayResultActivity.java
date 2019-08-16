package com.link.component_pay.alipay;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.link.component_pay.R;
import com.link.component_pay.alipay.utils.PayResult;


public class AliPayResultActivity extends AppCompatActivity {

    private ImageView mIvIcon;
    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_pay_result);

        mIvIcon = findViewById(R.id.iv_icon);
        mTvText = findViewById(R.id.tv_text);

        mIvIcon.setImageResource(R.mipmap.error);
        mTvText.setText("支付失败，用户手动取消");

        initResult();
    }

    /**
     * 处理支付宝回调的方法
     */
    private void initResult () {
        PayResult payResult = getIntent().getParcelableExtra("PayResult");

        /**
         * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖requestPermission服务端的异步通知。
            mIvIcon.setImageResource(R.mipmap.success);
            mTvText.setText("付款成功");
        } else {
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            mIvIcon.setImageResource(R.mipmap.error);
            mTvText.setText("付款失败，服务器返回错误信息：" + payResult);
        }
    }


    /**
     * 返回按钮点击事件
     * @param view
     */
    public void onBackClick (View view) {
        onBackPressed();
    }
}

package com.link.fan.utils.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.link.fan.R;
import com.link.fan.utils.wxapi.constants.WXConstants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI wxApi;
    private ImageView mIvIcon;
    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);

        wxApi = WXAPIFactory.createWXAPI(this, WXConstants.WX_APP_ID);
        wxApi.handleIntent(getIntent(), this);

        mIvIcon = findViewById(R.id.iv_icon);
        mTvText = findViewById(R.id.tv_text);

        mIvIcon.setImageResource(R.mipmap.error);
        mTvText.setText("支付失败，用户手动取消");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        wxApi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Message message = new Message();
            message.what = baseResp.errCode;
            message.obj = baseResp.errStr;
            handler.sendMessage(message);
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    mIvIcon.setImageResource(R.mipmap.success);
                    mTvText.setText("支付成功");
                    break;
                case -1:
                    mIvIcon.setImageResource(R.mipmap.error);
                    mTvText.setText("支付失败，调用支付错误：" + msg.obj);
                    break;
                case -2:
                    mIvIcon.setImageResource(R.mipmap.error);
                    mTvText.setText("支付失败，用户手动取消");
                    break;
            }
        }
    };


    /**
     * 返回按钮点击事件
     * @param view
     */
    public void onBackClick (View view) {
        onBackPressed();
    }
}

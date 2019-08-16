package com.link.librarymodule.keeplive.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author WJ
 * @date 2019-08-15
 *
 * 描述：一个像素的activity，常见的保活方案
 * */
public final class OnePixelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设定一像素的activity
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkScreenOn("onResume");
    }
    private void checkScreenOn(String methodName) {
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        if (isScreenOn) {
            finish();
        }
    }
}

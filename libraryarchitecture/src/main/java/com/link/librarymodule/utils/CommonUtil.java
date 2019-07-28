package com.link.librarymodule.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.link.librarybase.Utils;
import com.link.librarymodule.BaseApplication;

import java.lang.ref.WeakReference;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by wj on 2017/5/17.
 */
public class CommonUtil {


    public static PackageInfo getPackageInfo() {
        PackageInfo info = new PackageInfo();
        try {
            info = Utils.getContext().getPackageManager().getPackageInfo(Utils.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * dp 转 px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * dp 转 sp
     *
     * @param dp
     * @return
     */
    public static int dp2sp(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp,
                Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * Description:设置外边距
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getDisWidth() {
        return Utils.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPx(float dp) {
        return (int) (Utils.getContext().getResources().getDisplayMetrics().density * dp);
    }

    public static byte[] hamcSha1(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }


    public static final int DELAY = 800;
    private static long lastClickTime = 0;

    /**
     * 防止重复点击
     *
     * @return
     */
    public static boolean isNotFastClick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DELAY) {
            lastClickTime = currentTime;
            return true;
        } else {
            return false;
        }
    }

    //获取当前进程的名字
    @Nullable
    private String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (mActivityManager == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static int getWindowWidth(Context context) {
        WeakReference<Context> reference = new WeakReference<>(context);
        if (reference.get() == null) {
            return 0;
        }
        DisplayMetrics dm = reference.get().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getWindowHeight(Context context) {
        WeakReference<Context> reference = new WeakReference<>(context);
        if (reference.get() == null) {
            return 0;
        }
        DisplayMetrics dm = reference.get().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getWindowDpi(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }


}

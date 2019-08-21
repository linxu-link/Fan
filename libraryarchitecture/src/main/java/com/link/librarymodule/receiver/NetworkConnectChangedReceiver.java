package com.link.librarymodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

import com.link.general_network.utils.NetworkUtil;

import java.lang.ref.WeakReference;


/**
 * @author WJ
 * @date 2019-08-19
 *
 * 描述：监听网络变化的广播
 */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    public NetworkConnectChangedReceiver() {
    }

    public NetworkConnectChangedReceiver(Context context) {
        registerNetworkStatusReceiver(context);
    }

    private NetworkChangeListener mNetworkChangeListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
//            Log.e("onReceive: ", NetworkUtil.isNetworkAvailable(context) + "");
            if (mNetworkChangeListener!=null){
                mNetworkChangeListener.onNetworkChange(NetworkUtil.isNetworkAvailable(context));
            }
        }
    }


    /**
     * 注册广播
     *
     * @param context
     */
    public void registerNetworkStatusReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        context.registerReceiver(this, filter);
    }

    public void unregisterNetworkReceiver(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (weakReference.get() != null) {
            weakReference.get().unregisterReceiver(this);
        }
    }


    private void networkConnectListener(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            // connected to the internet
            if (activeNetwork != null) {
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                    }
                } else {

                }
            } else {

            }
        }
    }

    // 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager
    // .WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
    // 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，
    // 刚打开wifi肯定还没有连接到有效的无线
    private void wifiConnectListener(Context context, Intent intent) {

        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {

            Parcelable parcelableExtra = intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                // 当然，这边可以更精确的确定状态
                boolean isConnected = state == NetworkInfo.State.CONNECTED;
                if (isConnected) {


                } else {


                }
            }

        }


    }

    private void wifiStateListener(Context context, Intent intent) {
        // 这个监听wifi的打开与关闭，与wifi的连接无关
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:

                    break;
                case WifiManager.WIFI_STATE_DISABLING:

                    break;
                case WifiManager.WIFI_STATE_ENABLING:

                    break;
                case WifiManager.WIFI_STATE_ENABLED:

                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:

                    break;
                default:
                    break;
            }
        }

    }


    public interface NetworkChangeListener {

        void onNetworkChange(boolean isNetConnect);

    }

    public void setNetworkChangeListener(NetworkChangeListener networkChangeListener) {
        mNetworkChangeListener = networkChangeListener;
    }
}

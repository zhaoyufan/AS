package com.upic.asn.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by ZYF on 2016/12/18.
 */
public class NetWorkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        NetworkInfo networkInfo1 = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo2 = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null && networkInfo.isAvailable()) {
            if(networkInfo2.isConnected()){
                Toast.makeText(context, "当前使用2G/3G/4G！", Toast.LENGTH_SHORT).show();
            }else if(networkInfo1.isConnected()){
                //Toast.makeText(context, "当前使用wifi！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "网络不可用！", Toast.LENGTH_SHORT).show();
        }
    }
}

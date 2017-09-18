package com.baway.hujiqiang.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author:
 * data: 2017/8/25.
 * desc:判断当前有无网络连接
 */

public class IsNetUtil {
    //判断当前有无网络连接
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null) {
                    return info.isAvailable();
                }
            }
        }
        return false;
    }
}

package com.baway.hujiqiang.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author:
 * data: 2017/8/25.
 * desc:网络请求
 */

public class RequestUrlUtil {
    private static volatile RequestUrlUtil mRequestUrlUtil = null;
    private OkHttpClient client;

    private RequestUrlUtil() {
        client = new OkHttpClient();
    }

    //get请求网络
    public static RequestUrlUtil getInstance() {

        if (mRequestUrlUtil == null) {
            synchronized (RequestUrlUtil.class) {
                if (mRequestUrlUtil == null) {
                    mRequestUrlUtil = new RequestUrlUtil();
                }
            }
        }
        return mRequestUrlUtil;
    }

    public Call getRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        return call;
    }
}

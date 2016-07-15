package com.example.fupei.pythonapi.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by FuPei on 2016/7/15.
 */
public class RequestUtil {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void get(String url) {
        Request request = new Request.Builder().url(url).build();
        connect(request);
    }

    private static void connect(Request request) {
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String msg = response.body().string();
                LogUtil.i(msg);
            }
        });
    }

    public static void post(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
       connect(request);
    }

}

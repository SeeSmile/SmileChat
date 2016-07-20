package com.seesmile.chat.pythonapi.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by FuPei on 2016/7/19.
 */
public class ApiUtil {

    public static void get(String url, RequestParams params, final RequestListener listener) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new ResponHandler(listener));
    }

    public static void post(String url, RequestParams params, final RequestListener listener) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new ResponHandler(listener));
    }

    private static class ResponHandler extends AsyncHttpResponseHandler {

        private RequestListener listener;

        public ResponHandler(RequestListener listener) {
            this.listener = listener;
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            if(responseBody != null) {
                listener.onSuccess(new String(responseBody));
            } else {
                listener.onSuccess("");
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            if(responseBody != null) {
                listener.onFail(statusCode, new String(responseBody));
            } else {
                listener.onFail(statusCode, "");
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            listener.onFinish();
        }
    }



    abstract static public class RequestListener {
        public abstract void onSuccess(String content);
        public abstract void onFail(int code, String content);
        public void onFinish() {

        }
    }

}

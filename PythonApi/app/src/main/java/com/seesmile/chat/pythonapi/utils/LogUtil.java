package com.seesmile.chat.pythonapi.utils;

import android.util.Log;

/**
 * Created by FuPei on 2016/7/15.
 */
public class LogUtil {

    private static final boolean SHOW_LOG = true;
    private static final String TAG = "chats";

    public static void i(String text) {
        if(SHOW_LOG) {
            Log.i(TAG, text);
        }
    }

    public static void d(String text) {
        if(SHOW_LOG) {
            Log.d(TAG, text);
        }
    }

}

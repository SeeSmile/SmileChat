package com.example.fupei.pythonapi.utils;

import android.util.Log;

/**
 * Created by FuPei on 2016/7/15.
 */
public class LogUtil {

    private static final boolean ISLOG = true;
    private static final String TAG = "smile";

    public static void i(String text) {
        if(ISLOG) {
            Log.i(TAG, text);
        }
    }

}

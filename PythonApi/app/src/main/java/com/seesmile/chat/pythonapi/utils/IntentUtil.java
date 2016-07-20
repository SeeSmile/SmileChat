package com.seesmile.chat.pythonapi.utils;

import android.content.Intent;

import com.seesmile.chat.pythonapi.data.ConstantBrocast;

/**
 * Created by FuPei on 2016/7/19.
 */
public class IntentUtil {

    public static Intent getLoginIntent() {
        Intent intent = new Intent();
        intent.setAction(ConstantBrocast.BROCAST_LOGIN_SUCCESS);
        return intent;
    }

}

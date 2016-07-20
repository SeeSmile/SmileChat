package com.seesmile.chat.pythonapi.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by FuPei on 2016/7/15.
 */
public class WeiboLoginUtil {
    public static final String APP_KEY      = "1025972305";		   // 应用的APP_KEY
    public static final String REDIRECT_URL = "http://www.baidu.com";// 应用的回调页
    public static final String SCOPE = "";
    public static void login(final Context context) {
        final AuthInfo mAuthInfo = new AuthInfo(context, APP_KEY, REDIRECT_URL, SCOPE);
        WeiboAuthListener listener = new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(bundle); // 从 Bundle 中解析 Token
                if (mAccessToken.isSessionValid()) {
                    Log.i("smile", mAccessToken.getPhoneNum());

                } else {
                    // 当您注册的应用程序签名不正确时，就会收到错误Code，请确保签名正确
                    String code = bundle.getString("code", "");
                }

            }

            @Override
            public void onWeiboException(WeiboException e) {

            }

            @Override
            public void onCancel() {

            }
        };
        SsoHandler handler = new SsoHandler((Activity) context, mAuthInfo);
        handler.authorizeWeb(listener);
    }
}

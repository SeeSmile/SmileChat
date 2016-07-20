package com.seesmile.chat.pythonapi.appcation;

import android.app.Application;
import android.widget.Toast;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.util.NetUtils;
import com.seesmile.chat.pythonapi.utils.LogUtil;
import com.seesmile.chat.pythonapi.utils.ToastUtil;

/**
 * Created by FuPei on 2016/7/19.
 */
public class SmileApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initHuanXin();
    }

    private void initHuanXin() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(int error) {
                if(error == EMError.USER_REMOVED){
                    // 显示帐号已经被移除
                }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    LogUtil.i("北极了，我曹");
                    // 显示帐号在其他设备登录
                } else {
                    if (NetUtils.hasNetwork(SmileApplication.this)) {

                    }
                    //连接不到聊天服务器
                    else {

                    }
                    //当前网络不可用，请检查网络设置
                }
            }
        });
    }

}

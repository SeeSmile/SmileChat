package com.seesmile.chat.pythonapi.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseActivity;
import com.seesmile.chat.pythonapi.fragments.FriendFragment;
import com.seesmile.chat.pythonapi.fragments.FunctionFragment;
import com.seesmile.chat.pythonapi.utils.LogUtil;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by FuPei on 2016/7/19.
 */
public class MainActivity extends BaseActivity {

    private final static String TAG_FRI = "friend";
    private final static String TAG_FUN = "function";

    @InjectView(R.id.tv_friend)
    TextView tv_friend;
    @InjectView(R.id.tv_function)
    TextView tv_function;

    private FriendFragment frag_fri;
    private FunctionFragment frag_fun;
    private EMMessageListener messageListener;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        frag_fri = new FriendFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ly_main, frag_fri, TAG_FRI).commit();
    }

    @Override
    public void initListener() {
        tv_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentByTag(TAG_FRI) == null) {
                    frag_fri = new FriendFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.ly_main, frag_fri,
                            TAG_FRI).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(frag_fri);
                }
            }
        });

        tv_function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentByTag(TAG_FUN) == null) {
                    frag_fun = new FunctionFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.ly_main, frag_fun,
                            TAG_FUN).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(frag_fun);
                }
            }
        });

        messageListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                LogUtil.d("onMessageReceived");
                for(EMMessage msg : list) {
                    LogUtil.d(msg.toString());
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {
                LogUtil.d("onCmdMessageReceived");
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> list) {
                LogUtil.d("onMessageReceivedAckReceived");
            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> list) {
                LogUtil.d("onMessageDeliveryAckReceived");
            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {
                LogUtil.d("onMessageChanged");
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }
}

package com.seesmile.chat.pythonapi.activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseActivity;
import com.seesmile.chat.pythonapi.data.ConstantBrocast;

/**
 * Created by FuPei on 2016/7/19.
 */
public class WelComeActivity extends BaseActivity {

    private TextView tv_welcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv_welcome = (TextView) findViewById(R.id.tv_welcome);
        tv_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelComeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        registBro();
    }

    private void registBro() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstantBrocast.BROCAST_LOGIN_SUCCESS);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                startActivity(new Intent(WelComeActivity.this, MainActivity.class));
                finish();
            }
        }, filter);
    }
}

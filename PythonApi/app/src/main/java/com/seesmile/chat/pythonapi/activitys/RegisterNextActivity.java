package com.seesmile.chat.pythonapi.activitys;

import android.os.Bundle;
import android.renderscript.Type;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseActivity;
import com.seesmile.chat.pythonapi.utils.LogUtil;

import butterknife.InjectView;

/**
 * Created by FuPei on 2016/7/20.
 */
public class RegisterNextActivity extends BaseActivity {

    public static final String TYPE_ACCOUNT = "ACCOUNT";
    public static final String TYPE_PASSWORD = "PASSWORD";
    public static final String TYPE_NICKNAME = "NICKNAME";

    private String account;
    private String password;
    private String nickname;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        initData();
        initToolBar();
    }

    @Override
    public void initData() {
        account = getIntent().getStringExtra(TYPE_ACCOUNT);
        password = getIntent().getStringExtra(TYPE_PASSWORD);
        nickname = getIntent().getStringExtra(TYPE_NICKNAME);
        LogUtil.i("account = " + account + ", password = " + password + ", nickname = " + nickname);
    }

    private void initToolBar() {
        setTitle("注册");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

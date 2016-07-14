package com.example.fupei.pythonapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_login)
    TextView tv_login;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initListener();
    }

    private void initListener() {
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
    }

    private void initToolBar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showToast() {
        mToast = new Toast(this);
        TextView tv = new TextView(this);
        tv.setText("我曹嗷嗷");
        TranslateAnimation ani = new TranslateAnimation(0, 0, 100, 100);
        ani.setDuration(1000);
//        tv.setAnimation(ani);
        mToast.setView(tv);
        mToast.setGravity(Gravity.TOP, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
        mToast.getView().startAnimation(ani);

//        tv.startAnimation(ani);
    }
}

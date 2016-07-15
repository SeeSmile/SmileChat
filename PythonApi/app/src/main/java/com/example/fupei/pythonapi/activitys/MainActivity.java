package com.example.fupei.pythonapi.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fupei.pythonapi.R;
import com.example.fupei.pythonapi.base.BaseActivity;
import com.example.fupei.pythonapi.data.Constant;
import com.example.fupei.pythonapi.utils.RequestUtil;
import com.example.fupei.pythonapi.utils.ToastUtil;
import com.example.fupei.pythonapi.utils.WeiboLoginUtil;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;

public class MainActivity extends BaseActivity {

    private TextView tv_login_sina;
    private Toolbar mToolbar;
    private EditText et_account;
    private EditText et_password;
    private TextView tv_login;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initListener();
    }

    private void initListener() {
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_login_sina = (TextView) findViewById(R.id.tv_login_sina);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                pDialog.setTitleText("正在登陆...");
//                pDialog.setCancelable(false);
//                pDialog.show();
                JSONObject json = new JSONObject();
                try {
                    json.put("account", et_account.getText().toString());
                    json.put("pwd", et_password.getText().toString());
                } catch (JSONException e) {

                }
                RequestBody body = RequestBody.create(RequestUtil.JSON, json.toString());
                RequestUtil.post(Constant.API_LOGIN, body);
            }
        });

        tv_login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeiboLoginUtil.login(MainActivity.this);
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


}

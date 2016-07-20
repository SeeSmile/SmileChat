package com.seesmile.chat.pythonapi.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseActivity;
import com.seesmile.chat.pythonapi.data.Constant;
import com.seesmile.chat.pythonapi.utils.ApiUtil;
import com.seesmile.chat.pythonapi.utils.IntentUtil;
import com.seesmile.chat.pythonapi.utils.LogUtil;
import com.seesmile.chat.pythonapi.utils.ToastUtil;
import com.seesmile.chat.pythonapi.utils.WeiboLoginUtil;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    private TextView tv_login_sina;
    private Toolbar mToolbar;
    private EditText et_account;
    private EditText et_password;
    private TextView tv_login;
    private TextView tv_login_weixin;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolBar();
    }

    public void initListener() {
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_login_sina = (TextView) findViewById(R.id.tv_login_weibo);
        tv_login_weixin = (TextView) findViewById(R.id.tv_login_weixin);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCompleteInfo()) {
                    return;
                }
                loginHuanxin();

            }
        });

        tv_login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeiboLoginUtil.login(LoginActivity.this);
            }
        });

        tv_login_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean isCompleteInfo() {
        if(et_account.getText().toString() == null || et_account.getText().toString().length() == 0) {
            ToastUtil.show(this, "请填写账号!");
            return false;
        }
        if(et_password.getText().toString() == null || et_password.getText().toString().length() == 0) {
            ToastUtil.show(this, "请填写密码!");
            return false;
        }
        return true;
    }

    private void checkLogin(String content) {
        try {
            JSONObject json = new JSONObject(content);
            LogUtil.i(json.toString());
            int code = json.optInt("code");
            if(code == 1) {
                loginHuanxin();

            } else if(code == 3 || code == 2) {
                ToastUtil.show(LoginActivity.this, json.optString("msg"));
            } else {
                ToastUtil.show(LoginActivity.this, "服务器死了");
            }
        } catch (JSONException e) {
            ToastUtil.show(LoginActivity.this, "数据错误");
        }
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("登录");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loginApp() {
        RequestParams params = new RequestParams();
        params.put("account", et_account.getText().toString());
        params.put("password", et_password.getText().toString());
        ToastUtil.show(LoginActivity.this, "正在登陆");
        ApiUtil.post(Constant.API_LOGIN, params, new ApiUtil.RequestListener() {

            @Override
            public void onSuccess(String content) {
                checkLogin(content);
            }

            @Override
            public void onFail(int code, String content) {
                ToastUtil.show(LoginActivity.this, "连接网络失败" + code);
            }
        });
    }

    private void loginHuanxin() {
        EMClient.getInstance().login("seesmile", "123456",
//        EMClient.getInstance().login(et_account.getText().toString(), et_password.getText().toString(),
                new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
//                        EMClient.getInstance().groupManager().loadAllGroups();
//                        EMClient.getInstance().chatManager().loadAllConversations();
                        LogUtil.i("登录聊天服务器成功！");
                        sendBroadcast(IntentUtil.getLoginIntent());
                        finish();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        LogUtil.i("登录聊天服务器失败！");
                    }
                });
    }

}

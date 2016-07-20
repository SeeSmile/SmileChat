package com.seesmile.chat.pythonapi.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseActivity;
import com.seesmile.chat.pythonapi.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by FuPei on 2016/7/19.
 */
public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.et_pwd)
    EditText et_pwd;
    @InjectView(R.id.et_nickname)
    EditText et_name;
    @InjectView(R.id.cb_show_pwd)
    CheckBox cb_show;
    @InjectView(R.id.et_account)
    EditText et_account;
    @InjectView(R.id.tv_next)
    TextView tv_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initToolBar();
        initListener();
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

    @Override
    public void initListener() {
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCompleteInfo()) {
                    Intent intent = new Intent(RegisterActivity.this, RegisterNextActivity.class);
                    intent.putExtra(RegisterNextActivity.TYPE_ACCOUNT, et_account.getText().toString());
                    intent.putExtra(RegisterNextActivity.TYPE_PASSWORD, et_pwd.getText().toString());
                    intent.putExtra(RegisterNextActivity.TYPE_NICKNAME, et_name.getText().toString());
                    startActivity(intent);
                }
            }
        });

        cb_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    et_pwd.setInputType(InputType.TYPE_NULL);
                }
            }
        });
    }

    private boolean isCompleteInfo() {
        if(et_account.getText().length() == 0) {
            ToastUtil.show(this, "请输入手机号或邮箱!");
            return false;
        }
        if(et_pwd.getText().toString().length() == 0) {
            ToastUtil.show(this, "请输入密码!");
            return false;
        }
        if(et_name.getText().length() == 0) {
            ToastUtil.show(this, "请输入昵称!");
            return false;
        }
        return true;
    }

}

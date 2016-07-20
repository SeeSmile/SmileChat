package com.seesmile.chat.pythonapi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.seesmile.chat.pythonapi.R;

import butterknife.ButterKnife;

/**
 * Created by FuPei on 2016/7/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initListener();
    }

    public void initListener() {

    }

    public void initData() {

    }

    public void initView() {

    }

}

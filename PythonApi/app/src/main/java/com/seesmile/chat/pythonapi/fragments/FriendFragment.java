package com.seesmile.chat.pythonapi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseFragment;

/**
 * Created by FuPei on 2016/7/20.
 */
public class FriendFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, null);
        return view;

    }
}

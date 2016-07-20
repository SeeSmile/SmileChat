package com.seesmile.chat.pythonapi.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.seesmile.chat.pythonapi.R;
import com.seesmile.chat.pythonapi.base.BaseActivity;

/**
 * Created by FuPei on 2016/7/15.
 */
public class ToastUtil {

    private static boolean isToast = false;
    private static View toastView;
    private static TextView tv_toast;

    public static void show(final Activity activity, String text) {
        if(!isToast) {
            toastView = LayoutInflater.from(activity).inflate(R.layout.toastview, null);
            tv_toast = (TextView) toastView.findViewById(R.id.tv_toast);
            tv_toast.setText(text);
            TranslateAnimation ani = new TranslateAnimation(0, 0, 0, 80);
            AlphaAnimation ani_alp = new AlphaAnimation(0, 1);
            ani_alp.setDuration(600);
            ani.setDuration(300);
            final ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
            viewGroup.addView(toastView);
            isToast = true;
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(ani);
            animationSet.addAnimation(ani_alp);
            animationSet.setFillAfter(true);
            tv_toast.startAnimation(animationSet);
            toastView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation ani_tran = new TranslateAnimation(0, 0, 80, 0);
                    AlphaAnimation ani_alp = new AlphaAnimation(1, 0);
                    AnimationSet animationSet = new AnimationSet(false);
                    ani_alp.setDuration(200);
                    ani_tran.setDuration(300);
                    animationSet.addAnimation(ani_tran);
                    animationSet.addAnimation(ani_alp);
                    animationSet.setFillAfter(true);
                    tv_toast.startAnimation(animationSet);
                    tv_toast.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
                            viewGroup.removeView(toastView);
                            isToast = false;
                        }
                    }, 700);

                }
            }, 2000);
        } else {
            tv_toast.setText(text);
        }
    }

}

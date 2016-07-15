package com.example.fupei.pythonapi.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.fupei.pythonapi.R;
import com.example.fupei.pythonapi.base.BaseActivity;

/**
 * Created by FuPei on 2016/7/15.
 */
public class ToastUtil {

    private static boolean isToast = false;
    private static View toastView;
    private static TextView tv_toast;

    public static void show(BaseActivity activity, String text) {
        if(!isToast) {
            isToast = true;
            toastView = LayoutInflater.from(activity).inflate(R.layout.toastview, null);
            tv_toast = (TextView) toastView.findViewById(R.id.tv_toast);
            tv_toast.setText(text);
            TranslateAnimation ani = new TranslateAnimation(0, 0, 0, 80);
            AlphaAnimation ani_alp = new AlphaAnimation(0, 1);
            ani_alp.setDuration(1000);
            ani.setDuration(500);
            final ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
            viewGroup.addView(toastView);
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
                    AnimationSet animationSet = new AnimationSet(true);
                    ani_alp.setDuration(500);
                    ani_tran.setDuration(1000);
                    animationSet.addAnimation(ani_tran);
                    animationSet.addAnimation(ani_alp);
                    animationSet.setFillAfter(true);
                    animationSet.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            viewGroup.removeView(toastView);
                            isToast = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    tv_toast.startAnimation(animationSet);

                }
            }, 2000);
        } else {
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
            viewGroup.removeView(toastView);
            isToast = false;
            show(activity, text);
        }
    }

}

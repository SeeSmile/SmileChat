package com.example.fupei.pythonapi.views;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by FuPei on 2016/7/15.
 */
public class ToastCustom {

    private static final int MESSAGE_TIMEOUT = 2;
    private WindowManager wdm;
    private double time;
    private View mView;
    private WindowManager.LayoutParams params;
    private WorkerHandler mHandler;

    private ToastCustom(Context context, String text, double time) {
        wdm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mHandler = new WorkerHandler();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mView = toast.getView();
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.windowAnimations = toast.getView().getAnimation().INFINITE;

        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        this.time = time;
    }

    public static ToastCustom makeText(Context context, String text, double time) {
        ToastCustom toastCustom = new ToastCustom(context, text, time);
        return toastCustom;
    }

    public void show() {
        wdm.addView(mView, params);
        mHandler.sendEmptyMessageDelayed(MESSAGE_TIMEOUT, (long) (time * 1000));
    }

    public void cancel() {
        wdm.removeView(mView);
    }

    private class WorkerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_TIMEOUT:
                    cancel();
                    break;
            }
        }
    }

}

package com.joyin.volleydemo.utils.ui;

import android.widget.Toast;

import com.joyin.volleydemo.app.MyApplication;

/**
 * Created by joyin on 16-4-3.
 */
public class ToastUtil {
    private ToastUtil() {

    }

    private static Toast mToast;

    public static void show(int resId) {
        show(MyApplication.getInstance().getString(resId));
    }

    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}

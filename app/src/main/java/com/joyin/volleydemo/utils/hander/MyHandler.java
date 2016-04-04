package com.joyin.volleydemo.utils.hander;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by joyin on 16-4-3.
 */
public class MyHandler<T extends IHandleMessage> extends Handler {

    private WeakReference<T> mTarget;

    public MyHandler(T t) {
        mTarget = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        T target = mTarget.get();
        if (target != null) {
            target.onHandleMessage(msg);
        }
    }
}
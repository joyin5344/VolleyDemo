package com.joyin.volleydemo.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.joyin.volleydemo.utils.hander.IHandleMessage;
import com.joyin.volleydemo.utils.hander.MyHandler;

/**
 * Created by joyin on 16-4-3.
 */
abstract public class BaseActivity extends AppCompatActivity implements IHandleMessage {

    public MyHandler<BaseActivity> mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyHandler<>(this);
    }

    @Override
    public void onHandleMessage(Message msg) {

    }
}

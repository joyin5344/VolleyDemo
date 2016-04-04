package com.joyin.volleydemo.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.joyin.volleydemo.utils.parse.xml.ErrorCodeParser;

/**
 * Created by joyin on 16-4-3.
 */
public class MyApplication extends Application {

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(this);
        ErrorCodeParser.init();
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static RequestQueue getRequestQueue() {
        return mInstance.mRequestQueue;
    }
}

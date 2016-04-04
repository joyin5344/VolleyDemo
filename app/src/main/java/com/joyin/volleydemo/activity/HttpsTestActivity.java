package com.joyin.volleydemo.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.android.volley.Request;
import com.joyin.volleydemo.R;
import com.joyin.volleydemo.utils.network.NetworkError;
import com.joyin.volleydemo.utils.network.RequestHandler;

/**
 * Created by joyin on 16-4-4.
 */
public class HttpsTestActivity extends BaseActivity {
    TextView mTvResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_https);
        initViews();
        initData();
    }

    private void initData() {
        String url = "https://kyfw.12306.cn/otn/";
        RequestHandler.addRequestWithDialog(
                Request.Method.GET, HttpsTestActivity.this,
                mHandler, RESULT_GET_HTTPS_RESULT, null, url, null, null);
    }

    private void initViews() {
        mTvResult = (TextView) findViewById(R.id.tv_https_result);
    }

    private static final int RESULT_GET_HTTPS_RESULT = 102;

    @Override
    public void onHandleMessage(Message msg) {
        switch (msg.what) {
            case RESULT_GET_HTTPS_RESULT:
                mTvResult.setText((String) msg.obj);
                break;
            case NetworkError.NET_ERROR_VOLLEY:
                mTvResult.setText("network error");
                break;
        }
    }
}

package com.joyin.volleydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.joyin.volleydemo.R;
import com.joyin.volleydemo.data.api.IpInfo;
import com.joyin.volleydemo.utils.network.NetworkError;
import com.joyin.volleydemo.utils.network.RequestHandler;
import com.joyin.volleydemo.view.dialog.MessageDialog;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    TextView mTvCountry, mTvCountryId, mTvIP;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews() {
        mTvCountry = (TextView) findViewById(R.id.tv_country);
        mTvCountryId = (TextView) findViewById(R.id.tv_country_id);
        mTvIP = (TextView) findViewById(R.id.tv_ip);
    }

    private void initData() {
        String url = "http://ip.taobao.com/service/getIpInfo.php";
        Map<String, String> params = new HashMap<>();
        params.put("ip", "2dss3");
        RequestHandler.addRequestWithDialog(Request.Method.GET, MainActivity.this, mHandler, RESULT_GET_IP_INFO, null, url, params, null);
    }

    private void setIpInfoToView(IpInfo ipInfo) {
        mTvCountry.setText(ipInfo.getData().getCountry());
        mTvCountryId.setText(ipInfo.getData().getCountry_id());
        mTvIP.setText(ipInfo.getData().getIp());
    }

    private static final int RESULT_GET_IP_INFO = 101;

    @Override
    public void onHandleMessage(Message msg) {
        switch (msg.what) {
            case RESULT_GET_IP_INFO:
                String result = (String) msg.obj;
                Log.d("demo", result);
                IpInfo ipInfo = JSON.parseObject(result, IpInfo.class);
                setIpInfoToView(ipInfo);
                break;
            case NetworkError.NET_ERROR_CUSTOM:
                mTvCountry.setText("获取请求失败");
                MessageDialog dialog = new MessageDialog(MainActivity.this);
                dialog.setMessage("获取请求失败了");
                dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, HttpsTestActivity.class));
                    }
                });
                dialog.show();
                break;
        }
    }
}

package com.joyin.volleydemo.utils.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.joyin.volleydemo.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Hashtable;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by joyin on 16-4-5.
 */
public class RequestManager {

    private RequestManager() {

    }

    private static RequestManager instance;

    public RequestQueue mRequestQueue;

    public RequestManager(Context context) {
        mRequestQueue = newRequestQueue(context);
    }

    public static RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager(context);
        }
        return instance;
    }


    public static HurlStack getSelfSignSslOkHttpStack(Context context) {

        String[] hosts = {"kyfw.12306.cn"};
        int[] certRes = {R.raw.kyfw};
        String[] certPass = {"asdfqaz"};

        try {
            Hashtable<String, SSLSocketFactory> socketFactoryMap = new Hashtable<>(hosts.length);
            for (int i = 0; i < certRes.length; i++) {
                int res = certRes[i];
                String password = certPass[i];
                SSLSocketFactory sslSocketFactory = createSSLSocketFactory(context, res, password);
                socketFactoryMap.put(hosts[i], sslSocketFactory);
            }
            HurlStack stack = new SelfSignSslOkHttpStack(socketFactoryMap);

            return stack;
        } catch (Exception e) {
            return null;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory(Context context, int res, String password)
            throws CertificateException, NoSuchAlgorithmException, IOException,
            KeyStoreException, KeyManagementException {
        InputStream inputStream = context.getResources().openRawResource(res);
        KeyStore keyStore = KeyStore.getInstance("BKS");
        keyStore.load(inputStream, password.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
        return sslContext.getSocketFactory();
    }

    private RequestQueue newRequestQueue(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context, getSelfSignSslOkHttpStack(context));
        return requestQueue;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public void addRequest(Request request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        } else if (!TextUtils.isEmpty(request.getUrl())) {
            request.setTag(request.getUrl());
        }
        mRequestQueue.add(request);
    }
}
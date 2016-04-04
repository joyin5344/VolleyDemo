package com.joyin.volleydemo.utils.network;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.joyin.volleydemo.utils.ui.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by joyin on 16-4-3.
 */
public class NetworkError {

    public static HashMap<String, String> mErrorMap = null;

    public static final int NET_ERROR_VOLLEY = -2;
    public static final int NET_ERROR_CUSTOM = -1;

    /**
     * 网络请求的bundle参数分析如下
     * ignoreError (boolean，默认false)，是否忽略所有errorCode，如后台调用
     * ignoreToastErrorCode （ArrayList<String>），list里面的errorCode会被忽略掉
     */
    public static void error(String errorCode, JSONObject jsonObject, Bundle bundle) {
        if (bundle != null) {
            // ignoreError若为true，则忽略所有errorCode
            if (bundle.getBoolean("ignoreError", false)) {
                // 该请求无需错误处理
                return;
            }
        }

        if (mErrorMap == null) {
            return;
        }

        if (!checkIgnoreCodes(bundle, errorCode)) {
            parseDefaultErrorCode(errorCode, jsonObject);
        }
    }

    /**
     * 遍历code，弹出对应错误信息Toast
     */
    private static void parseDefaultErrorCode(String errorCode, JSONObject jsonObject) {
        if (mErrorMap != null && mErrorMap.containsKey(errorCode)) {
            ToastUtil.show(mErrorMap.get(errorCode));
            return;
        }
        ToastUtil.show(jsonObject.toString());
    }

    /**
     * 检查该code是否需忽略
     *
     * @return 验证是否通过
     */
    private static boolean checkIgnoreCodes(Bundle bundle, String errorCode) {
        if (bundle != null) {
            // 若errorCode存在于该list，则由调用者自己处理
            ArrayList<String> ignoreList = bundle.getStringArrayList("ignoreToastErrorCode");
            if (ignoreList != null && !ignoreList.isEmpty()) {
                if (ignoreList.contains(errorCode)) {
                    return true;
                }
            }
        }
        return false;
    }
}

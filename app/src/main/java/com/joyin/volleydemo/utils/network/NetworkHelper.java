package com.joyin.volleydemo.utils.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joyin on 16-4-2.
 */
public class NetworkHelper {

    /**
     * 将参数转换为字符串
     */
    public static String convertMapToString(Map<String, String> params) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<>(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }
        }
        return content.toString();
    }

    /**
     * 講參數进行URLEncode编码转换
     * @param params
     * @return
     */
    public static Map<String, String> getURLEncodeParams(Map<String, String> params) {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            try {
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * 拼接url和参数，用于Get请求
     * @param url
     * @param params
     * @return
     */
    public static String getUrlWithParams(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        params = getURLEncodeParams(params);
        String paramsStr = convertMapToString(params);
        if (!url.endsWith("?")) {
            url += "?";
        }
        url += paramsStr;
        return url;
    }
}

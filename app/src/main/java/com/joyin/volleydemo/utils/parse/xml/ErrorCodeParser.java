package com.joyin.volleydemo.utils.parse.xml;

import android.text.TextUtils;
import android.util.Xml;

import com.joyin.volleydemo.app.MyApplication;
import com.joyin.volleydemo.utils.network.NetworkError;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by joyin on 16-4-3.
 */
public class ErrorCodeParser {

    public static void init() {
        try {
            NetworkError.mErrorMap = getErrorCodeMessageMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, String> getErrorCodeMessageMap() throws IOException, XmlPullParserException {
        HashMap<String, String> map = null;

        XmlPullParser parser = Xml.newPullParser();
        InputStream in = MyApplication.getInstance().getAssets().open("configs/return_codes.xml");
        parser.setInput(in, "UTF-8");
        int eventType = parser.getEventType();

        String key = "";
        String value = "";

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String nodeName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    map = new HashMap<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (nodeName.equals("code")) {
                        key = parser.nextText();
                    } else if (nodeName.equals("data")) {
                        value = parser.nextText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (nodeName.equals("item") && !TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                        map.put(key, value);
                    }
                    break;
            }
            eventType = parser.next();
        }
        return map;
    }
}

package jp.co.ienter.mopros.utils;

import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;

import java.util.Map;

import jp.co.ienter.mopros.services.requests.JSONBaseRequest;

public class DebugUtils {

    private static final String TAG = DebugUtils.class.getSimpleName();

    public void logParams(Map<String, String> params, String url) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            sb.append("\t\t").append(key).append(":").append(params.get(key)).append(",\n");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        Log.d(TAG, "url = " + url + "\n params = \n" + sb.toString());
    }

    public void logParams(JSONBaseRequest mRequest) {

    }

    public static void logDebugs(String s) {
        Log.d(TAG, "logDebugs: \n" + s);
    }
}

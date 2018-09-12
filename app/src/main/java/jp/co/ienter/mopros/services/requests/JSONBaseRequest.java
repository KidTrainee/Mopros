package jp.co.ienter.mopros.services.requests;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;

import jp.co.ienter.mopros.utils.DebugUtils;


public class JSONBaseRequest extends JsonObjectRequest {

    private String TAG = getClass().getSimpleName();
    private final String joStr;

    public JSONBaseRequest(JSONObject jo, String url, final IResponseJSONCallback callback) {
        super(Request.Method.POST, url, jo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError(new Throwable("Cannot connect to network"));
                } else if (error instanceof ServerError) {
                    callback.onError(new Throwable("Server Error"));
                } else if (error instanceof AuthFailureError) {
                    callback.onError(new Throwable("Auth Failure Error"));
                } else if (error instanceof ParseError) {
                    callback.onError(new Throwable("Parse Error"));
                } else if (error instanceof TimeoutError) {
                    callback.onError(new Throwable("Time Out Error"));
                }
            }
        });
        joStr = jo.toString() + "\n" + url;
        DebugUtils.logDebugs(joStr);
    }

    @Override
    public String toString() {
        return joStr;
    }

    public interface IResponseJSONCallback {
        void onSuccess(JSONObject response);
        void onError(Throwable errors);
    }
}

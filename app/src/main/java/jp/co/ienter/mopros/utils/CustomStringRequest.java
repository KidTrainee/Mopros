package jp.co.ienter.mopros.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thanhnv on 7/20/18.
 */

    public class CustomStringRequest extends StringRequest {

    public CustomStringRequest(int method,final String url, final IResponseStringCallback callback) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response.replace(",\"ErrorList\":[]", ""));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError("Cannot connect to network");
                } else if (error instanceof ServerError) {
                    callback.onError("Error");
                } else if (error instanceof AuthFailureError) {
                    callback.onError("Error");
                } else if (error instanceof ParseError) {
                    callback.onError("Error");
                } else if (error instanceof NoConnectionError) {
                    callback.onError("Error");
                } else if (error instanceof TimeoutError) {
                    callback.onError("Time Out Error");
                } else {
                    callback.onError(error.getMessage());
                }
            }
        });
        setRetryPolicy(new DefaultRetryPolicy(AppConstants.VOLLEY_TIMEOUT, AppConstants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public CustomStringRequest(boolean logout, int method,final String url, final IResponseStringCallback callback) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response.replace(",\"ErrorList\":[]", ""));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String responseData = error.getMessage() == null ? "" : error.getMessage();
                Log.i("Error Call:",url+" ~ "+ responseData);
                callback.onError(responseData);
            }
        });
        setRetryPolicy(new DefaultRetryPolicy(AppConstants.VOLLEY_TIMEOUT, AppConstants.VOLLEY_MAX_NUM_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();

        return headers;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            volleyError = new VolleyError(new String(volleyError.networkResponse.data));
        }
        return volleyError;
    }


    public interface IResponseStringCallback {
        void onSuccess(String response);
        void onError(String errors);
    }
}

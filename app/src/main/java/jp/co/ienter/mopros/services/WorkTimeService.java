package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

/**
 * Created by thanhnv on 8/6/18.
 */

public class WorkTimeService {
    private final String TAG = SortService.class.getSimpleName();
    private static WorkTimeService sInstance = new WorkTimeService();

    public static WorkTimeService getInstance(){
        if(sInstance == null){
            sInstance = new WorkTimeService();
        }
        return sInstance;
    }

    public void updateWorkTime(final String id, final String haso_date, final String uriWorkTime, final LoginCallback callback){
        Log.d("WorkTimeService.class", "Start work ======>API : " +  ConfigAPIs.getInstance().login());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                uriWorkTime, new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        JSONObject jsonObject = new JSONObject(response);
                        if(ConfigAPIs.getInstance().startWorkTime().equalsIgnoreCase(uriWorkTime)) {
                            callback.onSuccess(jsonObject.getJSONObject("result").getString("clockin_time"));
                        }else{
                            callback.onSuccess(jsonObject.getJSONObject("result").getString("clockout_time"));
                        }
                    } else {
                        callback.onError(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(String.valueOf(e));
                }
            }

            @Override
            public void onError(String errors) {
                Log.d("Response: ",errors);
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date", haso_date);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);
    }
    public void getStatusWorkTime(final String id, final String haiso_date, final LoginCallback callback){

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().statusWorkTime(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        JSONObject jsonObject = new JSONObject(response);
                        jsonObject.getJSONObject("result").getString("is_started");
                        callback.onSuccess(jsonObject.getJSONObject("result").getString("is_started")+jsonObject.getJSONObject("result").getString("is_finished")+jsonObject.getJSONObject("result").getString("clock_in_time"));
                    } else {
                        callback.onError(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(String.valueOf(e));
                }
            }

            @Override
            public void onError(String errors) {
                Log.d("Response: ",errors);
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date",haiso_date);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }
}

package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.interfaces.StatusCallback;
import jp.co.ienter.mopros.models.DeliveryStatus;
import jp.co.ienter.mopros.models.LoadingStatusModel;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

/**
 * Created by donghv on 8/28/18.
 */

public class StatusService {

    private final String TAG = getClass().getName();
    private static StatusService sInstance = new StatusService();

    public static StatusService getInstance(){
        if(sInstance == null){
            sInstance = new StatusService();
        }
        return sInstance;
    }
    public void getStatus(final String id, final String haso_date, final StatusCallback callback){

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getStatus(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    Log.d(TAG, response);
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        JSONObject jsonObject = new JSONObject(response);
                        ArrayList<DeliveryStatus> listStatus =  new Gson().fromJson(jsonObject.getString("result"),
                                new TypeToken<List<DeliveryStatus>>(){}.getType());
                        callback.onSuccess(listStatus);
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
//                callback.onError(errors);
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

    /**
     * Loading status for action 積込作業報告(Chat Tai)
     * @param id
     * @param haisoDate
     * @param callback
     */
    public void loadingStatus(final String id,final String haisoDate,final LoadingStatusCallback callback){
        Log.d(TAG, "call api ----> " + ConfigAPIs.getInstance().getLoadingStatusUrl());
        Log.d(TAG, "id : " + id + " haisoDate : " + haisoDate);
        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getLoadingStatusUrl(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                Log.d( TAG , "response : " + response);
                if(!response.isEmpty()){
                    Gson gson = new Gson();
                    LoadingStatusModel object = gson.fromJson(response, LoadingStatusModel.class);
                    if(object != null && object.getStatus() == 1 ){
                        //success
                        callback.onSuccess(object);
                        return;
                    } else if (object == null){
                        // exception parse json
                        callback.onError("Parse Json have exception");
                    } else {
                        // error response from server
                        callback.onError(object.getMessages());
                    }
                } else {
                    // maybe network exception
                    callback.onError("Network exception");
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
                params.put("id", id);
                params.put("haiso_date", haisoDate);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public interface LoadingStatusCallback{
        void onSuccess(LoadingStatusModel statusModel);
        void onError(String message);
    }
}

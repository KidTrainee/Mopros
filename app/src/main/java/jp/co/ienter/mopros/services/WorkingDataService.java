package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.models.LoadingStatusModel;
import jp.co.ienter.mopros.models.WorkingData;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

/**
 * call api 037
 */
public class WorkingDataService {

    private final String TAG = getClass().getName();

    public void getWorkingData(final String id, final String haisoDate, final IWorkingDataCallback callback){
        Log.d(TAG, "call API: " + ConfigAPIs.getInstance().getWorkingDataUrl());
        Log.d(TAG, "id : " + id + " haiso_date : " + haisoDate);
        CustomStringRequest rq = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getWorkingDataUrl(),
                new CustomStringRequest.IResponseStringCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d( TAG , "response : " + response);
                        if(!response.isEmpty()){
                            Gson gson = new Gson();
                            WorkingData object = gson.fromJson(response, WorkingData.class);
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
        MoprosApp.getInstance().addToRequestQueue(rq);
    }

    public interface IWorkingDataCallback{
        void onSuccess(WorkingData data);
        void onError(String message);
    }

}

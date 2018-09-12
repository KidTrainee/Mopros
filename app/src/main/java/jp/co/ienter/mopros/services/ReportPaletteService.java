package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.interfaces.IBasicApiCallback;
import jp.co.ienter.mopros.models.PaletteReportDataModel;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

public class ReportPaletteService {
    private final String TAG = getClass().getName();

    public void getPaletteMasterData(final String data_type,final IBasicApiCallback callback){
        Log.d(TAG, "call api ----> " + ConfigAPIs.getInstance().getPaletteReportMasterUrl());
        Log.d(TAG, "data_type : " + data_type );
        CustomStringRequest rq = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getPaletteReportMasterUrl(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                Log.d( TAG , "response : " + response);
                if(!response.isEmpty()){
                    Gson gson = new Gson();
                    PaletteReportDataModel object = gson.fromJson(response, PaletteReportDataModel.class);
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
                params.put("data_type", data_type);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(rq);
    }

}

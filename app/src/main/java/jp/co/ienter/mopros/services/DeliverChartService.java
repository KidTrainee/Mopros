package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.interfaces.IDeliverChartCallback;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

public class DeliverChartService {

    private final String TAG = DeliverChartService.class.getName();

    /**
     * this method use to get deliver chart info.
     * DeliverChartModel response in callback
     * @param id
     * @param supplierCode
     * @param callback
     */
    public void getDeliverChartInfo(final String id, final String supplierCode, final IDeliverChartCallback callback){
        Log.d(TAG, "leaveApp ======>API : " +  ConfigAPIs.getInstance().getDeliverChartInfo());
        Log.d(TAG, "Parrams: id:" + id + " supplier_code:" + supplierCode);

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getDeliverChartInfo(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "response : " + response);
                    if(!response.isEmpty()){
                        Gson gson = new Gson();
                        DeliverChartModel deliverChartModel = gson.fromJson(response, DeliverChartModel.class);
                        if(deliverChartModel != null && deliverChartModel.getStatus() == 1 ){
                            //success
                            callback.onSuccess(deliverChartModel);
                            return;
                        } else if (deliverChartModel == null){
                            // exception parse json
                            callback.onError("Parse Json have exception");
                        } else {
                            // error response from server
                            callback.onError(deliverChartModel.getMessages());
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
                params.put("id",id);
                params.put("supplier_code", supplierCode);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

}

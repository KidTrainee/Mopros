package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.JsonHelper;
import jp.co.ienter.mopros.interfaces.IBasicApiNoDataCallback;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.DebugUtils;

public class NoDeliveryReportService {

    private String TAG = getClass().getName();


    /**
     * API 19 in document
     */
    public void noReportDeliver(JsonHelper jsonHelper, DeliverArrApi api, final IBasicApiNoDataCallback callback) throws JSONException {
        if (api == null || api.getTransport_data().length==0) {
            Log.d(TAG, "List MoprosDeliver input is Empty");
            return;
        }

//        JSONObject jsonObject = convertToJSONObject(id, haisoDate, data.get(0).getData_type(), data);
        JSONObject jsonObject = jsonHelper.convertDeliverArrApiToJO(api);
        DebugUtils.logDebugs(jsonObject.toString() + "\n" + ConfigAPIs.getInstance().getDeliverNoReportUrl());
        JsonObjectRequest rq = new JsonObjectRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getDeliverNoReportUrl(),
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                String messages = "";
                try {
                    messages = String.valueOf(response.get("messages"));
                    String status = String.valueOf(response.get("status"));
                    if (status.trim().equals("1") && messages.equals("")) {
                        callback.onSuccess();
                    } else {
                        callback.onError(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.onError(error.getMessage());
            }
        });
        MoprosApp.getInstance().addToRequestQueue(rq);

    }

    /**
     * @param id
     * @param haisoDate
     * @param dataType
     * @param arrData
     * @return JSONObject post to server
     * @throws JSONException
     */
    private JSONObject convertToJSONObject(String id, String haisoDate, String dataType, List<MoprosDelivery> arrData) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("haiso_date", haisoDate);
        jsonObject.put("data_type", dataType);
        JSONArray jsonArray = new JSONArray();
        for (MoprosDelivery item : arrData) {
            JSONObject objectDetail = new JSONObject();
            objectDetail.put("course_cd1_after", item.getCourse_code_1());
            objectDetail.put("course_cd2_after", item.getCourse_code_2());
            objectDetail.put("haiso_order_no", item.getHaiso_order_no());
            objectDetail.put("trip", item.getTrip());
            objectDetail.put("gosha", item.getGosha());
            String transportCode = "";
            if (item.getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER)) {
                transportCode = item.getNonyu_code();
            } else {
                transportCode = item.getShuka_code();
            }
            objectDetail.put("transport_code", transportCode);
            jsonArray.put(objectDetail);
        }
        jsonObject.put("transport_data", jsonArray);
        Log.d(TAG, jsonObject.toString());
        return jsonObject;
    }
}

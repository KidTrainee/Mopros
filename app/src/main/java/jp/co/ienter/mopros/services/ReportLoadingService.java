package jp.co.ienter.mopros.services;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.ISaveDeliveryListCallback;
import jp.co.ienter.mopros.interfaces.ReportDetailListCallBack;
import jp.co.ienter.mopros.interfaces.ReportLoadingCallback;
import jp.co.ienter.mopros.interfaces.ReportedDataCallBack;
import jp.co.ienter.mopros.interfaces.SelectReportListCallBack;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.ReportLoading;
import jp.co.ienter.mopros.models.ReportedData;
import jp.co.ienter.mopros.models.SelectReportList;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.models.apis.PaletteResultReport;
import jp.co.ienter.mopros.models.apis.SelectDataResultReport;
import jp.co.ienter.mopros.models.apis.SelectRadioResultReport;
import jp.co.ienter.mopros.services.requests.JSONBaseRequest;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

/**
 * Created by donghv on 8/3/18.
 */

public class ReportLoadingService {
    private final String TAG = ReportLoadingService.class.getSimpleName();
    private static ReportLoadingService sInstance = new ReportLoadingService();

    public static ReportLoadingService getInstance(){
        if(sInstance == null){
            sInstance = new ReportLoadingService();
        }
        return sInstance;
    }

    public void startLoading(final String id, final String haisoDate, final ReportLoadingCallback callback){
        Log.d("hayhay", "leaveApp ======>API : " +  ConfigAPIs.getInstance().startLoading());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().startLoading(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        callback.onSuccess(response);
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
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date",haisoDate);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

    public void endLoading(final String id, final String haisoDate, final ReportLoadingCallback callback){
        Log.d("hayhay", "leaveApp ======>API : " +  ConfigAPIs.getInstance().endLoading());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().endLoading(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        callback.onSuccess(response);
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
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date",haisoDate);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }
    public void getReportDetailList(final String sagyo_code, final ReportDetailListCallBack callback){
        Log.d("hayhay", "leaveApp ======>API : " +  ConfigAPIs.getInstance().getReportDetailList());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getReportDetailList(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        ArrayList<ReportLoading> listReportDeatail = new Gson().fromJson(myObject.getString("result").toString(), new TypeToken<List<ReportLoading>>(){}.getType());
                        callback.onSuccess(listReportDeatail);
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
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sagyo_code",sagyo_code);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

    public void getReportedData(final String id, final String haisoDate, final ReportedDataCallBack callback){
        Log.d("hayhay", "leaveApp ======>API : " +  ConfigAPIs.getInstance().getReportedData());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getReportedData(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    Log.d(TAG, response);
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        ArrayList<ReportedData> listReportedData = new Gson().fromJson(myObject.getString("work_result").toString(), new TypeToken<List<ReportedData>>() {}.getType());
                        ArrayList<MoprosDelivery> listDelivery = new Gson().fromJson(myObject.getString("result").toString(), new TypeToken<List<MoprosDelivery>>() {}.getType());
                        callback.onSuccess(myObject.getInt("loading_time"),listReportedData,listDelivery);
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
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date",haisoDate);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }
    public void getSelectReportList(final SelectReportListCallBack callback){

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getSelectReportList(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        ArrayList<SelectReportList> listSelectReport = new Gson().fromJson(myObject.getString("result").toString(), new TypeToken<List<SelectReportList>>(){}.getType());
                        callback.onSuccess(listSelectReport);
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
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

    public void noReportLoading(final String id, final String haisoDate, final ReportLoadingCallback callback) {

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().noReportLoading(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if (messages.equals("")) {
                        callback.onSuccess(response);
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
                callback.onError(errors);
            }
        }) {
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

    public  void regReportLoading(final String id, final String haiso_date,final ArrayList<ReportedData> reportedData,
                                        final ArrayList<SelectableDelivery> listLoadingReport, final ISaveDeliveryListCallback callback) throws JSONException {
        JSONObject jsonObject = convertJson(id, haiso_date,reportedData, listLoadingReport);
        JSONBaseRequest request=new JSONBaseRequest(jsonObject, ConfigAPIs.getInstance().regLoadingReport(),
                new JSONBaseRequest.IResponseJSONCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            if(response.getString("status").equals("1") && response.getString("messages").equals("")){
                                callback.onSuccess();
                            } else{
                                callback.onError(new Throwable(response.getString("messages")));
                            }
                        } catch (JSONException e) {
                            callback.onError(e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable errors) {
                        callback.onError(errors);
                    }
                });
        MoprosApp.getInstance().addToRequestQueue(request);
    }

    public  void regReportDecision(final String id, final String haiso_date, final String data_type, final ArrayList<SelectableDelivery> mSelectableDelivery, final ArrayList<SelectDataResultReport> mSelectDataResultReport,
                                   final ArrayList<SelectRadioResultReport> mSelectRadioResultReport,
                                   final ArrayList<PaletteResultReport> mPaletteResultReport, final ISaveDeliveryListCallback callback) throws JSONException {
        JSONObject jsonObject = convertJsonDelivery(id, haiso_date,data_type,mSelectableDelivery,mSelectDataResultReport, mSelectRadioResultReport,mPaletteResultReport);
        JSONBaseRequest request=new JSONBaseRequest(jsonObject, ConfigAPIs.getInstance().regReportDecision(),
                new JSONBaseRequest.IResponseJSONCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            if(response.getString("status").equals("1") && response.getString("messages").equals("")){
                                callback.onSuccess();
                            } else{
                                callback.onError(new Throwable(response.getString("messages")));
                            }
                        } catch (JSONException e) {
                            callback.onError(e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable errors) {
                        callback.onError(errors);
                    }
                });
        MoprosApp.getInstance().addToRequestQueue(request);
    }

    private static JSONObject convertJsonDelivery(String id, String haiso_date, String data_type, ArrayList<SelectableDelivery> mSelectableDelivery,ArrayList<SelectDataResultReport> mSelectDataResultReport, ArrayList<SelectRadioResultReport> mSelectRadioResultReport, ArrayList<PaletteResultReport> mPaletteResultReport) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("haiso_date", haiso_date);
        jsonObject.put("data_type",Integer.parseInt(data_type));

        //transport_data
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < mSelectableDelivery.size(); i++) {
            MoprosDelivery object = mSelectableDelivery.get(i);
            JSONObject objectDetail = new JSONObject();
            String course_cd1_affter = object.getCourse_code_1() == null ? "" : object.getCourse_code_1();
            objectDetail.put("course_cd1_after", course_cd1_affter);
            String course_cd2_affter = object.getCourse_code_2() == null ? "" : object.getCourse_code_2();
            objectDetail.put("course_cd2_after", course_cd2_affter);
            if (object.getHaiso_order_no() != null)
                objectDetail.put("haiso_order_no", object.getHaiso_order_no());
            String trip = object.getTrip() == null ? "" : object.getTrip();
            objectDetail.put("trip", trip);
            objectDetail.put("nonyu_cd",object.getNonyu_code());
            jsonArray.put(objectDetail);
        }
        jsonObject.put("transport_data", jsonArray);

        //spinner
        JSONArray jsonArray2 = new JSONArray();
        for (int i =0; i<mSelectDataResultReport.size();i++){
            SelectDataResultReport object2 = mSelectDataResultReport.get(i);
            JSONObject objectDetail2 = new JSONObject();
            if(!object2.getSagyo_time().equals("00")){
                objectDetail2.put("sagyo_code",object2.getSagyo_code());
                objectDetail2.put("sagyo_time",object2.getSagyo_time());
                jsonArray2.put(objectDetail2);
            }
        }
        jsonObject.put("select_data",jsonArray2);

        //radio
        JSONArray jsonArray3 = new JSONArray();
        for (int i =0; i<mSelectRadioResultReport.size();i++){
            SelectRadioResultReport object3 = mSelectRadioResultReport.get(i);
            JSONObject objectDetail3 = new JSONObject();
            if(!object3.getChange_kbn().equals("-1")){
                objectDetail3.put("sagyo_code",object3.getSagyo_code());
                objectDetail3.put("sagyo_time",object3.getChange_kbn());
                jsonArray3.put(objectDetail3);
            }
        }
        jsonObject.put("radio_data",jsonArray3);

        //palette
        JSONArray jsonArray4 = new JSONArray();
        for (int i =0; i<mPaletteResultReport.size();i++){
            PaletteResultReport object4 = mPaletteResultReport.get(i);
            JSONObject objectDetail4 = new JSONObject();
//            if(!object3.getChange_kbn().equals("-1")){
//                objectDetail3.put("sagyo_code",object3.getSagyo_code());
//                objectDetail3.put("sagyo_time",object3.getChange_kbn());
//                jsonArray3.put(objectDetail3);
//            }
            objectDetail4.put("palette_no",object4.getPalette_no());
            objectDetail4.put("jitsu_kago_cnt",object4.getJitsu_kago_cnt());
            objectDetail4.put("kaishu_kago_cnt",object4.getKaishu_kago_cnt());
            objectDetail4.put("item_code",object4.getItem_code());
            objectDetail4.put("shuka_cnt",object4.getShuka_cnt());
        }
        jsonObject.put("palette_data",jsonArray4);

        Log.d("Json: ", jsonObject.toString());
        return jsonObject;
    }

    /**
     *
     * @param id
     * @param haiso_date
     * @param listLoadingReport
     * @return JSONObject post to server
     * @throws JSONException
     */
    private static JSONObject convertJson(String id, String haiso_date,ArrayList<ReportedData> reportedData,ArrayList<SelectableDelivery> listLoadingReport) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("haiso_date", haiso_date);
        JSONArray jsonArray2 = new JSONArray();
        for (int i =0; i<reportedData.size();i++){
            ReportedData object2 = reportedData.get(i);
            JSONObject objectDetail2 = new JSONObject();
            if(!object2.getSagyo_time().equals("00")){
                objectDetail2.put("sagyo_code",object2.getSagyo_code());
                objectDetail2.put("sagyo_time",object2.getSagyo_time());
                jsonArray2.put(objectDetail2);
            }
        }
        jsonObject.put("work_data",jsonArray2);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < listLoadingReport.size(); i++) {
            MoprosDelivery object = listLoadingReport.get(i);
            JSONObject objectDetail = new JSONObject();
            String course_cd1_affter = object.getCourse_code_1() == null ? "" : object.getCourse_code_1();
            objectDetail.put("course_cd1_after", course_cd1_affter);
            String course_cd2_affter = object.getCourse_code_2() == null ? "" : object.getCourse_code_2();
            objectDetail.put("course_cd2_after", course_cd2_affter);
            if (object.getHaiso_order_no() != null)
                objectDetail.put("haiso_order_no", object.getHaiso_order_no());
            String trip = object.getTrip() == null ? "" : object.getTrip();
            objectDetail.put("trip", trip);
            objectDetail.put("nonyu_cd",object.getNonyu_code());
            jsonArray.put(objectDetail);
        }
        jsonObject.put("transport_data", jsonArray);
        Log.d("Json: ", jsonObject.toString());
        return jsonObject;
    }
}

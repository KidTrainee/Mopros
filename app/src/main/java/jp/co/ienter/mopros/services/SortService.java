package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.JsonHelper;
import jp.co.ienter.mopros.interfaces.IBasicApiNoDataCallback;
import jp.co.ienter.mopros.interfaces.IDeliveryServiceApi;
import jp.co.ienter.mopros.interfaces.IListSortCallBack;
import jp.co.ienter.mopros.interfaces.IRegSortDeleveryCallBack;
import jp.co.ienter.mopros.interfaces.ListCargoCallback;
import jp.co.ienter.mopros.models.MoprosCargo;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.CargoApi;
import jp.co.ienter.mopros.models.apis.UpdateCargoApi;
import jp.co.ienter.mopros.services.requests.JSONBaseRequest;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.CustomStringRequest;
import jp.co.ienter.mopros.utils.DebugUtils;

/**
 * Created by thanhnv on 8/3/18.
 */

public class SortService implements IDeliveryServiceApi {

    protected String TAG = getClass().getSimpleName();
    private static SortService sInstance = new SortService();
    private final int NO_DELIVERY_FLAG_BUTTON_DONE = 0;

    public static SortService getInstance() {
        if (sInstance == null) {
            sInstance = new SortService();
        }
        return sInstance;
    }

    @Override
    public void getListInfo(final String id, final String haso_date, final IListSortCallBack callback) {
        Log.d(TAG, "leaveApp ======>API : " + ConfigAPIs.getInstance().getSortInfor());
        DebugUtils.logDebugs("id = " + id + "\thaiso_date = " + haso_date + "\turl = " + ConfigAPIs.getInstance().getSortInfor());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getSortInfor(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                DebugUtils.logDebugs(response);
                try {
                    Log.d(TAG, response);
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if (messages.equals("")) {
                        JSONObject jsonObject = new JSONObject(response);

                        ArrayList<MoprosDelivery> listSort = new Gson().fromJson(jsonObject.getString("result"),
                                new TypeToken<List<MoprosDelivery>>() {
                                }.getType());
                        if (!listSort.isEmpty()) callback.onSuccess(listSort);
                        else callback.onError("No Data");
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
                Log.d("Response: ", errors);
                callback.onError(errors);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("haiso_date", haso_date);

                return params;
            }
        };

        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

    public void updateNoDelivery(final String id, final String haso_date,
                                 final String course_code_1, final String course_code_2, final String haiso_order_no, final String trip, final String kanryo_flg, final IRegSortDeleveryCallBack callback) {
        Log.d(TAG, "leaveApp ======>API : " + ConfigAPIs.getInstance().updateNoDeliveryUrl());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().updateNoDeliveryUrl(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    String status = String.valueOf(myObject.get("status"));
                    if (status.equals("1") && messages.equals("")) {
                        callback.onSuccess();
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
                Log.d("Response: ", errors);
                callback.onError(errors);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("haiso_date", haso_date);
                params.put("course_cd1_after", course_code_1);
                params.put("course_cd2_after", course_code_2);
                params.put("haiso_order_no", haiso_order_no);
                params.put("trip", trip);
                params.put("kanryo_flg", kanryo_flg);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void deletePickup(final String id, final String haso_date,
                             final String shuka_code,
                             final String sharyo_code,
                             final IBasicApiNoDataCallback callback) {
        Log.d(TAG, "leaveApp ======>API : " + ConfigAPIs.getInstance().getSortDeletePickup());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getSortDeletePickup(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    Log.d(TAG, response);
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    String status = String.valueOf(myObject.get("status"));
                    if (status.equals("1") && messages.equals("")) {
                        callback.onSuccess();
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
                Log.d("Response: ", errors);
                callback.onError(errors);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("haiso_date", haso_date);
                params.put("shuka_code", shuka_code);
                params.put("sharyo_code", sharyo_code);
//                params.put("shuka_name", shuka_name);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    public void regPickup(final String id, final String haso_date,
                          final String shuka_code,
                          final String sharyo_code,
                          final String shuka_name,
                          final IBasicApiNoDataCallback callback) {
        Log.d(TAG, "leaveApp ======>API : " + ConfigAPIs.getInstance().getSortRegisterPickup());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getSortRegisterPickup(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    Log.d(TAG, response);
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    String status = String.valueOf(myObject.get("status"));
                    if (status.equals("1") && messages.equals("")) {
                        callback.onSuccess();
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
                Log.d("Response: ", errors);
                callback.onError(errors);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("haiso_date", haso_date);
                params.put("shuka_code", shuka_code);
                params.put("shuka_name", shuka_name);
                params.put("sharyo_code", sharyo_code);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);
    }

    /**
     * use put list delivery after soft
     *
     * @param id
     * @param haiso_date
     * @param sortDataArr
     * @param callback
     * @throws JSONException
     */
    public void updateSort(final String id, final String haiso_date,
                           final ArrayList<MoprosDelivery> sortDataArr, final IRegSortDeleveryCallBack callback) throws JSONException {
        JSONObject jsonObject = convertJson(id, haiso_date, sortDataArr);
        JsonObjectRequest rq = new JsonObjectRequest(Request.Method.POST, ConfigAPIs.getInstance().getSortDeliveryData(),
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response : " + response);
                try {
                    if (response.getInt("status") == 1) {
                        if (response.getString("messages").isEmpty()) {
                            callback.onSuccess();
                        } else {
                            callback.onError(response.getString("messages"));
                        }
                    } else {
                        callback.onError(response.getString("messages"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response: ", "" + error.getMessage());
                callback.onError(error.getMessage());
            }
        });
        MoprosApp.getInstance().addToRequestQueue(rq);
    }

    /**
     * @param id
     * @param haiso_date
     * @param sortDataArr
     * @return JSONObject post to server
     * @throws JSONException
     */
    private JSONObject convertJson(String id, String haiso_date, ArrayList<MoprosDelivery> sortDataArr) throws JSONException {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("haiso_date", haiso_date);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < sortDataArr.size(); i++) {
            MoprosDelivery object = sortDataArr.get(i);
            JSONObject objectDetail = new JSONObject();
            String course_cd1_affter = object.getCourse_code_1() == null ? "" : object.getCourse_code_1();
            objectDetail.put("course_cd1_after", course_cd1_affter);
            String course_cd2_affter = object.getCourse_code_2() == null ? "" : object.getCourse_code_2();
            objectDetail.put("course_cd2_after", course_cd2_affter);
            if (object.getHaiso_order_no() != null)
                objectDetail.put("haiso_order_no", object.getHaiso_order_no());
            String trip = object.getTrip() == null ? "" : object.getTrip();
            objectDetail.put("trip", trip);
            if (object.getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER)) {
                objectDetail.put("shuka_code", "");
            } else {
                String shuka_code = object.getShuka_code() == null ? "" : object.getShuka_code();
                objectDetail.put("shuka_code", shuka_code);
            }
            if (object.getKanryo_flg() != null)
                objectDetail.put("kanryo_flg", object.getKanryo_flg());
            objectDetail.put("order_no", i);
            objectDetail.put("no_delivery_flg", NO_DELIVERY_FLAG_BUTTON_DONE);
            jsonArray.put(objectDetail);
        }
        jsonObject.put("transport_data", jsonArray);
        Log.d(TAG, jsonObject.toString());
        return jsonObject;
    }


    // {id: '001', haiso_date: '2018/02/15', selected_data:[{shuka_code: '301'}, {shuka_code: '303'}]}
    public void getCargoListData(final String id, final String haso_date,
                                 final CargoApi[] selected_data, final ListCargoCallback callback) {
        Log.d(TAG, "getCargoListDataUrl ======>API : " + ConfigAPIs.getInstance().getCargoListDataUrl());
        JsonHelper mJsonHelper = new JsonHelper(new Gson());
        JSONObject jo = mJsonHelper.convertCargoListToJO(new UpdateCargoApi(id, haso_date, selected_data));

        JSONBaseRequest mRequest = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getCargoListDataUrl(),
                new JSONBaseRequest.IResponseJSONCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {

                        try {
                            Log.d(TAG, response.toString());
                            String messages = response.getString("messages");
                            if (messages.equals("")) {
                                ArrayList<MoprosCargo> listSort = new Gson().fromJson(response.getString("result"),
                                        new TypeToken<List<MoprosCargo>>() {
                                        }.getType());
                                callback.onSuccess(listSort);
                            } else {
                                callback.onError(messages);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable errors) {
                    }
                });


        MoprosApp.getInstance().addToRequestQueue(mRequest);

    }
}

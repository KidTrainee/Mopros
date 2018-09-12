package jp.co.ienter.mopros.activity.deliver_report.data;

import android.app.Activity;
import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.JsonHelper;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.RegReturnResult;
import jp.co.ienter.mopros.models.apis.RegWorkingDataApi;
import jp.co.ienter.mopros.models.apis.ResultReportData;
import jp.co.ienter.mopros.models.apis.ResultReportWaitingTime;
import jp.co.ienter.mopros.models.apis.ReturnApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;
import jp.co.ienter.mopros.models.apis.StatusInfo;
import jp.co.ienter.mopros.models.apis.UpdateResult;
import jp.co.ienter.mopros.services.contracts.IDeliverReportService;
import jp.co.ienter.mopros.annotation.Remote;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.interfaces.IListSortCallBack;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.services.SortService;
import jp.co.ienter.mopros.services.requests.JSONBaseRequest;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.JsonKeys;

import static jp.co.ienter.mopros.utils.PreConditions.checkNotNull;

@Remote
public class RemoteDeliveryService implements IDeliverReportService {

    private static final String TAG = RemoteDeliveryService.class.getSimpleName();
    private Activity mActivity;

    private JsonHelper mJsonHelper;

    private static RemoteDeliveryService instance;

    public static RemoteDeliveryService getInstance(Activity activity) {
        if (instance == null) {
            instance = new RemoteDeliveryService(activity);
        }
        return instance;
    }

    public RemoteDeliveryService(Activity activity) {
        this.mActivity = activity;
        mJsonHelper = new JsonHelper(new Gson());
    }

    @Override
    public void getDeliveryList(BaseApi baseApi, final LoadDeliveryListCallback callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            SortService.getInstance().getListInfo(baseApi.getId(), baseApi.getHaiso_date(), new IListSortCallBack() {
                @Override
                public void onSuccess(ArrayList<MoprosDelivery> listSort) {
                    callback.onSuccess(listSort);
                }

                @Override
                public void onError(String error) {
                    callback.onError(error);
                }
            });
        }
    }

    @Override
    public void getSelectedDeliveryList(BaseApi api, final IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            // TODO: 8/27/18 implement when having api
            JSONObject jo = mJsonHelper.convertBaseApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getSelectReportList(),
                    new ResponseCallback(callback) {
                        @Override
                        public void onSuccess(JSONObject jo) {
                            super.onSuccess(jo);
                            if (mStatus != -100) {
                                switch (mStatus) {
                                    case 1:
                                        //メッセージなし（登録・更新成功）- No message (registration / update success)
                                        UpdateResult result = new UpdateResult();
                                        callback.onSuccess(result);
                                        break;
                                    default:
                                        callback.onError(new Throwable("status is not match any case"));
                                        break;
                                }
                            }
                        }
                    });
            sendRequest(request);
        }
    }

    @Override
    public void startBreak(final BaseApi api, final IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertBaseApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo,
                    ConfigAPIs.getInstance().getStartBreakUrl(),
                    new ResponseBreakCallback(callback));

            sendRequest(request);
        }
    }

    @Override
    public void finishBreak(BaseApi api, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertBaseApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo,
                    ConfigAPIs.getInstance().getEndBreakUrl(),
                    new ResponseBreakCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void pendingProcess(PendingDeliverApi api, final IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertPendingApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getPendingUrl(),
                    new ResponseCallback(callback) {
                        @Override
                        public void onSuccess(JSONObject jo) {
                            super.onSuccess(jo);
                            switch (mStatus) {
                                case 1:
                                    UpdateResult result = new UpdateResult();
                                    callback.onSuccess(result);
                                    break;
                                default:
                                    callback.onError(new Throwable(mMessages));
                                    break;
                            }
                        }
                    });
            sendRequest(request);
        }
    }

    @Override
    public void departureDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverArrApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getDeliverDepartureUrl(),
                    new ResponseDeliverCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void arrivalDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverArrApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getDeliverArrivalUrl(),
                    new ResponseDeliverCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void startDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverArrApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getDeliverStartUrl(),
                    new ResponseDeliverCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void endDeliver(DeliverArrApi updateApi, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverArrApiToJO(updateApi);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getDeliverEndUrl(),
                    new ResponseDeliverCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void getWorkingData(BaseApi api, final IUpdateCallback<List<MoprosDelivery>> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertBaseApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo,
                    ConfigAPIs.getInstance().getWorkingDataUrl(),
                    new ResponseCallback(callback) {
                        @Override
                        public void onSuccess(JSONObject jo) {
                            super.onSuccess(jo);
                            switch (mStatus) {
                                case 0:
                                    // fail
                                    callback.onError(new Throwable(mMessages));
                                    break;
                                case 1:
                                    // メッセージなし（登録・更新成功）(không có messege(đăng ký/update thành công)
                                    try {
                                        List<MoprosDelivery> result = new Gson().fromJson(jo.getJSONArray("result").toString(),
                                                new TypeToken<List<MoprosDelivery>>(){}.getType());
                                        callback.onSuccess(result);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        }
                    });
            sendRequest(request);
        }
    }

    @Override
    public void regReturn(ReturnApi api, final IUpdateCallback<RegReturnResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            JSONObject jo = mJsonHelper.convertReturnApi(api);
            JsonObjectRequest request = new JSONBaseRequest(jo,
                    ConfigAPIs.getInstance().getReturnUrl(), new ResponseCallback(callback) {
                @Override
                public void onSuccess(JSONObject jo) {
                    super.onSuccess(jo);
                    if (mStatus != -100) {
                        switch (mStatus) {
                            case 1:
                                //メッセージなし（登録・更新成功）- No message (registration / update success)
                                try {
                                    String[] arr = new Gson().fromJson(jo.getJSONArray(Const.RESULT).toString(), String[].class);
                                    RegReturnResult result = new RegReturnResult(arr);
                                    callback.onSuccess(result);
                                } catch (JSONException e) {
                                    try {
                                        String str = jo.getJSONObject(Const.RESULT).getString(JsonKeys.RETURN_TIME);
                                        RegReturnResult result = new RegReturnResult(new String[]{str});
                                        callback.onSuccess(result);
                                    } catch (JSONException e1) {
                                        e.printStackTrace();
                                        e1.printStackTrace();
                                        showError();
                                    }

                                }
                                break;
                            case 0:
                                // {"帰着の登録処理に失敗しました。"} Đã thất bại trong việc xử lý đăng ký return
                                showError();
                                break;
                        }
                    }
                }

                private void showError() {
                    if (mMessages != null && !mMessages.isEmpty())
                        callback.onError(new Throwable(mMessages));
                }

            });

            sendRequest(request);
        }
    }

    @Override
    public void reportDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback) {
        JSONObject jo = mJsonHelper.convertDeliverArrApiToJO(api);
        JsonObjectRequest request = new JSONBaseRequest(jo,
                ConfigAPIs.getInstance().getNoReportUrl(),
                new ResponseDeliverCallback(callback));
        sendRequest(request);
    }

    @Override
    public void noReportDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverArrApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo,
                    ConfigAPIs.getInstance().getNoReportUrl(),
                    new ResponseDeliverCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void getReachStatus(SimpleDeliverApi api, final IUpdateCallback<ResultReportWaitingTime[]> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverApi(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getReportWaitingTimeUrl(),
                    new ResponseCallback(callback) {
                        @Override
                        public void onSuccess(JSONObject jo) {
                            super.onSuccess(jo);
                            switch (mStatus) {
                                case 0:
                                    // {"運送到着時間の取得に失敗しました。"} Đã thất bại trong việc get thời gian tới nơi ship hàng
                                    callback.onError(new Throwable(mMessages));
                                    break;
                                case 1:
                                    // Thành công - Không có messege
                                    try {
                                        ResultReportWaitingTime[] result = new Gson().fromJson(jo.getJSONArray("result").toString(),
                                                ResultReportWaitingTime[].class);

                                        callback.onSuccess(result);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        callback.onError(e);
                                    }
                                    break;
                            }
                        }
                    });
            sendRequest(request);
        }
    }

    @Override
    public void checkDeliverStatus(SimpleDeliverApi api, final IUpdateCallback<StatusInfo> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverApi(api);
            JsonObjectRequest request = new JSONBaseRequest(jo,
                    ConfigAPIs.getInstance().getCheckStatusUrl(),
                    new ResponseCallback(callback) {
                        @Override
                        public void onSuccess(JSONObject jo) {
                            super.onSuccess(jo);
                            try {
                                JSONObject result = jo.getJSONObject("result");
                                if (result != null)
                                    callback.onSuccess(new Gson().fromJson(result.toString(), StatusInfo.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                callback.onError(e);
                            }
                        }
                    });
            sendRequest(request);
        }
    }

    @Override
    public void regWorkingData(RegWorkingDataApi api, IUpdateCallback<UpdateResult> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertRegWorkingDataApiToJO(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getRegWorkingDataUrl(),
                    new ResponseDeliverCallback(callback));
            sendRequest(request);
        }
    }

    @Override
    public void getReportData(SimpleDeliverApi api, final IUpdateCallback<ResultReportData> callback) {
        if (InternetManager.hasInternet(mActivity)) {
            callback.onLoading();
            JSONObject jo = mJsonHelper.convertDeliverApi(api);
            JsonObjectRequest request = new JSONBaseRequest(jo, ConfigAPIs.getInstance().getReportedDataUrl(),
                    new ResponseCallback(callback) {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Log.d(TAG, "onSuccess: " + response);
                            try {
                                ResultReportData resultReportData = new Gson().fromJson(response.toString(), ResultReportData.class);
                                callback.onSuccess(resultReportData);
                            } catch (Exception e) {
                                Log.d(TAG, "onSuccess11: " + e.getMessage());
                            }
                        }

                        @Override
                        public void onError(Throwable errors) {
                            Log.e(TAG, "onError: ", errors);
                        }
                    }
          );
            sendRequest(request);
        }
    }

    @Override
    public void sendRequest(JsonObjectRequest request) {
        checkNotNull(request);
        MoprosApp.getInstance().addToRequestQueue(request);
    }

}

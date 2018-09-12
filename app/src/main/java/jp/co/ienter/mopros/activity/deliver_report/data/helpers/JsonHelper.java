package jp.co.ienter.mopros.activity.deliver_report.data.helpers;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

//import jp.co.ienter.mopros.activity.deliver_report.data.deliver.remote.controllers.ReportDataAPI;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.apis.DeliverApi;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.RegWorkingDataApi;
import jp.co.ienter.mopros.models.apis.ReportWaitingTimeApi;
import jp.co.ienter.mopros.models.apis.ReturnApi;
import jp.co.ienter.mopros.models.apis.UpdateCargoApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;

public class JsonHelper implements IJsonHelper {
    private static final String TAG = JsonHelper.class.getSimpleName();
    private Gson mGson;

    public JsonHelper(Gson gson) {
        mGson = gson;
    }

    @Override
    public JSONObject convertDeliverArrApiToJO(DeliverArrApi api) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(api, DeliverArrApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertDeliverArrApiToJO: ", e);
        }
        Log.d(TAG, "convertDeliverArrApiToJO: " + jo.toString());
        return jo;
    }

    @Override
    public JSONObject convertBaseApiToJO(BaseApi baseApi) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(baseApi, BaseApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertDeliverArrApiToJO: ", e);
        }
        return jo;
    }

    @Override
    public JSONObject convertDeliverApi(SimpleDeliverApi api) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(api, SimpleDeliverApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertDeliverApi: ", e);
        }
        return jo;
    }

    @Override
    public JSONObject convertPendingApiToJO(PendingDeliverApi api) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(api, PendingDeliverApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertDeliverArrApiToJO: ", e);
        }
        return jo;
    }

    @Override
    public JSONObject convertReturnApi(ReturnApi updateApi) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(updateApi, ReturnApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertReturnApi: ", e);
        }
        return jo;
    }

    public JSONObject convertReportWaitingTimeToJO(DeliverArrApi api) {
        JSONObject jo = null;
        DeliverApi deliverApi = api.getTransport_data()[0];
        ReportWaitingTimeApi timeApi = new ReportWaitingTimeApi(api.getId(), api.getHaiso_date(),
                api.getData_type(), deliverApi.getCourse_cd1_after(), deliverApi.getCourse_cd2_after(),
                deliverApi.getHaiso_order_no(), deliverApi.getTrip(), deliverApi.getTransport_code());
        try {
            jo = new JSONObject(mGson.toJson(timeApi, ReportWaitingTimeApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertDeliverArrApiToJO: ", e);
        }
        return jo;
    }

    public JSONObject convertCargoListToJO(UpdateCargoApi api) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(api, UpdateCargoApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertDeliverArrApiToJO: ", e);
        }
        return jo;
    }

    public JSONObject convertRegWorkingDataApiToJO(RegWorkingDataApi api) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(mGson.toJson(api, RegWorkingDataApi.class));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "convertRegWorkingDataApiToJO: ", e);
        }

        return jo;
    }

//    public JSONObject converReportedApiToJO(ReportDataAPI api) {
//        JSONObject jo = null;
//        try {
//            jo = new JSONObject(mGson.toJson(api, ReportDataAPI.class));
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(TAG, "convertMultipleDeliverApiToJO: ", e);
//        }
//        return jo;
//    }
}

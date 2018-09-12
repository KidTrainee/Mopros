package jp.co.ienter.mopros.services.contracts;

import com.android.volley.toolbox.JsonObjectRequest;

import java.util.List;

import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.RegReturnResult;
import jp.co.ienter.mopros.models.apis.RegWorkingDataApi;
import jp.co.ienter.mopros.models.apis.ResultReportData;
import jp.co.ienter.mopros.models.apis.ResultReportWaitingTime;
import jp.co.ienter.mopros.models.apis.ReturnApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;
import jp.co.ienter.mopros.models.apis.StatusInfo;
import jp.co.ienter.mopros.models.apis.UpdateResult;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.MoprosDelivery;

public interface IDeliverReportService {

    void sendRequest(JsonObjectRequest request);

    void getReportData(SimpleDeliverApi api, IUpdateCallback<ResultReportData> callback);

//    void getReportData(ReportDataAPI reportDataAPI, IUpdateCallback<ResultReportData> iUpdateCallback);

    interface LoadDeliveryListCallback {
        void onLoading();
        void onSuccess(List<MoprosDelivery> deliveryList);
        void onError(String e);
    }

    interface ILoadDeliveryStatusCallback {
        void onLoading();
        void onSuccess(StatusInfo statusInfo);
        void onError(Throwable error);
    }

    void getDeliveryList(BaseApi api, LoadDeliveryListCallback callback);

    void getSelectedDeliveryList(BaseApi api, IUpdateCallback<UpdateResult> callback);

    void startBreak(BaseApi api, IUpdateCallback<UpdateResult> callback);

    void finishBreak(BaseApi api, IUpdateCallback<UpdateResult> callback);

    void pendingProcess(PendingDeliverApi api, IUpdateCallback<UpdateResult> callback);

    void departureDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback);

    void arrivalDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback);

    void startDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback);

    void endDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback);

    void regReturn(ReturnApi api, IUpdateCallback<RegReturnResult> callback);

    void reportDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback);

    void noReportDeliver(DeliverArrApi api, IUpdateCallback<UpdateResult> callback);

    void getReachStatus(SimpleDeliverApi api, IUpdateCallback<ResultReportWaitingTime[]> callback);

    void checkDeliverStatus(SimpleDeliverApi api, IUpdateCallback<StatusInfo> callback);

    void regWorkingData(RegWorkingDataApi api, IUpdateCallback<UpdateResult> callback);

    void getWorkingData(BaseApi api, IUpdateCallback<List<MoprosDelivery>> callback);
}

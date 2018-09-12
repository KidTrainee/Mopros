package jp.co.ienter.mopros.activity.deliver_report.data;

import android.app.Activity;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.ResultReportWaitingTime;
import jp.co.ienter.mopros.models.apis.UpdateDeliverApi;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.UpdateDeliversApi;
import jp.co.ienter.mopros.models.apis.UpdateResult;
import jp.co.ienter.mopros.models.apis.WorkerInfo;
import jp.co.ienter.mopros.services.contracts.IDeliverReportService;

import static jp.co.ienter.mopros.MoprosApp.WORKER_INFO;

public class RemoteDeliveryServiceFake implements IDeliverReportService {

    private static final long DELAYED_TIME_MILLIS = 300;
    private Activity mActivity;

    public RemoteDeliveryServiceFake(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void getDeliveryList(BaseApi baseApi, final IDeliverReportService.LoadDeliveryListCallback callback) {

        final List<MoprosDelivery> deliveryList = new ArrayList<>();
        deliveryList.add(new MoprosDelivery("1", "1", "001379",
                "00", "1", "1", "2","",
                "","首都圏国分（株）八代センター",
                "山梨県笛吹市米倉字米田１２７０ー１","08:00-10:30",
                "48", "2"));
        deliveryList.add(new MoprosDelivery("1", "1", "001379",
                "00", "1", "1", "2","",
                "","首都圏国分（株）八代センター",
                "山梨県笛吹市米倉字米田１２７０ー１","08:00-10:30",
                "48", "2"));
        deliveryList.add(new MoprosDelivery("1", "1", "001379",
                "00", "1", "1", "2","",
                "","首都圏国分（株）八代センター",
                "山梨県笛吹市米倉字米田１２７０ー１","08:00-10:30",
                "48", "2"));
        deliveryList.add(new MoprosDelivery("1", "1", "001379",
                "00", "1", "1", "2","",
                "","首都圏国分（株）八代センター",
                "山梨県笛吹市米倉字米田１２７０ー１","08:00-10:30",
                "48", "2"));
        deliveryList.add(new MoprosDelivery("4", "4", "001379", "00",
                "4", "1", "2", "", "",
                "ＳＥＪ山梨センター（三井食品ＣＶＳ）", "山梨県甲府市国母６－２ー３８",
                "", "128", "1"
        ));
        deliveryList.add(new MoprosDelivery( "9", "9", "001379",
                "00", "9", "1", "2", "",
                "", "７２３１０（株）ジャパンビバレッジ　甲府",
                "山梨県中巨摩郡昭和町西条２１７１", "", "10", "2"));

        callback.onLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(deliveryList);
                    }
                });
            }
        }, DELAYED_TIME_MILLIS);
    }

    @Override
    public void getSelectedDeliveryList(BaseApi api, final LoadDeliveryListCallback callback) {
        final ArrayList<MoprosDelivery> deliveryList = new ArrayList<>();
        deliveryList.add(new MoprosDelivery("4", "4", "001379", "00",
                "4", "1", "1", "", "",
                "ＳＥＪ山梨センター（三井食品ＣＶＳ）", "山梨県甲府市国母６－２ー３８",
                "", "128", "1"
        ));
        deliveryList.add(new MoprosDelivery( "9", "9", "001379",
                "00", "9", "1", "1", "",
                "", "７２３１０（株）ジャパンビバレッジ　甲府",
                "山梨県中巨摩郡昭和町西条２１７１", "", "10", "2"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(deliveryList);
                    }
                });
            }
        }, DELAYED_TIME_MILLIS);
    }

    @Override
    public void startBreak(BaseApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnBreak();
        fakeDeliverOperation(callback);
    }

    @Override
    public void finishBreak(BaseApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnChooseDestination();
        fakeDeliverOperation(callback);
    }

    @Override
    public void pendingProcess(PendingDeliverApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnChooseDestination();
        fakeDeliverOperation(callback);
    }

    @Override
    public void departureDeliver(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setInTransport();
        fakeDeliverOperation(callback);
    }

    @Override
    public void arrivalDeliver(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setInWaiting();
        fakeDeliverOperation(callback);
    }

    @Override
    public void startDeliver(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnDeliver();
        fakeDeliverOperation(callback);
    }

    @Override
    public void endDeliver(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setAtIncidentalWork();
        fakeDeliverOperation(callback);
    }

    @Override
    public void departurePickup(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setInTransport();
        fakeDeliverOperation(callback);
    }

    @Override
    public void arrivalPickup(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setInWaiting();
        fakeDeliverOperation(callback);
    }

    @Override
    public void startPickup(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnDeliver();
        fakeDeliverOperation(callback);

    }

    @Override
    public void finishPickup(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setAtIncidentalWork();
        fakeDeliverOperation(callback);

    }

    @Override
    public void finishReturn(UpdateDeliversApi updateApi, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnChooseDestination();
        fakeDeliverOperation(callback);
    }

    @Override
    public void startReturn(BaseApi api, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setInReturn();
        fakeDeliverOperation(callback);

    }

    @Override
    public void reportDeliver(UpdateDeliversApi api, IUpdateCallback<UpdateResult> callback) {

    }

    @Override
    public void noReportDeliver(UpdateDeliversApi api, IUpdateCallback<UpdateResult> callback) {
        WORKER_INFO.setOnChooseDestination();
        fakeDeliverOperation(callback);
    }

    @Override
    public void reportWaitingTime(UpdateDeliversApi updateApi, IUpdateCallback<ResultReportWaitingTime> callback) {
        WORKER_INFO.setOnChooseDestination();
        fakeReportWaitingTimeOperation(callback);
    }

    private void fakeReportWaitingTimeOperation(final IUpdateCallback<ResultReportWaitingTime> callback) {
        callback.onLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ResultReportWaitingTime updateResult =
                                new ResultReportWaitingTime();
                        callback.onSuccess(updateResult);
                    }
                });
            }
        }, DELAYED_TIME_MILLIS);
    }

    @Override
    public void checkDeliverStatus(UpdateDeliverApi api, final IUpdateCallback<WorkerInfo> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WorkerInfo workerInfo = WORKER_INFO;
                callback.onSuccess(workerInfo);
            }
        }, DELAYED_TIME_MILLIS);
    }


    private void fakeDeliverOperation(final IUpdateCallback<UpdateResult> callback) {
        callback.onLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UpdateResult updateResult = new UpdateResult();
                        callback.onSuccess(updateResult);
                    }
                });
            }
        }, DELAYED_TIME_MILLIS);
    }
}

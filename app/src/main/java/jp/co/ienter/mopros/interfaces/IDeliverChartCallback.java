package jp.co.ienter.mopros.interfaces;

import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;

public interface IDeliverChartCallback {

    void onSuccess(DeliverChartModel model);

    void onError(String error);
}

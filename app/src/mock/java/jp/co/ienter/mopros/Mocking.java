package jp.co.ienter.mopros;

import jp.co.ienter.mopros.activity.deliver_report.callbacks.ButtonCallbacks;

public class Mocking {
    public static void departureDeliver(ButtonCallbacks.DepartureCallback mCallback) {
        mCallback.onBtnDeliverDepartureClick();
    }

    public static void arrivalDeliver(ButtonCallbacks.ArrivalCallback mCallback) {
        mCallback.onBtnDeliverArrivalClick();
    }
}

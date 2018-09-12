package jp.co.ienter.mopros.activity.deliver_report.data.helpers;

public interface IDebounceHandler {

    IDebounceHandler startLoading();

    boolean isBusy();

    IDebounceHandler loadATask();

    IDebounceHandler finishATask();

    void finishIfNotBusy();

    void finish();
}

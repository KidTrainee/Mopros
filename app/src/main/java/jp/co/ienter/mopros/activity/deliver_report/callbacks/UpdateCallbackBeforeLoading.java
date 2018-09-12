package jp.co.ienter.mopros.activity.deliver_report.callbacks;

import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;

public abstract class UpdateCallbackBeforeLoading<T> implements IUpdateCallback<T> {
    @Override
    public void onLoading() {
        LoadingDebounce.getInstance().startLoading().loadATask();
    }

    @Override
    public void onSuccess(T result) {
        onApiSuccess(result);
        LoadingDebounce.getInstance().finishIfNotBusy();
    }

    @Override
    public void onError(Throwable error) {
        onApiError(error);
        LoadingDebounce.getInstance().finishIfNotBusy();
    }

    public abstract void onApiSuccess(T result);
    public abstract void onApiError(Throwable error);
}
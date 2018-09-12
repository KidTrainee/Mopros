package jp.co.ienter.mopros.activity.deliver_report.callbacks;

import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;

public abstract class UpdateCallbackAfterLoading<T> implements IUpdateCallback<T> {
    @Override
    public void onLoading() {
        LoadingDebounce.getInstance().startLoading().loadATask();
    }

    @Override
    public void onSuccess(T result) {
        LoadingDebounce.getInstance().finishIfNotBusy();
        onApiSuccess(result);
    }

    @Override
    public void onError(Throwable error) {
        LoadingDebounce.getInstance().finishIfNotBusy();
        onApiError(error);
    }

    public abstract void onApiSuccess(T result);
    public abstract void onApiError(Throwable error);
}

package jp.co.ienter.mopros.activity.deliver_report.callbacks;

public interface IUpdateCallback<T> {
    void onLoading();
    void onSuccess(T t);
    void onError(Throwable error);
}

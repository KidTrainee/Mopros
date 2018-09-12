package jp.co.ienter.mopros.interfaces;

public interface IBasicApiCallback<T> {
    void onSuccess(T response);

    void onError(String message);
}

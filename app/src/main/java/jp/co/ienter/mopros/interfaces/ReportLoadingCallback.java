package jp.co.ienter.mopros.interfaces;

/**
 * Created by donghv on 8/3/18.
 */

public interface ReportLoadingCallback {
    public void onSuccess(String response);

    public void onError(String error);
}

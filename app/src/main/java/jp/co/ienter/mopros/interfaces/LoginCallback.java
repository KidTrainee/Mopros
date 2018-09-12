package jp.co.ienter.mopros.interfaces;

/**
 * Created by thanhnv on 7/23/18.
 */

public interface LoginCallback {

        public void onSuccess(String response);

        public void onError(String error);
}

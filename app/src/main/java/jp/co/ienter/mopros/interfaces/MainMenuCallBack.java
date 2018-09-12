package jp.co.ienter.mopros.interfaces;

import jp.co.ienter.mopros.models.MainMenuInfor;

/**
 * Created by thanhnv on 8/2/18.
 */

public interface MainMenuCallBack {
    public void onSuccess(MainMenuInfor obj);

    public void onError(String error);
}

package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.Message;

/**
 * Created by donghv on 8/23/18.
 */

public interface MessageCallBack {
    public void onSuccess(ArrayList<Message> listMessage);

    public void onError(String error);
}

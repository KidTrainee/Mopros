package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.MoprosCargo;

/**
 * Created by thanhnv on 8/29/18.
 */

public interface ListCargoCallback {
    void onSuccess(ArrayList<MoprosCargo> listSort);

    void onError(String error);
}

package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.MoprosDelivery;

/**
 * Created by thanhnv on 8/3/18.
 */

public interface IListSortCallBack {

     void onSuccess(ArrayList<MoprosDelivery> listSort);

     void onError(String error);
}

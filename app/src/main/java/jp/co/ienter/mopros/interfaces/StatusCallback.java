package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.DeliveryStatus;
import jp.co.ienter.mopros.models.Message;

/**
 * Created by donghv on 8/28/18.
 */

public interface StatusCallback {
        public void onSuccess(ArrayList<DeliveryStatus> listStatus);

        public void onError(String error);

}

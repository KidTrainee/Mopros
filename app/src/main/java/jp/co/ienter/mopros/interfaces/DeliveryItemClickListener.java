package jp.co.ienter.mopros.interfaces;

import jp.co.ienter.mopros.models.MoprosDelivery;

/**
 * Created by thanhnv on 8/7/18.
 */

public interface DeliveryItemClickListener {
    void onItemClick(MoprosDelivery delivery, int position);
}

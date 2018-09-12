package jp.co.ienter.mopros.interfaces;

import jp.co.ienter.mopros.models.MoprosDelivery;

/**
 * Created by thanhnv on 8/21/18.
 */

public interface OnItemChoosedListener {
    void onIsChooseItem(boolean isChoosed, int position, String kanryoFlag, MoprosDelivery delivery);
}

package jp.co.ienter.mopros.utils;

import android.content.Context;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.MoprosDelivery;

public class MoprosFormatter {
    private Context mContext;

    public MoprosFormatter(Context context) {
        this.mContext = context;
    }

    public String formatStoreName(MoprosDelivery delivery) {
        String format = (delivery.getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER))
                ? mContext.getString(R.string.deliver_store_name_format)
                : mContext.getString(R.string.pickup_store_name_format);
        return String.format(format, delivery.getNonyu_name());
    }
}

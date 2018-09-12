package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.ReportedData;

/**
 * Created by donghv on 8/31/18.
 */

public interface ReportedDataCallBack {
    public void onSuccess(int loading_time, ArrayList<ReportedData> listReportedData, ArrayList<MoprosDelivery> listDelivery);

    public void onError(String error);
}

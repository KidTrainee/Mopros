package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.models.ReportLoading;


/**
 * Created by donghv on 8/3/18.
 */

public interface ReportDetailListCallBack {
    public void onSuccess(ArrayList<ReportLoading> listReportDetail);

    public void onError(String error);
}

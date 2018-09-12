package jp.co.ienter.mopros.interfaces;

import java.util.ArrayList;

import jp.co.ienter.mopros.models.ReportLoading;
import jp.co.ienter.mopros.models.SelectReportList;

/**
 * Created by donghv on 8/31/18.
 */

public interface SelectReportListCallBack {
    public void onSuccess(ArrayList<SelectReportList> listSelectReport);

    public void onError(String error);
}

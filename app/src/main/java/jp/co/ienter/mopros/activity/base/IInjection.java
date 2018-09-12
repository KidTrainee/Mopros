package jp.co.ienter.mopros.activity.base;

import android.app.Activity;

import jp.co.ienter.mopros.services.contracts.IDeliverReportService;

public interface IInjection {
    IDeliverReportService provideDeliveryReportService(Activity activity);
}

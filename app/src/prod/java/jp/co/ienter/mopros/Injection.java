package jp.co.ienter.mopros;

import android.app.Activity;

import jp.co.ienter.mopros.activity.base.IInjection;
import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;

/**
 * Enables injection of mock implementations for {@link RemoteDeliveryService} and
 * {@link null} at compile time.
 */
public class Injection implements IInjection {

    @Override
    public RemoteDeliveryService provideDeliveryReportService(Activity activity) {
        return RemoteDeliveryService.getInstance(activity);
    }

}

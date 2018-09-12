package jp.co.ienter.mopros;

import android.app.Activity;

import jp.co.ienter.mopros.activity.base.IInjection;
import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryServiceFake;

/**
 * Enables injection of mock implementations for {@link RemoteDeliveryServiceFake} and
 * {@link null} at compile time.
 */
public class Injection implements IInjection {

    @Override
    public DeliveryReportService provideDeliveryReportService(Activity activity) {
        return DeliveryReportService.getInstance(new RemoteDeliveryServiceFake(activity), null);
    }

}

package jp.co.ienter.mopros.activity.deliver_report.contracts;

import android.support.annotation.Nullable;

public interface IDeliveryReportBaseView {

    void initDependencies();

    void setupHeader(@Nullable String nonyu_code);

    void setupFooter();
}

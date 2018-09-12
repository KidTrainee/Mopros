package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;

import butterknife.OnClick;
import jp.co.ienter.mopros.R;

public class FragmentExtraReportPickup extends FragmentExtraReportDeliver {

    public static FragmentExtraReportPickup newInstance() {

        Bundle args = new Bundle();

        FragmentExtraReportPickup fragment = new FragmentExtraReportPickup();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setButtonNames() {
        btnReportExtra.setText(R.string.btn_report_quantity);
    }

    @Override
    @OnClick(R.id.btn_report_extra)
    public void onExtraReportClick() {
        if (!mSelectedDeliveryList.isEmpty())
            mFragmentDeliverReport.gotoExtraExtraReport();
    }
}

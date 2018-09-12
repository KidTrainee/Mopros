package jp.co.ienter.mopros.activity.deliver_report;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;
import jp.co.ienter.mopros.utils.DialogUtils;

import static jp.co.ienter.mopros.utils.PreConditions.checkNotNull;

public abstract class FragmentDeliverReportChildBase extends BaseFragment {

    protected TopActivity mActivity;
    protected FragmentDeliverReport mFragmentDeliverReport;
    protected List<MoprosDelivery> mSelectedDeliveryList;
    protected RemoteDeliveryService mDeliverReportRepository;
    protected DeliverArrApi mDeliverArrApi;

//    protected BaseButtonClickCallback mCallback;
    protected SimpleDeliverApi mSimpleDeliverApi;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mActivity = (TopActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().getSimpleName()
                    + " must be " + TopActivity.class.getSimpleName());
        }
        try {
            mFragmentDeliverReport = (FragmentDeliverReport) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getClass().getSimpleName()
                    + " must take " + FragmentDeliverReport.class.getSimpleName()
                    + " as parent fragment");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
        mSelectedDeliveryList = checkNotNull(mFragmentDeliverReport.getSelectedDeliveryList());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentDeliverReport.updateFooter();
    }

    protected void initDependencies() {
        mDeliverReportRepository = mFragmentDeliverReport.getDeliverReportService();
        mDeliverArrApi = mFragmentDeliverReport.getDeliverArrApi();
        mSimpleDeliverApi = mFragmentDeliverReport.getSimpleDeliverApi();
    }

    public void showError(Throwable error) {
        new DialogUtils().showDialog(error.getMessage(), mContext);
    }
}
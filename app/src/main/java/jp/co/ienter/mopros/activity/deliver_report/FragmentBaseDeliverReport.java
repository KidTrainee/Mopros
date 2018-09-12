package jp.co.ienter.mopros.activity.deliver_report;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.contracts.IDeliveryReportBaseView;
import jp.co.ienter.mopros.activity.deliver_report.component_fragment.FragmentFooter;
import jp.co.ienter.mopros.activity.deliver_report.component_fragment.FragmentHeader;
import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;
import jp.co.ienter.mopros.fragments.FragmentGeneralInfo;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.models.helpers.DeliverParser;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.PreferencesHelper;
import jp.co.ienter.mopros.utils.ScreenTransition;

public abstract class FragmentBaseDeliverReport extends BaseFragment implements IDeliveryReportBaseView {

    protected ScreenTransition mScreenTransition;
    protected DeliverParser mDeliverHelper;
    protected PreferencesHelper mPrefsHelper;
    protected TopActivity mActivity;
    protected RemoteDeliveryService mDeliverReportRepository;
    protected IMainFragmentChangeCallback mCallback;
    private FragmentFooter fragmentFooter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mActivity = (TopActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Can't cast " + mContext.getClass().getSimpleName() + " to " + mActivity.getClass().getSimpleName());
        }

        try {
            mCallback = (IMainFragmentChangeCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().getSimpleName() + " doesn't implement this listener " + mCallback.getClass().getSimpleName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
    }

    @Override
    public void initDependencies() {
        mScreenTransition = new ScreenTransition(getChildFragmentManager());
        mDeliverHelper = new DeliverParser();
        mPrefsHelper = PreferencesHelper.getInstance(mContext);
        mDeliverReportRepository = mActivity.getDeliveryReportService();
    }

    @Override
    public void setupHeader(@Nullable String nonyu_code) {
        mScreenTransition.replaceFragment(R.id.fl_header, FragmentHeader.newInstance(nonyu_code));
    }

    protected void setupGeneralInfo() {
            mScreenTransition.replaceFragment(R.id.fl_general_info, FragmentGeneralInfo.newInstance());
    }

    @Override
    public void setupFooter() {
        fragmentFooter = FragmentFooter.newInstance();
        updateFooter();
        mScreenTransition.replaceFragment(R.id.fl_footer, fragmentFooter);
    }

    public void updateFooter() {
        if (fragmentFooter!=null) {
            fragmentFooter.updateMessage();
        }
    }

    public void showError(Throwable error) {
        new DialogUtils().showDialog(error.getMessage(), mContext);
    }
}

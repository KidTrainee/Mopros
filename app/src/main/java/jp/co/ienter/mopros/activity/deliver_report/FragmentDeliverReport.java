package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.ButtonCallbacks;
import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.PaletteResultReport;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;
import jp.co.ienter.mopros.models.helpers.StatusHelper;
import jp.co.ienter.mopros.utils.Const;

public class FragmentDeliverReport extends FragmentBaseDeliverReport
        implements ButtonCallbacks.DepartureCallback, ButtonCallbacks.ArrivalCallback,
        ButtonCallbacks.DeliverStartCallback {

    private List<MoprosDelivery> mSelectedDeliveryList;
    private RemoteDeliveryService mDeliverService;
    private  ArrayList<PaletteResultReport> listPallet;
    private DeliverArrApi mDeliverArrApi;
    private SimpleDeliverApi mSimpleDeliverApi;
//    private ReportDataAPI mReportDataApi;

    @BindView(R.id.fl_deliver_info_container)
    FrameLayout deliverInfoContainer;
//    private DeliverInfoDetailWhenDeliverFragment deliverInfoDetailFragment;
    private BaseFragment mExtraFragment;

    public static FragmentDeliverReport newInstance() {

        Bundle args = new Bundle();

        FragmentDeliverReport fragment = new FragmentDeliverReport();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_deliver_report, container, false);

        getData();

        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    @Override
    public void initDependencies() {
        super.initDependencies();
        mDeliverService = mActivity.getDeliveryReportService();
    }

    public void gotoFragmentDeliverArrival() {
        replaceProgressFragment(FragmentDeliverArrival.newInstance());
    }

    @Override
    public void onBtnDeliverArrivalClick() {
        BaseFragment fragment = mSelectedDeliveryList.get(0).getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER)
                ? FragmentDeliverStart.newInstance()
                : FragmentPickupStart.newInstance();

        replaceProgressFragment(fragment);
    }

    @Override
    public void gotoDeliverEndFragment() {
//        deliverInfoDetailFragment = DeliverInfoDetailWhenDeliverFragment
//                .newInstance(mDeliverHelper.convertToArrayList(mSelectedDeliveryList));
//        setupDeliverInfoFragment(deliverInfoDetailFragment);
        BaseFragment fragment = mSelectedDeliveryList.get(0).getData_type()
                .equals(Const.FLAG_DESTINATION_TYPE_DELIVER)
                ? FragmentDeliverEnd.newInstance()
                : FragmentPickupEnd.newInstance();

        replaceProgressFragment(fragment);
    }

    public void gotoChooseDestinationFragment() {
        mActivity.gotoChooseDestinationFragment();
    }

    public RemoteDeliveryService getDeliverReportService() {
        return mDeliverService;
    }

    private void setupUI() {
        setupHeader(mPrefsHelper.getNonyuCode());
        setupGeneralInfo();
        setupFooter();

//        setupDeliverInfoFragment(DeliverInfoDetailBeforeDeliverFragment
//                .newInstance(mDeliverHelper.convertToArrayList(mSelectedDeliveryList)));
        setFlow ();
    }



    public DeliverArrApi getDeliverArrApi() {
        return mDeliverArrApi;
    }

    public SimpleDeliverApi getSimpleDeliverApi() {
        return mSimpleDeliverApi;
    }

    public List<MoprosDelivery> getSelectedDeliveryList() {
        return mSelectedDeliveryList;
    }

    /* Helper methods */
    private void setFlow() {
        StatusHelper statusHelper = mActivity.getWorkerInfoHelper();
        if (statusHelper == null)
            statusHelper = new StatusHelper(null);

        statusHelper.gotoDeliverReportFragment(this);
    }

//    private void setupDeliverInfoFragment(BaseFragment fragment) {
//        mScreenTransition.replaceFragment(R.id.fl_deliver_info_container, fragment);
//    }

    public void replaceProgressFragment(BaseFragment fragment) {
        mScreenTransition.replaceFragment(R.id.fl_progress_container, fragment);
    }

    private void getData() {
        mSelectedDeliveryList = mActivity.getSelectedDeliveryList();
        mSimpleDeliverApi = mActivity.getSimpleDeliverApi();
        mDeliverArrApi = mActivity.getDeliverArrApi();
//        mReportDataApi = mActivity.getReportDataApi();
    }

    public void gotoExtraDeliverReport() {
//        if (deliverInfoDetailFragment == null) {
//            deliverInfoDetailFragment = DeliverInfoDetailWhenDeliverFragment
//                    .newInstance(mDeliverHelper.convertToArrayList(mSelectedDeliveryList));
////            setupDeliverInfoFragment(deliverInfoDetailFragment);
//        }
//        deliverInfoDetailFragment.showTotal(false);

        BaseFragment frag = (mSelectedDeliveryList.get(0).getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER))
                ? FragmentExtraReportDeliver.newInstance()
                : FragmentExtraReportPickup.newInstance();
        mExtraFragment = frag;
        replaceProgressFragment(frag);
    }

    public void gotoExtraExtraReport() {
        if (mExtraFragment != null) {

            BaseFragment fragment = mSelectedDeliveryList.get(0).getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER)
                    ? FragmentReportPalette.newInstance()
                    : FragmentReportPickupItem.newInstance();

            mScreenTransition.replaceChildFragment(R.id.fl_progress_container, fragment, mExtraFragment);
        }
    }

//    public ReportDataAPI getReportDataApi() {
//        return mReportDataApi;
//    }
    public void gotoPreviousFragment() {
        mScreenTransition.replacePreviousFragment();
        mExtraFragment.onExtraResumed();
    }

    public void setListPallet(ArrayList<PaletteResultReport> listPallet) {
        this.listPallet=listPallet;
    }

//    public ArrayList<PaletteResultReport> getListPallet(ArrayList<PaletteResultReport> listPallet) {
//        return listPallet;
//    }

    public ArrayList<PaletteResultReport> getListPallet() {
        return listPallet;
    }
}

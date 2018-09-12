package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import butterknife.ButterKnife;
import jp.co.ienter.mopros.Injection;
import jp.co.ienter.mopros.Mocking;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseNoMessageActivity;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.UpdateCallbackAfterLoading;

import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;

import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;
import jp.co.ienter.mopros.models.apis.StatusInfo;
import jp.co.ienter.mopros.models.helpers.DeliverParser;
import jp.co.ienter.mopros.models.helpers.StatusHelper;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.PreferencesHelper;
import jp.co.ienter.mopros.utils.ScreenTransition;

import static jp.co.ienter.mopros.utils.PreConditions.checkNotNull;

public class TopActivity extends BaseNoMessageActivity
        implements IMainFragmentChangeCallback {

    private DialogUtils mDialogUtils;
    private List<MoprosDelivery> mSelectedDeliveryList;
    protected ScreenTransition mScreenTransition;
    protected DeliverParser mDeliverHelper;
    protected PreferencesHelper mPrefsHelper;

    private RemoteDeliveryService mDeliverReportRepository;
    private BaseApi mBaseApi;
    private StatusHelper mStatusHelper;
    private SimpleDeliverApi mSimpleDeliverApi;
    private DeliverArrApi mDeliverArrApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
        setContentView(R.layout.top_activity);
        ButterKnife.bind(this);
        // init mBaseApi to check for list of working and deliver status
        mBaseApi = new DeliverParser().createBaseApi(mPrefsHelper);
        getData();
//        Mocking.checkServerCurrentDeliverStateMock(mScreenTransition);
    }

    private void getData() {

        mDeliverReportRepository.getWorkingData(mBaseApi, new IUpdateCallback<List<MoprosDelivery>>() {

            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(List<MoprosDelivery> deliveryList) {
                Log.d(TAG, "onSuccess1234: " + deliveryList.toString());

                handleOnSuccess(deliveryList);
//                gotoChooseDestinationFragment();
                LoadingDebounce.getInstance().finishIfNotBusy();
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "onError: ", error);
                LoadingDebounce.getInstance().finishIfNotBusy();
                gotoChooseDestinationFragment();
            }
        });

    }

    private void handleOnSuccess(List<MoprosDelivery> deliveryList) {
        // if nothing found
        if (deliveryList == null || deliveryList.isEmpty()) {
            gotoChooseDestinationFragment();
            return;
        }
        saveLocalData(deliveryList);
        initApiObject();

        checkStatus();
    }

    private void saveLocalData(List<MoprosDelivery> deliveryList) {

        mSelectedDeliveryList = deliveryList;

        mPrefsHelper.setNonyuCode(deliveryList.get(0).getNonyu_code());

    }

    private void checkStatus() {
        mDeliverReportRepository.checkDeliverStatus(mSimpleDeliverApi, new UpdateCallbackAfterLoading<StatusInfo>() {
            @Override
            public void onApiSuccess(StatusInfo result) {
                mStatusHelper = new StatusHelper(result);
                BaseFragment fragment = mStatusHelper.getTopFragment();
                mScreenTransition.replaceFragment(fragment);
            }

            @Override
            public void onApiError(Throwable error) {
                gotoChooseDestinationFragment();
            }
        });
    }

    private void initApiObject() {
//        if (mReportDataApi == null)
//            mReportDataApi = mDeliverHelper
//                    .createReportApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate(), mSelectedDeliveryList);
        if (mSimpleDeliverApi == null)
            mSimpleDeliverApi = mDeliverHelper
                    .createUpdateDeliverApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate(), mSelectedDeliveryList);
        if (mDeliverArrApi == null)
            mDeliverArrApi = mDeliverHelper
                    .createMultiDeliverApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate(), mSelectedDeliveryList);
    }

    @Override
    protected void onDestroy() {
        LoadingDebounce.dispose();
        super.onDestroy();
    }

    public void initDependencies() {
        mScreenTransition = new ScreenTransition(getSupportFragmentManager());
        mDeliverHelper = new DeliverParser();
        mPrefsHelper = PreferencesHelper.getInstance(this);
        mDialogUtils = new DialogUtils(this);
        mDeliverReportRepository = new Injection().provideDeliveryReportService(this);

        LoadingDebounce.initialize(mDialogUtils);
    }

    @Override
    public void gotoDeliverReportFragment() {
        mScreenTransition.replaceFragment(FragmentDeliverReport.newInstance());
    }

    public void gotoChooseDestinationFragment() {
        mScreenTransition.replaceFragment(FragmentChooseDestination.newInstance());
    }

    @Override
    public void gotoStartBreakFragment() {
        mScreenTransition.replaceFragment(FragmentBreakStart.newInstance());
    }

    @Override
    public void gotoEndBreakFragment() {
        mScreenTransition.replaceFragment(FragmentBreakEnd.newInstance());
    }

    @Override
    public void gotoStartReturnFragment() {
        mScreenTransition.replaceFragment(FragmentReturnDeparture.newInstance());
    }

    @Override
    public void gotoEndReturnFragment() {
        mScreenTransition.replaceFragment(FragmentReturnArrival.newInstance());
    }

    public void setSelectedDeliveryList(List<MoprosDelivery> deliveryList) {
        saveLocalData(deliveryList);
        initApiObject();
    }

    public List<MoprosDelivery> getSelectedDeliveryList() {
        return mSelectedDeliveryList;
    }

    public DialogUtils getDialogUtils() {
        return mDialogUtils;
    }

    public RemoteDeliveryService getDeliveryReportService() {
        return mDeliverReportRepository;
    }

    public StatusHelper getWorkerInfoHelper() {
        return mStatusHelper;
    }

    public BaseApi getBaseApi() {
        checkNotNull(mBaseApi);
        return mBaseApi;
    }

    public SimpleDeliverApi getSimpleDeliverApi() {
        checkNotNull(mSimpleDeliverApi);
        return mSimpleDeliverApi;
    }

    public DeliverArrApi getDeliverArrApi() {
        checkNotNull(mDeliverArrApi);
        return mDeliverArrApi;
    }


    public void updateData() {
        updateSelectedList();
        updateObjApi();
    }

    private void updateObjApi() {
        if (mSimpleDeliverApi != null) {
            mSimpleDeliverApi.incrementGosha();
        }
        if (mDeliverArrApi != null) {
            mDeliverArrApi.incrementGosha();
        }

    }

    private void updateSelectedList() {
        if (mSelectedDeliveryList != null) {
            for (MoprosDelivery delivery : mSelectedDeliveryList) {
                    delivery.incrementGosha();
            }
        }
    }

//    public ReportDataAPI getReportDataApi() {
//        return mReportDataApi;
//    }
}

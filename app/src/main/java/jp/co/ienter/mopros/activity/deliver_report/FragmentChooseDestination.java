package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;
import jp.co.ienter.mopros.adapter.SelectableDeliveryAdapter;
import jp.co.ienter.mopros.interfaces.ConfirmListener;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.helpers.DeliverParser;
import jp.co.ienter.mopros.services.contracts.IDeliverReportService;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class FragmentChooseDestination extends FragmentBaseDeliverReport
        implements SelectableDeliveryAdapter.OnDestinationClickListener {

    @BindView(R.id.rcDestinationList)
    RecyclerView rcDestinationList;

    //    @BindView(R.id.progress_bar)
//    ProgressBar mProgressBar;
    @BindView(R.id.tv_loading_error)
    TextView mLoadingErrorTV;
    @BindView(R.id.btnDecide)
    Button btnDecide;
    @BindView(R.id.btnBreak)
    Button btnBreak;
    @BindView(R.id.btnReturn)
    Button btnReturn;

    private RemoteDeliveryService mDeliveryService;
    private SelectableDeliveryAdapter mSelectableDestinationAdapter;
    private ArrayList<SelectableDelivery> mSelectedDeliveryList;

    private DialogUtils mDialogUtils;
    private boolean mIsPickupSelected = false;
    private BaseApi mBaseApi;

    private boolean mIsReturnable = false;

    public static FragmentChooseDestination newInstance() {

        Bundle args = new Bundle();

        FragmentChooseDestination fragment = new FragmentChooseDestination();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_choose_destination, container, false);

        ButterKnife.bind(this, view);

        initData();
        setupUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getDestinationList();
    }

    @OnClick(R.id.btnDecide)
    public void onCLickBtnDecide() {
        mPrefsHelper.setDeliveryReportFlag(PreferencesHelper.FLOW_DELIVERY_REPORT_DECISION);
        mActivity.setSelectedDeliveryList(new DeliverParser().convertToDeliverList(mSelectedDeliveryList));
        mActivity.gotoDeliverReportFragment();
    }

    @OnClick(R.id.btnBreak)
    public void onClickBtnBreak() {
        mPrefsHelper.setDeliveryReportFlag(PreferencesHelper.FLOW_DELIVERY_REPORT_BREAK);
        mActivity.gotoStartBreakFragment();
    }

    @OnClick(R.id.btnReturn)
    public void onCLickBtnReturn() {
        mPrefsHelper.setDeliveryReportFlag(PreferencesHelper.FLOW_DELIVERY_REPORT_RETURN);
        mActivity.gotoStartReturnFragment();
    }

    // Call when multiple selection is enabled
    @Override
    public void onItemClick(SelectableDelivery delivery, int position) {
        if (!delivery.isSelected()) {
            if (mIsPickupSelected) {
                removeAll();
                mIsPickupSelected = false;
            }
            addToSelectedList(delivery);
            delivery.setSelected(true);
        } else {
            removeFromSelectedList(delivery);
            delivery.setSelected(false);
        }

        enableButtons(checkEmptySelectedList());
    }

    // Call when single selection is enabled
    @Override
    public void onSingleItemClick(SelectableDelivery pickup, int position) {

        if (!pickup.isSelected()) {
            removeAll();
            addToSelectedList(pickup);
            mIsPickupSelected = true;
            pickup.setSelected(true);
        } else {
            removeFromSelectedList(pickup);
            mIsPickupSelected = false;
            pickup.setSelected(false);
        }

        enableButtons(checkEmptySelectedList());
    }

    @Override
    public void onDoneItemClick(SelectableDelivery delivery, int position, ConfirmListener listener) {
        mDialogUtils.showDialogConfirm(mContext.getString(R.string.item_done_click), mContext, listener);
    }

    private void removeAll() {
        mSelectableDestinationAdapter.deselectAll(mSelectedDeliveryList);
        mSelectedDeliveryList.clear();
    }

    private void enableButtons(boolean isSelectedListEmpty) {
        btnBreak.setEnabled(isSelectedListEmpty);
        btnReturn.setEnabled(isSelectedListEmpty && mIsReturnable);
//        btnReturn.setEnabled(isSelectedListEmpty);
        btnDecide.setEnabled(!isSelectedListEmpty);
    }

    private boolean checkEmptySelectedList() {
        return mSelectedDeliveryList == null || mSelectedDeliveryList.isEmpty();
    }

    private void setupRecyclerView() {
        mSelectableDestinationAdapter = new SelectableDeliveryAdapter(mContext, this);
        rcDestinationList.setAdapter(mSelectableDestinationAdapter);
        rcDestinationList.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void getDestinationList() {
        mDeliveryService.getDeliveryList(mBaseApi, new IDeliverReportService.LoadDeliveryListCallback() {
            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(final List<MoprosDelivery> deliveryList) {
                mActivity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Log.d(TAG, "run: " + deliveryList.toString());
                        mSelectableDestinationAdapter.updateData(
                                new DeliverParser().convertToSelectableList(deliveryList));

                        checkReturnable(deliveryList);

                        enableButtons(checkEmptySelectedList());
                        LoadingDebounce.getInstance().finishIfNotBusy();
                    }
                });
            }

            @Override
            public void onError(final String e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        hideProgressBar();
                        LoadingDebounce.getInstance().finishIfNotBusy();
                        Log.e(TAG, "run: " + e);
                        showLoadingError(getString(R.string.txt_error_loading_data));
                    }
                });
            }
        });
    }

    private void checkReturnable(List<MoprosDelivery> deliveryList) {
        mIsReturnable = true;
        for (MoprosDelivery delivery : deliveryList) {
            if (delivery.getKanryo_flg().equals(Const.KANRYO_FLAG_NOT_DONE))
                mIsReturnable = false;
        }
    }

    private void removeFromSelectedList(SelectableDelivery delivery) {
        mSelectedDeliveryList.remove(delivery);
    }

    private void addToSelectedList(SelectableDelivery delivery) {
        mSelectedDeliveryList.add(delivery);
    }

    protected void showLoadingError(String error) {
        if (mLoadingErrorTV != null && mLoadingErrorTV.getVisibility() != View.VISIBLE) {
            mLoadingErrorTV.setVisibility(View.VISIBLE);
            mLoadingErrorTV.setText(error);
        }
    }

    @Override
    public void initDependencies() {
        super.initDependencies();

        mDialogUtils = new DialogUtils(mContext);

        mDeliveryService = mActivity.getDeliveryReportService();
    }

    private void initData() {
        mSelectedDeliveryList = new ArrayList<>();
        mBaseApi = mActivity.getBaseApi();
    }

    private void setupUI() {
        setupHeader("");
        setupGeneralInfo();
        setupFooter();
        setupRecyclerView();
    }

}

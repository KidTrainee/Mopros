package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.UpdateCallbackAfterLoading;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;
import jp.co.ienter.mopros.adapter.DeliverDetailAdapter;
import jp.co.ienter.mopros.adapter.SimpleDeliverAdapter;
import jp.co.ienter.mopros.fragments.base.TwoButtonsFragment;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.ResultReportWaitingTime;
import jp.co.ienter.mopros.models.apis.UpdateResult;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.custom.CustomChronometer;

public class FragmentDeliverStart extends FragmentDeliverReportChildBase {

    @BindView(R.id.fragment_deliver_start_chronometer_waiting_count_up)
    CustomChronometer mWaitingCountUpChronometer;
    @BindView(R.id.deliver_rc)
    RecyclerView mDeliverRc;
    @BindView(R.id.fragment_deliver_arrival_btn_start_deliver)
    Button startBtn;

    public static FragmentDeliverStart newInstance() {

        Bundle args = new Bundle();

        FragmentDeliverStart fragment = new FragmentDeliverStart();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deliver_start, container, false);

        ButterKnife.bind(this, view);
        setupDeliverList();
        setupBtnNames();
        return view;
    }

    protected void setupBtnNames() {

    }

    protected void setupDeliverList() {
        SimpleDeliverAdapter adapter = new SimpleDeliverAdapter(mSelectedDeliveryList, mContext);
        mDeliverRc.setAdapter(adapter);
        mDeliverRc.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        mWaitingCountUpChronometer.start();
    }

    @Override
    public void onDestroy() {
        mWaitingCountUpChronometer.stop();
        super.onDestroy();
    }

    // Button Deliver Start Click
    @OnClick(R.id.fragment_deliver_arrival_btn_start_deliver)
    protected void onStartDeliver() {
        mDeliverReportRepository.startDeliver(mDeliverArrApi, new IUpdateCallback<UpdateResult>() {
            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(UpdateResult result) {
                LoadingDebounce.getInstance().finishIfNotBusy();
                mFragmentDeliverReport.gotoDeliverEndFragment();
            }

            @Override
            public void onError(Throwable error) {
                LoadingDebounce.getInstance().finishIfNotBusy();

                Log.d(TAG, LoadingDebounce.class.getSimpleName() + " onError2: ");
                showError(error);
            }
        });
    }

    @OnClick(R.id.fragment_deliver_arrival_btn_pending)
    protected void onPending() {
        mDeliverReportRepository.pendingProcess(new PendingDeliverApi(mDeliverArrApi, Const.TIME_TYPE_AFTER_ARRIVAL),
                new UpdateCallbackAfterLoading<UpdateResult>() {
                    @Override
                    public void onApiSuccess(UpdateResult result) {
                        mFragmentDeliverReport.gotoChooseDestinationFragment();
                    }

                    @Override
                    public void onApiError(Throwable error) {
                        showError(error);
                    }
                });
    }
}

package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;
import jp.co.ienter.mopros.adapter.DeliverDetailAdapter;
import jp.co.ienter.mopros.fragments.base.TwoButtonsFragment;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.ResultReportWaitingTime;
import jp.co.ienter.mopros.models.apis.UpdateResult;
import jp.co.ienter.mopros.utils.Const;

public class FragmentDeliverArrival extends FragmentDeliverReportChildBase {

    @BindView(R.id.deliver_rc)
    RecyclerView mDeliverRc;

    public static FragmentDeliverArrival newInstance() {

        Bundle args = new Bundle();

        FragmentDeliverArrival fragment = new FragmentDeliverArrival();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deliver_arrival, container, false);

        ButterKnife.bind(this, view);
        setupDeliverList();
        return view;
    }

    private void setupDeliverList() {
        DeliverDetailAdapter adapter = new DeliverDetailAdapter(mContext);
        mDeliverRc.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mDeliverRc.setLayoutManager(linearLayoutManager);
        adapter.updateData(mSelectedDeliveryList);
    }

    @OnClick(R.id.fragment_deliver_arrival_btn_arrival)
    protected void onArrival() {
//        mFragmentDeliverReport.gotoExtraDeliverReport();
        // TODO: 8/28/18 update when api ready
        mDeliverReportRepository.arrivalDeliver(mDeliverArrApi, new IUpdateCallback<UpdateResult>() {

            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(UpdateResult result) {

                mDeliverReportRepository.getReachStatus(mSimpleDeliverApi, new IUpdateCallback<ResultReportWaitingTime[]>() {
                    @Override
                    public void onLoading() {
                        LoadingDebounce.getInstance().startLoading().loadATask();
                    }

                    @Override
                    public void onSuccess(ResultReportWaitingTime[] resultReportWaitingTime) {
                        LoadingDebounce.getInstance().finishIfNotBusy();
                        mFragmentDeliverReport.onBtnDeliverArrivalClick();
                    }

                    @Override
                    public void onError(Throwable error) {
                        LoadingDebounce.getInstance().finishIfNotBusy();
                        showError(error);
                    }
                });
                LoadingDebounce.getInstance().finishIfNotBusy();
            }

            @Override
            public void onError(Throwable error) {
                LoadingDebounce.getInstance().finishIfNotBusy();
                showError(error);
            }
        });
//        mDeliverReportRepository.arrivalDeliver(mDeliverArrApi, new UpdateCallbackAfterLoading<UpdateResult>() {
//
//            @Override
//            public void onApiSuccess(UpdateResult result) {
//                mFragmentDeliverReport.onBtnDeliverArrivalClick();
//            }
//
//            @Override
//            public void onApiError(Throwable error) {
//                showError(error);
//            }
//        });
    }

    // button correction
    @OnClick(R.id.fragment_deliver_arrival_btn_pending)
    protected void onPending() {
        mDeliverReportRepository.pendingProcess(
                new PendingDeliverApi(mDeliverArrApi,
                        Const.TIME_TYPE_BEFORE_ARRIVAL),
                        new IUpdateCallback<UpdateResult>() {
            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(UpdateResult result) {
                LoadingDebounce.getInstance().finishIfNotBusy();
                mFragmentDeliverReport.gotoChooseDestinationFragment();
            }

            @Override
            public void onError(Throwable error) {
                LoadingDebounce.getInstance().finishIfNotBusy();
                showError(error);
            }
        });

    }
}

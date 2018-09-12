package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.UpdateCallbackBeforeLoading;
import jp.co.ienter.mopros.adapter.DeliverDetailAdapter;
import jp.co.ienter.mopros.models.apis.DeliverApi;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.RegWorkingDataApi;
import jp.co.ienter.mopros.models.apis.RegWorkingDataItem;
import jp.co.ienter.mopros.models.apis.UpdateResult;

public class FragmentDeliverDeparture extends FragmentDeliverReportChildBase {

    @BindView(R.id.fragment_deliver_info_detail_tv_message)
    TextView tvCounting;
    @BindView(R.id.deliver_rc)
    RecyclerView mDeliverRc;

    public static FragmentDeliverDeparture newInstance() {

        Bundle args = new Bundle();
        FragmentDeliverDeparture fragment = new FragmentDeliverDeparture();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deliver_departure, container, false);

        ButterKnife.bind(this, view);

        setupUI();
        return view;
    }

    public void setupUI() {
        setupDeliverList();
        setupDeliverCounting();
    }

    private void setupDeliverCounting() {
        tvCounting.setVisibility((mSelectedDeliveryList.size() > 1) ? View.VISIBLE : View.GONE);
        tvCounting.setText(String.format(getString(R.string.total_deliver_format),
                mSelectedDeliveryList.size()));
    }

    private void setupDeliverList() {
        DeliverDetailAdapter adapter = new DeliverDetailAdapter(mContext);
        mDeliverRc.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mDeliverRc.setLayoutManager(linearLayoutManager);
        adapter.updateData(mSelectedDeliveryList);
    }

    // button departure
    @OnClick(R.id.btn_departure)
    protected void onDeparture() {
//        mFragmentDeliverReport.gotoFragmentDeliverArrival();
        mDeliverReportRepository.departureDeliver(mDeliverArrApi, new UpdateCallbackBeforeLoading<UpdateResult>() {
            @Override
            public void onApiSuccess(UpdateResult result) {

                mDeliverReportRepository.regWorkingData(convertDeliverArrToRegWorkingData(mDeliverArrApi),
                        new UpdateCallbackBeforeLoading<UpdateResult>() {

                    @Override
                    public void onApiSuccess(UpdateResult result) {
                        mActivity.updateData();
                        mFragmentDeliverReport.gotoFragmentDeliverArrival();
                    }

                    @Override
                    public void onApiError(Throwable error) {
                        showError(error);
                    }
                });

            }

            @Override
            public void onApiError(Throwable error) {
                showError(error);
            }
        });
    }

    private RegWorkingDataApi convertDeliverArrToRegWorkingData(DeliverArrApi deliverArrApi) {
        int size = deliverArrApi.getTransport_data().length;
        RegWorkingDataItem[] itemArr = new RegWorkingDataItem[size];
        for (int i = 0; i < size; i++) {
            DeliverApi deliverApi = deliverArrApi.getTransport_data()[i];
            itemArr[i] = new RegWorkingDataItem(deliverApi.getCourse_cd1_after(), deliverApi.getCourse_cd2_after(),
                    deliverApi.getHaiso_order_no(), deliverApi.getTrip(), deliverApi.getTransport_code());
        }
        return new RegWorkingDataApi(deliverArrApi.getId(),
                deliverArrApi.getHaiso_date(),
                deliverArrApi.getData_type(),
                itemArr);
    }

    // button correction
    @OnClick(R.id.btn_correction)
    protected void onButtonTwoClick() {
        mFragmentDeliverReport.gotoChooseDestinationFragment();
    }

}

package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.UpdateCallbackAfterLoading;
import jp.co.ienter.mopros.models.apis.RegReturnResult;
import jp.co.ienter.mopros.models.apis.ReturnApi;
import jp.co.ienter.mopros.utils.Const;

public class FragmentReturnArrival extends FragmentBaseDeliverReport {

    private ReturnApi mReturnApi;

    public static FragmentReturnArrival newInstance() {

        Bundle args = new Bundle();

        FragmentReturnArrival fragment = new FragmentReturnArrival();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_return_arrival, container, false);

        initData();

        ButterKnife.bind(this, view);
        setupUI();

        return view;
    }

    private void initData() {
        mReturnApi = new ReturnApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate(),
                Const.RETURN_FLAG_ARRIVAL);
    }

    @Override
    public void initDependencies() {
        super.initDependencies();
    }

    private void setupUI() {
        setupHeader("");
        setupGeneralInfo();
        setupFooter();
    }

    @OnClick(R.id.fragment_return_end_btn)
    public void onStartBreak() {
        mDeliverReportRepository.regReturn(mReturnApi, new UpdateCallbackAfterLoading<RegReturnResult>() {
            @Override
            public void onApiSuccess(RegReturnResult result) {
                mActivity.gotoChooseDestinationFragment();
            }

            @Override
            public void onApiError(Throwable error) {
                showError(error);
            }
        });
    }
}

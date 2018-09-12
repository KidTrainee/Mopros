package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;
import jp.co.ienter.mopros.models.apis.RegReturnResult;
import jp.co.ienter.mopros.models.apis.ReturnApi;
import jp.co.ienter.mopros.utils.Const;

public class FragmentReturnDeparture extends FragmentBaseDeliverReport {

    private ReturnApi mReturnApi;

    public static FragmentReturnDeparture newInstance() {

        Bundle args = new Bundle();

        FragmentReturnDeparture fragment = new FragmentReturnDeparture();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_return_departure, container, false);

        initData();

        ButterKnife.bind(this, view);
        setupUI();

        return view;
    }

    private void initData() {
        mReturnApi = new ReturnApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate(), Const.RETURN_FLAG_DEPARTURE);
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

    @OnClick(R.id.fragment_return_departure_btn_start)
    public void onStartReturn() {
        mDeliverReportRepository.regReturn(mReturnApi, new IUpdateCallback<RegReturnResult>() {
            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(RegReturnResult regReturnResult) {
                LoadingDebounce.getInstance().finishIfNotBusy();
                mActivity.gotoEndReturnFragment();
            }

            @Override
            public void onError(Throwable error) {
                LoadingDebounce.getInstance().finishIfNotBusy();
                showError(error);
            }
        });
    }

    @OnClick(R.id.fragment_return_arrival_btn_correction)
    public void onCorrection() {
        mActivity.gotoChooseDestinationFragment();
    }
}

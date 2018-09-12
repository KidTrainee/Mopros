package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.BaseButtonClickCallback;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.LoadingDebounce;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.apis.UpdateResult;

public class FragmentBreakEnd extends FragmentBaseDeliverReport {
    private BaseApi mBaseApi;
    protected BaseButtonClickCallback mCallback;
    public static FragmentBreakEnd newInstance() {

        Bundle args = new Bundle();

        FragmentBreakEnd fragment = new FragmentBreakEnd();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_deliver_end_break, container, false);

        initData();

        ButterKnife.bind(this, view);
        setupUI();

        return view;
    }

    private void initData() {
        mBaseApi = new BaseApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate());
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

    @OnClick(R.id.fragment_break_btn_end_break)
    public void onFinishBreak() {
        mDeliverReportRepository.finishBreak(mBaseApi, new IUpdateCallback<UpdateResult>() {
            @Override
            public void onLoading() {
                LoadingDebounce.getInstance().startLoading().loadATask();
            }

            @Override
            public void onSuccess(UpdateResult result) {
//                mCallback.onLoadingFinished();
//                ((ButtonCallbacks.BreakFinishBtnClickCallback) mCallback).onBtnBreakFinishClick();

                LoadingDebounce.getInstance().finishIfNotBusy();
                mActivity.gotoChooseDestinationFragment();
            }

            @Override
            public void onError(Throwable error) {
//                mCallback.onLoadingFinished();
                LoadingDebounce.getInstance().finishIfNotBusy();
                showError(error);
            }
        });
    }
}

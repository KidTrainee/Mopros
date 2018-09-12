package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.UpdateCallbackAfterLoading;
import jp.co.ienter.mopros.adapter.SimpleDeliverAdapter;
import jp.co.ienter.mopros.models.apis.UpdateResult;

public class FragmentDeliverEnd extends FragmentDeliverReportChildBase {

    @BindView(R.id.deliver_rc)
    RecyclerView mDeliverRc;
    @BindView(R.id.btn_deliver_end)
    Button endBtn;
    @BindView(R.id.tv_total_deliver)
    TextView tvCounting;

    public static FragmentDeliverEnd newInstance() {

        Bundle args = new Bundle();

        FragmentDeliverEnd fragment = new FragmentDeliverEnd();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliver_end, container, false);

        ButterKnife.bind(this, view);

        setupDeliverList();
        setBtnNames();
        setupViewDeliverCount();
        return view;
    }

    private void setupViewDeliverCount() {
        tvCounting.setVisibility((mSelectedDeliveryList.size() > 1) ? View.VISIBLE : View.GONE);
        tvCounting.setText(String.format(getString(R.string.total_deliver_format),
                    mSelectedDeliveryList.size()));
    }

    protected void setBtnNames() {

    }

    public void setupDeliverList() {
        SimpleDeliverAdapter adapter = new SimpleDeliverAdapter(mSelectedDeliveryList, mContext);
        mDeliverRc.setAdapter(adapter);
        mDeliverRc.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @OnClick(R.id.btn_deliver_end)
    protected void onDeliverEnd() {
        mDeliverReportRepository.endDeliver(mDeliverArrApi, new UpdateCallbackAfterLoading<UpdateResult>() {
            @Override
            public void onApiSuccess(UpdateResult result) {
                mFragmentDeliverReport.gotoExtraDeliverReport();
            }

            @Override
            public void onApiError(Throwable error) {
                showError(error);
            }
        });
    }
}

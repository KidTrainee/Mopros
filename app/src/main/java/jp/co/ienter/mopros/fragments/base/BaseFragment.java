package jp.co.ienter.mopros.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;

public abstract class BaseFragment extends LogcatFragment {

    protected Context mContext;
    protected Bundle mArgs;

    public String getTAG() {
        return TAG;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = getArguments();
    }

    public void notifyDataChanged(DeliverChartModel object){

    }

    public void onExtraResumed() {}
}

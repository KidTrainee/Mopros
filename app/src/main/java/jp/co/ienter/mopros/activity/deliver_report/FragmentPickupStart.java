package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.ButtonCallbacks;

public class FragmentPickupStart extends FragmentDeliverStart {

    public static FragmentPickupStart newInstance() {

        Bundle args = new Bundle();

        FragmentPickupStart fragment = new FragmentPickupStart();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupBtnNames() {
        startBtn.setText(getString(R.string.btn_pick_up_start));
    }
}

package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;

public class FragmentPickupEnd extends FragmentDeliverEnd {

    public static FragmentPickupEnd newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentPickupEnd fragment = new FragmentPickupEnd();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setBtnNames() {
        endBtn.setText(R.string.btn_pick_up_finish);
    }
}

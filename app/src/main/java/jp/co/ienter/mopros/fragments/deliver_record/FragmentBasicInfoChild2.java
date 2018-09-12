package jp.co.ienter.mopros.fragments.deliver_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.fragments.base.BaseFragment;

public class FragmentBasicInfoChild2 extends BaseFragment {
    public static FragmentBasicInfoChild1 newInstance() {

        Bundle args = new Bundle();

        FragmentBasicInfoChild1 fragment = new FragmentBasicInfoChild1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_basic_info_child_2, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }
}

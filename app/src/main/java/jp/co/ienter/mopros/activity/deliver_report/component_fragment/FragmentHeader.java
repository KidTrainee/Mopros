package jp.co.ienter.mopros.activity.deliver_report.component_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.DeliveryChartActivity;
import jp.co.ienter.mopros.fragments.base.BaseFragment;

public class FragmentHeader extends BaseFragment {

    private String mNonyuCode;

    public static FragmentHeader newInstance(@Nullable String nonyu_code) {

        Bundle args = new Bundle();
        args.putString("MoprosDelivery", nonyu_code);
        FragmentHeader fragment = new FragmentHeader();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.include_toolbar_iv_icon)
    ImageView iconIV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNonyuCode = getArguments().getString("MoprosDelivery");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.include_toolbar_deliver_report, container, false);

        ButterKnife.bind(this, view);

        if (mNonyuCode.isEmpty()) {
            iconIV.setVisibility(View.GONE);
        }

        return view;
    }

    @OnClick(R.id.include_toolbar_iv_icon)
    public void onClick() {
        startActivity(new Intent(mContext, DeliveryChartActivity.class)
        .putExtra("supplier_code", mNonyuCode));
    }
}

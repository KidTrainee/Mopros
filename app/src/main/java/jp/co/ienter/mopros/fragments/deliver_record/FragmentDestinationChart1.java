package jp.co.ienter.mopros.fragments.deliver_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.DeliveryChartActivity;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.adapter.DestinationChartAdapter;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.interfaces.IDeliverChartDataChangeListener;
import jp.co.ienter.mopros.utils.AppConstants;

public class FragmentDestinationChart1 extends BaseFragment implements IDeliverChartDataChangeListener {


    @BindView(R.id.activity_destination_chart_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_destination_chart_tabLayout)
    TabLayout mTabLayout;

    private DeliverChartModel mDeliverChartModel;
    private DeliveryChartActivity activity;
    private DestinationChartAdapter adapter;

    public static FragmentDestinationChart1 newInstance() {

        Bundle args = new Bundle();
        FragmentDestinationChart1 fragment = new FragmentDestinationChart1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_destination_chart_1, container, false);
        ButterKnife.bind(this, fragmentView);
        mViewPager.setOffscreenPageLimit(10);
        initViews();
        activity = (DeliveryChartActivity) getActivity();
        activity.setDataChangeListener(this);
        return fragmentView;
    }

    private void initViews() {
        adapter =
                new DestinationChartAdapter(getChildFragmentManager(), mContext, "");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onChanged(DeliverChartModel deliverChartModel) {
        // call back when request data success
        adapter.notifyDataChange(deliverChartModel);
    }
}

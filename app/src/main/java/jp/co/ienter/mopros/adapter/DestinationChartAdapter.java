package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.fragments.deliver_record.FragmentBasicInfo;
import jp.co.ienter.mopros.fragments.deliver_record.FragmentFrozenChilled;
import jp.co.ienter.mopros.fragments.deliver_record.FragmentNormalTemperature;
import jp.co.ienter.mopros.fragments.deliver_record.FragmentSurroundingMap;
import jp.co.ienter.mopros.fragments.deliver_record.FragmentSpecialWork;
import jp.co.ienter.mopros.fragments.base.BaseFragment;

public class DestinationChartAdapter extends FragmentPagerAdapter {

    private int FRAGMENT_NUMBER = 3;
    private Context mContext;
    private ArrayList<BaseFragment> fragments;
    private String[] mTitleArr;
    private final String FLAG_NORMAL_TEMPERATURE = "0";
    private final String FLAG_FROZEN_CHILLED = "1";
    private final String FLAG_SPECICAL_WORK = "2";
    private DeliverChartModel mDeliverChartModel;


    public DestinationChartAdapter(FragmentManager fm, Context context, String ondoKbn) {
        super(fm);
        mContext = context;
        fragments = new ArrayList<>();
        fragments.add(FragmentBasicInfo.newInstance());
        fragments.add(FragmentSurroundingMap.newInstance());
        fragments.add(FragmentNormalTemperature.newInstance());
//        fragments.add(FragmentFrozenChilled.newInstance());
//        fragments.add(FragmentSpecialWork.newInstance());
        mTitleArr = generateTitleTab(ondoKbn);
    }

    public void notifyDataChange(DeliverChartModel object) {
        if (object == null)
            return;
        String ondoKbn;
        try{
            ondoKbn = object.getResult().get(0).getDetailItem().get(0).getOndoKbn() == null ?
                    "0" : object.getResult().get(0).getDetailItem().get(0).getOndoKbn();
        } catch (Exception e){
            e.printStackTrace();
            ondoKbn = "0";
        }
        mTitleArr = generateTitleTab(ondoKbn);
        this.mDeliverChartModel = object;
        for (BaseFragment fragment : fragments) {
            fragment.notifyDataChanged(object);
        }
        notifyDataSetChanged();
    }

    private String[] generateTitleTab(String ondoKbn) {
        String normalTitle = "";
        if (ondoKbn.equals("") || ondoKbn.equals(FLAG_NORMAL_TEMPERATURE)) {
            normalTitle = mContext.getString(R.string.at_normal_temperature);
        } else if (ondoKbn.equals(FLAG_FROZEN_CHILLED)) {
            normalTitle = mContext.getString(R.string.frozen_chilled);
        } else if (ondoKbn.equals(FLAG_SPECICAL_WORK)) {
            normalTitle = mContext.getString(R.string.special_work);
        }
        return new String[]{
                mContext.getString(R.string.basic_info),
                mContext.getString(R.string.surrounding_map),
                normalTitle
        };

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArr[position];
    }

    @Override
    public int getCount() {
        return FRAGMENT_NUMBER;
    }
}

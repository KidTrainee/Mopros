package jp.co.ienter.mopros.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class FragmentGeneralInfo extends BaseFragment {

    private PreferencesHelper mPrefsHelper;

    public static FragmentGeneralInfo newInstance() {

        Bundle args = new Bundle();

        FragmentGeneralInfo fragment = new FragmentGeneralInfo();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.include_general_info_tv_delivery_date) TextView mDeliveryDateTV;
    @BindView(R.id.include_general_info_tv_id) TextView mUserIdTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_general_info, container, false);
        ButterKnife.bind(this, fragmentView);
        initDependencies();
        setupUI();
        return fragmentView;
    }

    private void initDependencies() {
        mPrefsHelper = PreferencesHelper.getInstance(mContext);
    }

    private void setupUI() {
        // setup haiso date
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        mDeliveryDateTV.setText(dateTimeConverter.convertHaisoDate(mPrefsHelper.getHaiSoDate()));
        // setup user id
        mUserIdTV.setText(mPrefsHelper.getUserID());
    }
}

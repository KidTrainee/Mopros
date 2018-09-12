package jp.co.ienter.mopros.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseActivity;
import jp.co.ienter.mopros.activity.base.BaseNoMessageActivity;
import jp.co.ienter.mopros.interfaces.ReportLoadingCallback;
import jp.co.ienter.mopros.services.ReportLoadingService;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/18/18.
 */

public class EndLoadActivity extends BaseNoMessageActivity {
    private PreferencesHelper mPrefsHelper;
    String id = "";
    String haisoDate = "";
    private DialogUtils dialogUtils = new DialogUtils();
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_load);
        ButterKnife.bind(this);
        initEndLoadActivity();
    }

    private void initEndLoadActivity() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        id = mPrefsHelper.getUserID();
        haisoDate = mPrefsHelper.getHaiSoDate();
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(haisoDate));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
    }

    @OnClick(R.id.btnStartLoadComplete)
    public void onCLickBtnStartLoadComplete(View view) {
        if(!InternetManager.hasInternet(EndLoadActivity.this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet),EndLoadActivity.this);
            return;
        }
        dialogUtils.showProgress(EndLoadActivity.this,getResources().getString(R.string.loading));
        ReportLoadingService.getInstance().endLoading(id, haisoDate, new ReportLoadingCallback() {
            @Override
            public void onSuccess(String response) {
                dialogUtils.hideProgress();
                startActivityListTime();
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error,EndLoadActivity.this);
            }
        });
    }

    private void startActivityListTime() {
        Intent intentListTime=new Intent(this,ListTimeActivity.class);
        startActivity(intentListTime);
    }

}

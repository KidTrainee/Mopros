package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseActivity;
import jp.co.ienter.mopros.interfaces.MessageCallBack;
import jp.co.ienter.mopros.interfaces.ReportLoadingCallback;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.services.MessageService;
import jp.co.ienter.mopros.services.ReportLoadingService;
import jp.co.ienter.mopros.utils.CheckMessage;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/18/18.
 */

public class StartLoadActivity extends BaseActivity {
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
        setContentView(R.layout.activity_start_load);
        ButterKnife.bind(this);
        initStartLoadActivity();
    }


    private void initStartLoadActivity() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        id = mPrefsHelper.getUserID();
        haisoDate = mPrefsHelper.getHaiSoDate();
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(haisoDate));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
    }

    @OnClick(R.id.btnStartLoad)
    public void onCLickBtnStartLoad(View view) {
        if(!InternetManager.hasInternet(StartLoadActivity.this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet),StartLoadActivity.this);
            return;
        }
        dialogUtils.showProgress(StartLoadActivity.this,getResources().getString(R.string.loading));
        ReportLoadingService.getInstance().startLoading(id, haisoDate, new ReportLoadingCallback() {
            @Override
            public void onSuccess(String response) {
                dialogUtils.hideProgress();
                startActivityEndLoad();
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error,StartLoadActivity.this);
            }
        });
    }

    private void startActivityEndLoad() {
        Intent intentMainMenu=new Intent(this,EndLoadActivity.class);
        startActivity(intentMainMenu);
    }

    @OnClick(R.id.btnMainMenu)
    public void onCLickBtnMainMenu(View view) {
        finish();
    }
}

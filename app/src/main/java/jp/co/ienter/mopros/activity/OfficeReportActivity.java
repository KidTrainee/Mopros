package jp.co.ienter.mopros.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.services.WorkTimeService;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class OfficeReportActivity extends AppCompatActivity {
    int value;
    String status,clock_in_time,start_finish;
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.btnOffice) Button btnOffice;
    @BindView(R.id.layout_textview_report)
    LinearLayout lnTextViewReport;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.startWorkTime) TextView txtStartWork;
    private DialogUtils dialogUtils = new DialogUtils();
    private PreferencesHelper mPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_report);
        ButterKnife.bind(this);
        initView();
        LoadStatusWorkTime();
    }

    private void LoadStatusWorkTime() {
        WorkTimeService.getInstance().getStatusWorkTime(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(), new LoginCallback() {
            @Override
            public void onSuccess(String response) {
                start_finish = response.substring(0,2);
                clock_in_time = response.substring(2);
                if(value ==1 ){
                    if(start_finish.equals("11") || start_finish.equals("10")){
                        btnOffice.setEnabled(false);
                    }
                } else {
                    txtStartWork.setText(clock_in_time);
                    if(start_finish.equals("11") || start_finish.equals("01") || start_finish.equals("00") || !mPrefsHelper.getStatusHaisocnt()){
                        btnOffice.setEnabled(false);
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initView() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        Intent intent = getIntent();
        value = intent.getIntExtra("name",1);
        btnOffice.setText(value==1 ? R.string.txt_in_office : R.string.txt_out_office);
        if(value==1) lnTextViewReport.setVisibility(View.GONE);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
        if(value==1) {
            lnTextViewReport.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnMainMenu)
    public void onCLickBtnMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @OnClick(R.id.btnOffice)
    public void onClickBtnOfficeReport(View view){
        if(value==1){
            WorkTimeService.getInstance().updateWorkTime(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(), ConfigAPIs.getInstance().startWorkTime(), new LoginCallback() {
                @Override
                public void onSuccess(String response) {
                    Intent intent = new Intent(OfficeReportActivity.this, MainMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                @Override
                public void onError(String error) {
                    dialogUtils.showDialog(error, OfficeReportActivity.this);
                }
            });
        }else{
            WorkTimeService.getInstance().updateWorkTime(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(), ConfigAPIs.getInstance().endWorkTime(), new LoginCallback() {
                @Override
                public void onSuccess(String response) {
//                    PreferencesHelper.getInstance(getApplicationContext()).setUserLogin(PreferencesHelper.FLAG_USER_LOGIN_BREAK);
//                    PreferencesHelper.getInstance(getApplicationContext()).setUserID("");
//                    PreferencesHelper.getInstance(getApplicationContext()).setUserPass("");
                    Intent intent = new Intent(OfficeReportActivity.this, MainMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                @Override
                public void onError(String error) {
                    dialogUtils.showDialog(error, OfficeReportActivity.this);
                }
            });

        }

    }
    @OnClick(R.id.btnMsg)
    public void onCLickBtnMsg(View view) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }
}

package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseActivity;
import jp.co.ienter.mopros.interfaces.ReportDetailListCallBack;
import jp.co.ienter.mopros.models.ReportLoading;
import jp.co.ienter.mopros.services.ReportLoadingService;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class WorkTimeActivity extends BaseActivity {
    @BindView(R.id.radioGroup1)
    RadioGroup mRadioGroup;
    private PreferencesHelper mPrefsHelper;
    String id = "";
    String haisoDate = "";
    String currentTime = "";
    private int loading_time;
    private DialogUtils dialogUtils = new DialogUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_time);
        ButterKnife.bind(this);
        loading_time = getIntent().getExtras().getInt("loading_time");
        initWorkTimeActivity();
        loadData();
    }

    private void loadData() {
        if (!InternetManager.hasInternet(WorkTimeActivity.this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), WorkTimeActivity.this);
            return;
        }
        ReportLoadingService.getInstance().getReportDetailList(getIntent().getExtras().getString("sagyo_code"),new ReportDetailListCallBack() {
            @Override
            public void onSuccess(ArrayList<ReportLoading> listReportDeatail) {
                currentTime = getIntent().getExtras().getString("selected");
                addButtonRadio(listReportDeatail,currentTime);
                dialogUtils.hideProgress();
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error, WorkTimeActivity.this);
            }
        });
    }

    private void addButtonRadio(final ArrayList<ReportLoading> listReportDeatail, String selected) {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < listReportDeatail.size(); i++) {
            final RadioButton rbn = (RadioButton) inflater.inflate(R.layout.item_button, mRadioGroup, false);
            rbn.setText(listReportDeatail.get(i).getItem_name());
            final int finalI = i;
            rbn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(listReportDeatail.get(finalI).getItem_value()) <= loading_time) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("time", rbn.getText().toString());
                        returnIntent.putExtra("sagyo_time",listReportDeatail.get(finalI).getSagyo_time());
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                        overridePendingTransition(0, 0);
                    } else {
                        dialogUtils.showDialog(getResources().getString((R.string.dialog_cannot_choose),String.valueOf(loading_time)),WorkTimeActivity.this);
                        rbn.setChecked(false);
                    }
                }
            });
            mRadioGroup.addView(rbn);
            if (rbn.getText().toString().equals(selected)){
                rbn.setChecked(true);
            }
        }
    }


    private void initWorkTimeActivity() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        id = mPrefsHelper.getUserID();
        haisoDate = mPrefsHelper.getHaiSoDate();
    }
    @OnClick(R.id.btnMainMenu)
    public void onCLickBtnMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @OnClick(R.id.btnMsg)
    public void onCLickBtnMsg(View view) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("time", currentTime);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

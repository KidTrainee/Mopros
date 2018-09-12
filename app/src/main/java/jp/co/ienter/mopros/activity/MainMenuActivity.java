package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseNoMessageActivity;
import jp.co.ienter.mopros.activity.deliver_report.TopActivity;
import jp.co.ienter.mopros.interfaces.ConfirmListener;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.interfaces.MainMenuCallBack;
import jp.co.ienter.mopros.models.LoadingStatusModel;
import jp.co.ienter.mopros.models.MainMenuInfor;
import jp.co.ienter.mopros.services.LoginService;
import jp.co.ienter.mopros.services.StatusService;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/18/18.
 */

public class MainMenuActivity extends BaseNoMessageActivity {
    private MainMenuInfor objMainMenu;
    @BindView(R.id.txtStatusProgress)
    TextView txtStatusProgress;
    @BindView(R.id.txtLoadStatus)
    TextView txtLoadStatus;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.btnSort)
    Button btnSort;
    @BindView(R.id.btnReportDownload)
    Button btnReportDownload;
    @BindView(R.id.btnReportWork)
    Button btnReportWork;
    @BindView(R.id.btnStatus)
    Button btnStatus;
    @BindView(R.id.btnOutOffice)
    Button btnOutOffice;
    private DialogUtils dialogUtils = new DialogUtils(this);
    private PreferencesHelper mPrefsHelper;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        ButterKnife.bind(this);
        LoadAPIMainMenu(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate());
        initMainMenu();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        Intent mIntent = getIntent();
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mIntent);

    }

    private void initMainMenu() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        id = mPrefsHelper.getUserID();
        include_general_info_tv_id.setText(id);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(mPrefsHelper.getHaiSoDate()));
    }

    @OnClick(R.id.btnSort)
    public void onCLickbtnSort(View view) {
        Intent intentMainMenu = new Intent(this, SortActivity.class);
        startActivity(intentMainMenu);
    }

    @OnClick(R.id.btnReportDownload)
    public void onCLickBtnReportDownload(View view) {
//        Intent intentMainMenu = new Intent(this, StartLoadActivity.class);
//        startActivity(intentMainMenu);
        checkLoadStatus();
    }

    private void checkLoadStatus(){
        dialogUtils.showLoadingProgress();
        String id = PreferencesHelper.getInstance(this).getUserID();
        String haisoDate = PreferencesHelper.getInstance(this).getHaiSoDate();
        StatusService.getInstance().loadingStatus(id, haisoDate, new StatusService.LoadingStatusCallback() {
            @Override
            public void onSuccess(LoadingStatusModel statusModel) {
                dialogUtils.hideProgress();
                // use logic when have load status
                transitLoadAction(statusModel);
            }

            @Override
            public void onError(String message) {
                dialogUtils.hideProgress();
                /**
                 * 2018/09/07
                 * add this line to bypass server
                 */
                //dialogUtils.showDialog(message, MainMenuActivity.this);
                gotoActivtiy(StartLoadActivity.class);
            }
        });
    }

    private void transitLoadAction(LoadingStatusModel statusModel){
        if(statusModel == null){

            return;
        }
        try{
            int in_loading = statusModel.getResult().getIn_loading();
            switch (in_loading){
                case LoadingStatusModel.LOAD.BEFORE_START_LOAD:
                    gotoActivtiy(StartLoadActivity.class);
                    break;
                case LoadingStatusModel.LOAD.LOADING:
                    gotoActivtiy(EndLoadActivity.class);
                    break;
                case LoadingStatusModel.LOAD.FINISH_LOAD:
                    int in_report = statusModel.getResult().getIn_report();
                    gotoActivtiy(ListTimeActivity.class, Const.IN_REPORT_FLAG_KEY, in_report );
//                    if(in_report == LoadingStatusModel.REPORT.YET_REPORT){
//                        gotoActivtiy(ListTimeActivity.class);
//                    } else {
//                        gotoActivtiy(StartLoadActivity.class);
//                    }
                    break;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void gotoActivtiy(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void gotoActivtiy(Class activity, String keyExtra, int valueExtra ){
        Intent intent = new Intent(this, activity);
        if(keyExtra != null){
            intent.putExtra(keyExtra, valueExtra);
        }
        startActivity(intent);
    }

    @OnClick(R.id.btnReportWork)
    public void onCLickBtnReportWork(View view) {
//        Intent intentMainMenu = new Intent(this, ChooseDestinationActivity.class);
        Intent intentMainMenu = new Intent(this, TopActivity.class);
        startActivity(intentMainMenu);
    }

    @OnClick(R.id.btnStatus)
    public void onCLickBtnStatus(View view) {
        try{
            Intent intentMainMenu = new Intent(this, StatusActivity.class);
            intentMainMenu.putExtra("status",objMainMenu.getCompleted_unso_cnt()+"/"+objMainMenu.getUnso_cnt_all());
            startActivity(intentMainMenu);
        } catch (Exception e){
            e.printStackTrace();
            Intent intentMainMenu = new Intent(this, StatusActivity.class);
            intentMainMenu.putExtra("status","0/0");
            startActivity(intentMainMenu);
        }
    }

    @OnClick(R.id.btnOutOffice)
    public void onCLickBtnOutOffice(View view) {
        Intent intentMainMenu = new Intent(this, OutOfficeActivity.class);
        startActivity(intentMainMenu);
    }

    @OnClick(R.id.btnLogout)
    public void onCLickBtnLogout(View view) {
        if (!InternetManager.hasInternet(MainMenuActivity.this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), MainMenuActivity.this);
            return;
        }
        dialogUtils.showDialogConfirm(getResources().getString(R.string.confirm_exit), MainMenuActivity.this, new ConfirmListener() {
            @Override
            public void onAgree() {
                LoginService.getInstance().logOut(id, new LoginCallback() {
                    @Override
                    public void onSuccess(String response) {
                        logOutComplete();
                    }

                    @Override
                    public void onError(String error) {
                        ShowDialog(error, MainMenuActivity.this);
//                        DialogUtils.showDialog("error", MainMenuActivity.this);
                    }
                });
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void ShowDialog(final String msg, final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(activity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                       if(msg.equals("ログインしていません。")){
                           logOutComplete();
                       }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void logOutComplete() {
//        mPrefsHelper.setUserLogin(PreferencesHelper.FLAG_USER_LOGIN_BREAK);
        mPrefsHelper.setUserID("");
//        mPrefsHelper.setUserPass("");
        mPrefsHelper.setHaiSoDate("2018/02/15");
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void LoadAPIMainMenu(String id, String haisoDate) {
        dialogUtils.showProgress(MainMenuActivity.this, getResources().getString(R.string.loading));
        if (!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
            dialogUtils.hideProgress();
            return;
        }
        LoginService.getInstance().getMainMenuInfor(id, haisoDate, new MainMenuCallBack() {
            @Override
            public void onSuccess(MainMenuInfor obj) {
                dialogUtils.hideProgress();
                objMainMenu = obj;
                String statusLoad = (Integer.valueOf(obj.getIn_loading()) > 0) ? "済" : "未";

                txtLoadStatus.setText(String.format(getString(R.string.txt_note2),
                        statusLoad));
                txtStatusProgress.setText(String.format(getString(R.string.txt_note3),
                        objMainMenu.getCompleted_unso_cnt(),
                        objMainMenu.getUnso_cnt_all()));
                //Disable Sort,Load,Ship,,Status
                if (Integer.valueOf(obj.getCompleted_unso_cnt()) == Integer.valueOf(obj.getUnso_cnt_all())) {
                    mPrefsHelper.setStatusHaisocnt(true);
                } else {
                    mPrefsHelper.setStatusHaisocnt(false);
                }
                if (Integer.valueOf(obj.getWork_cnt()) == 0) { // no
                    btnSort.setEnabled(false);
                    btnReportDownload.setEnabled(false);
                    btnReportWork.setEnabled(false);
                    btnStatus.setEnabled(false);
                } else {// yes
                    btnSort.setEnabled(true);
                    btnReportDownload.setEnabled(true);
                    btnReportWork.setEnabled(true);
                    btnStatus.setEnabled(true);
                    //Disable Ship,status, go to back
                    if (Integer.valueOf(obj.getIn_loading()) == 0) { //No
                        btnReportWork.setEnabled(false);
                        btnStatus.setEnabled(false);
                        btnOutOffice.setEnabled(false);
                    } else {
                        btnReportWork.setEnabled(true);
                        btnStatus.setEnabled(true);
                        btnOutOffice.setEnabled(true);
                        //Disable goto Back
                        if (Integer.valueOf(obj.getCompleted_unso_cnt()) == Integer.valueOf(obj.getUnso_cnt_all())) {
                            btnOutOffice.setEnabled(true);
                        } else {
                            btnOutOffice.setEnabled(false);
                        }


                    }

                }
                // Loaded
                if(Integer.valueOf(obj.getIn_loading())==3){
                    btnReportDownload.setEnabled(false);
                }else{
                    btnReportDownload.setEnabled(true);
                }
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error, MainMenuActivity.this);
                txtLoadStatus.setText(String.format(getString(R.string.txt_note2),
                        ""));
                txtStatusProgress.setText(String.format(getString(R.string.txt_note3),
                        "0",
                        "0"));
            }
        });
    }

}

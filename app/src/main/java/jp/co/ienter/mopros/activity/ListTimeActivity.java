package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseActivity;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.ISaveDeliveryListCallback;
import jp.co.ienter.mopros.activity.deliver_report.views.CustomizeSpinnerWithLabel;
import jp.co.ienter.mopros.activity.deliver_report.views.SpinnerAdapter;
import jp.co.ienter.mopros.adapter.LoadDeliveryAdapter;
import jp.co.ienter.mopros.interfaces.ReportDetailListCallBack;
import jp.co.ienter.mopros.interfaces.ReportLoadingCallback;
import jp.co.ienter.mopros.interfaces.ReportedDataCallBack;
import jp.co.ienter.mopros.interfaces.SelectReportListCallBack;
import jp.co.ienter.mopros.models.ItemLoadingReport;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.ReportLoading;
import jp.co.ienter.mopros.models.ReportedData;
import jp.co.ienter.mopros.models.SelectReportList;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.models.helpers.DeliverParser;
import jp.co.ienter.mopros.services.ReportLoadingService;
import jp.co.ienter.mopros.utils.AppConstants;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.CustomSpinner;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/18/18.
 */

public class ListTimeActivity extends BaseActivity {
    @BindView(R.id.btnNoReport)
    Button btnNoReport;
    private PreferencesHelper mPrefsHelper;
    private DialogUtils dialogUtils = new DialogUtils();
    String id = "";
    String haisoDate = "";
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.tvTextTime)
    TextView txtTextTime;
    @BindView(R.id.btnReport)
    Button btnReport;
    @BindView(R.id.layout_spinner1)
    LinearLayout layoutSpinner;
    int mLoading_time;
    ArrayList<ReportedData> listItemChose = new ArrayList<>();
    ArrayList<SelectableDelivery> mSelectedDeliveryList;
    ArrayList<ReportedData> mReportedDataList;
    boolean check;
    int totalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_time);
        ButterKnife.bind(this);
        initWorkTimeActivity();
        LoadDataListTime();
    }

    private void setCompoundSpinnerBehavior( final ArrayList<SelectReportList> listSelectReport) {
        ReportLoadingService.getInstance().getReportDetailList("01",new ReportDetailListCallBack() {
            @Override
            public void onSuccess(ArrayList<ReportLoading> listReportDeatail) {
                initSpinner(listSelectReport , listReportDeatail);
            }

            @Override
            public void onError(String error) {
                dialogUtils.showDialog(error, ListTimeActivity.this);
            }
        });

    }

    private void initSpinner(final ArrayList<SelectReportList> listSelectReport,final ArrayList<ReportLoading> listReportDeatail) {
        listReportDeatail.add(0,new ReportLoading("00","","0"));
        for (int j = 0; j < listSelectReport.size(); j++) {
            check = false;
            for(int i = 0;i<mReportedDataList.size();i++){
                if(mReportedDataList.get(i).getSagyo_code().equals(listSelectReport.get(j).getSagyo_code())){
                    listItemChose.add(mReportedDataList.get(i));
                    check = true;
                }
            }
            if(!check){
                listItemChose.add(new ReportedData(listSelectReport.get(j).getSagyo_code(),"00","","0"));
            }
            ArrayAdapter<ReportLoading> adapter;
            adapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item, listReportDeatail);
            final CustomizeSpinnerWithLabel spinner = new CustomizeSpinnerWithLabel(this);
            spinner.setLabel(listSelectReport.get(j).getItem_name());
            spinner.setSpinnerData(adapter);
            final int finalJ = j;
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    totalTime = Integer.parseInt(listReportDeatail.get(i).getItem_value());
                    for(int k = 0; k<listItemChose.size();k++){
                        if( k != finalJ) {
                            totalTime = Integer.parseInt(listItemChose.get(k).getItem_value()) + totalTime;
                        }
                    }
                    if(mLoading_time < totalTime){
                        spinner.setSelection(indexOf(listReportDeatail,listItemChose.get(finalJ).getSagyo_time()),false);
                        dialogUtils.showDialog(getResources().getString((R.string.dialog_cannot_choose),String.valueOf(mLoading_time)),ListTimeActivity.this);
                    } else {
                        listItemChose.get(finalJ).setItem_name(listReportDeatail.get(i).getItem_name());
                        listItemChose.get(finalJ).setItem_value(listReportDeatail.get(i).getItem_value());
                        listItemChose.get(finalJ).setSagyo_time(listReportDeatail.get(i).getSagyo_time());
                        txtTextTime.setText(String.valueOf(totalTime)+"分");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutSpinner.addView(spinner, layoutParams);
            spinner.setSelection(indexOf(listReportDeatail,listItemChose.get(j).getSagyo_time()),false);
        }
    }

    private int indexOf(ArrayList<ReportLoading> listReportDeatail, String sagyo_time) {
        for(int i = 0; i< listReportDeatail.size();i++){
            if(listReportDeatail.get(i).getSagyo_time().equals(sagyo_time)){
                return i;
            }
        }
        return -1;
    }

    private void initWorkTimeActivity() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        id = mPrefsHelper.getUserID();
        haisoDate = mPrefsHelper.getHaiSoDate();
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(haisoDate));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
        try{
            if(getIntent().getExtras().getInt(Const.IN_REPORT_FLAG_KEY) == 1){
                btnReport.setEnabled(false);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void LoadDataListTime() {
        ReportLoadingService.getInstance().getReportedData(id, haisoDate, new ReportedDataCallBack() {
            @Override
            public void onSuccess(int loading_time, ArrayList<ReportedData> listReportedData, ArrayList<MoprosDelivery> listDelivery) {
                mSelectedDeliveryList = new DeliverParser().convertToSelectableListTrue(listDelivery);
                mLoading_time = loading_time;
                mReportedDataList = listReportedData;
                totalTime  = 0;
                for(int i = 0; i< listReportedData.size();i++){
                    totalTime = Integer.parseInt(listReportedData.get(i).getItem_value()) + totalTime;
                }
                txtTextTime.setText(String.valueOf(totalTime)+"分");
                loadDataWorkTime();
            }

            @Override
            public void onError(String error) {
                dialogUtils.showDialog(error, ListTimeActivity.this);
            }
        });
    }
    private void loadDataWorkTime() {
        ReportLoadingService.getInstance().getSelectReportList(new SelectReportListCallBack() {
            @Override
            public void onSuccess(ArrayList<SelectReportList> listSelectReport) {
                setCompoundSpinnerBehavior(listSelectReport);
            }

            @Override
            public void onError(String error) {
                dialogUtils.showDialog(error, ListTimeActivity.this);
            }
        });
    }
    @OnClick(R.id.btnDeliveryPlace)
    public void onCLickBtnDeliveryPlace(View view) {
        Intent intentLoadSupplier=new Intent(this,LoadSupplierActivity.class);
        intentLoadSupplier.putExtra("texttime",txtTextTime.getText().toString());
        Bundle args = new Bundle();
        args.putParcelableArrayList("listDeliveryChose", mSelectedDeliveryList);
        intentLoadSupplier.putExtra("BUNDLELOADLIST",args);
        startActivityForResult(intentLoadSupplier, AppConstants.REQUEST_CODE_LOAD_LIST);
    }

    @OnClick(R.id.btnReport)
    public void onCLickBtnReport(View view) {
        if(totalTime == 0){
            dialogUtils.showDialog("報告内容の登録に失敗しました。",ListTimeActivity.this);
        } else {
            dialogUtils.showProgress(ListTimeActivity.this, getResources().getString(R.string.loading));
            try {
                ReportLoadingService.getInstance().regReportLoading(id, haisoDate, listItemChose, mSelectedDeliveryList, new ISaveDeliveryListCallback() {
                    @Override
                    public void onSuccess() {
                        dialogUtils.hideProgress();
                        Intent intent = new Intent(ListTimeActivity.this, MainMenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable error) {
                        dialogUtils.hideProgress();
                        dialogUtils.showDialog(String.valueOf(error), ListTimeActivity.this);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @OnClick(R.id.btnNoReport)
    public void onClickBtnNoReport(View view){
        dialogUtils.showProgress(ListTimeActivity.this,getResources().getString(R.string.loading));
        ReportLoadingService.getInstance().noReportLoading(id, haisoDate, new ReportLoadingCallback() {
            @Override
            public void onSuccess(String response) {
                dialogUtils.hideProgress();
                Intent intent = new Intent(ListTimeActivity.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error, ListTimeActivity.this);
            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == AppConstants.REQUEST_CODE_WORKTIME && resultCode == Activity.RESULT_OK) {
//                mSagyo_time = data.getStringExtra("sagyo_time");
//                String time = data.getStringExtra("time");
//                txtTextTime.setText(time.equals("")?"0":time);
//                listItemChose.set(itemChose,time);
        if(requestCode == AppConstants.REQUEST_CODE_LOAD_LIST && resultCode == Activity.RESULT_OK){
            mSelectedDeliveryList = new ArrayList<>();
            Bundle bundle = data.getBundleExtra("BUNDLERETURNLISTLIST");
            mSelectedDeliveryList =  bundle.getParcelableArrayList("selectedDeliveryList");
        }
    }
}
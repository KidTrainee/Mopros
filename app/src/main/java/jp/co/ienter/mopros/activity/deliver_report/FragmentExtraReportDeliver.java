package jp.co.ienter.mopros.activity.deliver_report;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.helpers.JsonHelper;
import jp.co.ienter.mopros.activity.deliver_report.views.CustomizeRadioButton;
import jp.co.ienter.mopros.activity.deliver_report.views.CustomizeSpinnerWithLabel;
import jp.co.ienter.mopros.activity.deliver_report.views.SpinnerAdapter;
import jp.co.ienter.mopros.adapter.SimpleDeliverAdapter;
import jp.co.ienter.mopros.interfaces.IBasicApiCallback;
import jp.co.ienter.mopros.interfaces.IBasicApiNoDataCallback;
import jp.co.ienter.mopros.models.ReportMasterData;
import jp.co.ienter.mopros.models.ReportedData;
import jp.co.ienter.mopros.models.apis.PaletteResultReport;
import jp.co.ienter.mopros.models.apis.ResultReportData;
import jp.co.ienter.mopros.models.apis.SelectDataResultReport;
import jp.co.ienter.mopros.models.apis.SelectRadioResultReport;
import jp.co.ienter.mopros.services.NoDeliveryReportService;
import jp.co.ienter.mopros.services.ReportMasterDataService;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.DialogUtils;

import static android.app.Activity.RESULT_OK;

public class FragmentExtraReportDeliver extends FragmentDeliverReportChildBase {

    private static final int REQUEST_CODE_GET_WORK_TIME = 100;
//    protected List<MoprosDelivery> mDeliveryList;

//    @BindView(R.id.activity_extra_deliver_report_tv_store_name)
//    TextView mStoreNameTV;

    private ArrayList<CustomizeSpinnerWithLabel> spinners = new ArrayList<CustomizeSpinnerWithLabel>();
    private ArrayList<CustomizeRadioButton> radioButtons = new ArrayList<CustomizeRadioButton>();
    private ArrayList<SelectDataResultReport> mSelectDataResultReport = new ArrayList<>();
    private ArrayList<SelectRadioResultReport> mSelectRadioResultReport = new ArrayList<>();
    private ArrayList<PaletteResultReport> mPaletteResultReport = new ArrayList<>();
    private ArrayList<SelectRadioResultReport> listRadio = new ArrayList<>();
    private ArrayList<Integer> listCurrent = new ArrayList<Integer>();
    private int nohin_time = 0;
    int totalTime = 0;
    int pos = 0;
    final DialogUtils dialog = new DialogUtils(mContext);
    @BindView(R.id.layout_spinner)
    LinearLayout layoutSpinner;

    @BindView(R.id.deliver_rc)
    RecyclerView mDeliverRc;

//    private ReportDataAPI reportDataAPI;

    @BindView(R.id.btn_report_extra)
    Button btnReportExtra;

    //    TextView mStoreNameTV;
    @BindView(R.id.layout_radio_button)
    LinearLayout layoutRadioButton;
    @BindView(R.id.activity_extra_deliver_report_tv_time_deliver)
    TextView tvExtra;

    public static FragmentExtraReportDeliver newInstance() {

        Bundle args = new Bundle();

        FragmentExtraReportDeliver fragment = new FragmentExtraReportDeliver();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onExtraResumed() {
        mPaletteResultReport = mFragmentDeliverReport.getListPallet();
        Log.d(TAG, "onExtraResumed");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_extra_deliver_report, container, false);

        ButterKnife.bind(this, view);
        initData();
        setupDeliverList();
        return view;
    }

    private void setupDeliverList() {
        SimpleDeliverAdapter adapter = new SimpleDeliverAdapter(mSelectedDeliveryList, mContext);
        mDeliverRc.setAdapter(adapter);
        mDeliverRc.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    private void initData(){
        Log.d(TAG, "getCargoListDataUrl ======>API : " + ConfigAPIs.getInstance().getCargoListDataUrl());

//        JSONObject jo = mJsonHelper.convertCargoListToJO(mSelectedDeliveryList);

            mDeliverReportRepository.getReportData(
                    mSimpleDeliverApi, new IUpdateCallback<ResultReportData>() {

                        @Override
                        public void onLoading() {

                        }

                        @SuppressLint("StringFormatMatches")
                        @Override
                        public void onSuccess(ResultReportData resultReportData) {
                            tvExtra.setText(getString(R.string.txt_time_deliver, resultReportData.getNohin_time()));
                            nohin_time = Integer.parseInt(resultReportData.getNohin_time());
                            mSelectDataResultReport.addAll(Arrays.asList(resultReportData.getSelect_data()));
                            mPaletteResultReport.addAll(Arrays.asList(resultReportData.getPalette_data()));
                            mSelectRadioResultReport.addAll(Arrays.asList(resultReportData.getRadio_data()));
                            setupUI();
                            Log.d(TAG,resultReportData.toString());
                        }

                        @Override
                        public void onError(Throwable error) {
                            showError(error);
                        }
                    });
    }

    @Override
    public void initDependencies() {
        super.initDependencies();
    }

    private void getData() {
//        reportDataAPI = mFragmentDeliverReport.getReportDataApi();
    }

    private void setupUI() {
        //setupHeader(mPrefsHelper.getNonyuCode());
        //setupGeneralInfo();
        //setupFooter();
        // setup store name
//        mStoreNameTV.setText(new MoprosFormatter(mContext).formatStoreName(mSelectedDeliveryList.get(0)));
        setButtonNames();
        loadReportMasterData(mSelectedDeliveryList.get(0).getData_type());
    }

    protected void setButtonNames() {
        btnReportExtra.setText(getString(R.string.btn_report_palette));
    }

    private void loadReportMasterData(String dataType){
        final DialogUtils dialog = new DialogUtils(mContext);
        dialog.showLoadingProgress();
        new ReportMasterDataService().getReportMasterData(dataType, new IBasicApiCallback<ReportMasterData>() {
            @Override
            public void onSuccess(ReportMasterData response) {
                dialog.hideProgress();
                if(response.getSelect_item() != null){
                    for(ReportMasterData.Select_item item : response.getSelect_item()){
                        addSpinner(item.getSagyo_code(),item.getItem_name(), item.getSub_item_list());
                    }
                    for(ReportMasterData.Radio_item item : response.getRadio_item()){
                        addRadioButton(item.getItem_name(),item.getItem_code(),response.getRadio_item().indexOf(item));
                    }

                }
            }

            @Override
            public void onError(String message) {
                dialog.hideProgress();
                dialog.showDialog(message, mContext);
            }
        });
    }

    private void addSpinner(final String sagyo_code, String label, final List<ReportMasterData.Sub_item_list> arrData) {
        arrData.add(0, new ReportMasterData.Sub_item_list("00","","0"));
        final SpinnerAdapter adapter;
        adapter = new SpinnerAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, arrData);
        final CustomizeSpinnerWithLabel spinner = new CustomizeSpinnerWithLabel(mContext);
        spinners.add(spinner);
        spinner.setLabel(label);
        spinner.setSpinnerData(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = checkExists(sagyo_code);

                //get totaltime
                totalTime = Integer.parseInt(arrData.get(i).getSub_item_value());
                for(int k = 0; k<mSelectDataResultReport.size();k++){
                    for(int j = 0;j<arrData.size();j++){
                        if(mSelectDataResultReport.get(k).getSagyo_time().equals(arrData.get(j).getSub_item_code()) && !sagyo_code.equals(mSelectDataResultReport.get(k).getSagyo_code())){
                            totalTime = totalTime + Integer.parseInt(arrData.get(j).getSub_item_value());
                        }
                    }
                }

                //check totaltime
                if(totalTime > nohin_time){
                    dialog.showDialog(getString((R.string.dialog_cannot_choose),String.valueOf(nohin_time)), mContext);
                    if(pos == -1 ){
                        spinner.setSelection(0,false);
                    } else {
                        spinner.setSelection(indexOf(arrData,mSelectDataResultReport.get(pos).getSagyo_time()),false);
                    }
                } else {
                    if (pos == -1) {
                        mSelectDataResultReport.add(new SelectDataResultReport(sagyo_code, arrData.get(i).getSub_item_code()));
                    } else {
                        mSelectDataResultReport.set(pos, new SelectDataResultReport(sagyo_code, arrData.get(i).getSub_item_code()));
                    }
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
        if(checkExists(sagyo_code) == -1){
            spinner.setSelection(0,false);
        } else {
            spinner.setSelection(indexOf(arrData,mSelectDataResultReport.get(checkExists(sagyo_code)).getSagyo_time()),false);
        }
    }

    private int indexOf(List<ReportMasterData.Sub_item_list> arrData, String sagyo_time) {
        for(int i = 0; i< arrData.size();i++){
            if(arrData.get(i).getSub_item_code().equals(sagyo_time)){
                return i;
            }
        }
        return -1;
    }

    private int checkExists(String sagyo_code){
        for(int k = 0;k < mSelectDataResultReport.size();k++){
            if (mSelectDataResultReport.get(k).getSagyo_code().equals(sagyo_code)){
                return k;
            }
        }
        return -1;
    }

    private void addRadioButton(String label,String item_code,int pos){
        if(checkExistsRadio(item_code) == -1){
            listRadio.add(new SelectRadioResultReport(item_code,"-1"));
        } else {
            listRadio.add(new SelectRadioResultReport(item_code,mSelectRadioResultReport.get(checkExistsRadio(item_code)).getChange_kbn()));
        }

        CustomizeRadioButton rb = new CustomizeRadioButton(mContext);
        rb.setLabel(label);
        radioButtons.add(rb);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutRadioButton.addView(rb, layoutParams);
        rb.setChecked(listRadio.get(pos).getChange_kbn());
    }
    private void getListRadio(){
        for(CustomizeRadioButton rb : radioButtons){
            listRadio.get(radioButtons.indexOf(rb)).setChange_kbn(String.valueOf(rb.getSelected()));
        }
    }
    private int checkExistsRadio(String item_code) {
        for(int i=0;i<mSelectRadioResultReport.size();i++){
            if (mSelectRadioResultReport.get(i).getSagyo_code().equals(item_code)) {
                return i;
            }
        }
        return -1;
    }


    @OnClick({R.id.btn_report_extra,
            R.id.activity_extra_deliver_report_btn_decision,
            R.id.activity_extra_deliver_report_btn_no_report})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_extra_deliver_report_btn_decision:
                break;
            case R.id.activity_extra_deliver_report_btn_no_report:
//                startActivity(new Intent(mContext, ChooseDestinationActivity.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                // call api noreport
                Log.d(TAG, "onClick: " + mSelectedDeliveryList.toString());
                final DialogUtils dialog = new DialogUtils(mContext);
                dialog.showLoadingProgress();
                try {
                    new NoDeliveryReportService().noReportDeliver(new JsonHelper(new Gson()), mDeliverArrApi, new IBasicApiNoDataCallback() {
                                @Override
                                public void onSuccess() {
                                    dialog.hideProgress();
                                    mFragmentDeliverReport.gotoChooseDestinationFragment();
                                }

                                @Override
                                public void onError(String message) {
                                    dialog.hideProgress();
                                    dialog.showDialog(message, mContext);
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.hideProgress();
                    dialog.showDialog(e.getMessage(), mContext);
                }

                break;
        }
    }

    @OnClick(R.id.btn_report_extra)
    public void onExtraReportClick() {
        if (!mSelectedDeliveryList.isEmpty()) {}
//            startActivity(new Intent(mContext, ReportPaletteActivity.class)
//                    .putParcelableArrayListExtra(Const.EXTRA_DELIVERY_LIST, mSelectedDeliveryList));
//            mCallback.gotoExtraExtraReportFragment();
            mFragmentDeliverReport.gotoExtraExtraReport();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GET_WORK_TIME && resultCode == RESULT_OK) {
            // TODO: 7/31/18 get work time here
            Toast.makeText(mContext, "work time selected", Toast.LENGTH_SHORT).show();
        }
    }
    //    }
    //        mDeliveryList = mFragmentDeliverReport.getSelectedDeliveryList();
    //    private void getData() {

}

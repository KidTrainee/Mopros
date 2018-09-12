package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.views.CustomizePaletteItem;
import jp.co.ienter.mopros.interfaces.IBasicApiCallback;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.PaletteReportDataModel;
import jp.co.ienter.mopros.models.apis.PaletteResultReport;
import jp.co.ienter.mopros.services.ReportPaletteService;
import jp.co.ienter.mopros.utils.Const;
import jp.co.ienter.mopros.utils.DialogUtils;

import static jp.co.ienter.mopros.utils.PreConditions.checkNotNull;

public class FragmentReportPalette extends FragmentDeliverReportChildBase
        implements CustomizePaletteItem.OnItemDeliveryPaletteCallback,
        CustomizePaletteItem.OnItemReturnPaletteCallback{

    private static final int MAX_VALUE = 100;
    public ArrayList<PaletteResultReport> listTotalPallet;
    private String dataTypes="0";

//    @BindView(R.id.activity_report_palette_tv_store_name)
//    TextView mStoreNameTV;

//    @BindView()
       LinearLayout layoutPalette;


    private List<MoprosDelivery> mDeliveryList;
    private ArrayList<CustomizePaletteItem> paletteItems = new ArrayList<CustomizePaletteItem>();

    public static FragmentReportPalette newInstance() {

        Bundle args = new Bundle();
        FragmentReportPalette fragment = new FragmentReportPalette();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_palette, container, false);
        layoutPalette = (LinearLayout) view.findViewById(R.id.layout_item_palette);
        getData();
        ButterKnife.bind(this, view);
        setupUI();
        listTotalPallet=new ArrayList<>();
        return view;
    }

    private void setupUI() {
        loadPaletteData("1");
    }

    protected void loadPaletteData(String dataType){
        final DialogUtils dialog = new DialogUtils(mContext);
        dialog.showLoadingProgress();
        new ReportPaletteService().getPaletteMasterData(dataType, new IBasicApiCallback<PaletteReportDataModel>() {
            @Override
            public void onSuccess(PaletteReportDataModel response) {
                dialog.hideProgress();
                if(response.getPalette_data() != null){
                    dataTypes= Const.FLAG_DESTINATION_TYPE_DELIVER;
                    for(PaletteReportDataModel.Palette_datum item : response.getPalette_data()){
                        //add palette data
                        addPaletteItem(item.getPalette_name(), String.valueOf(item.getPalette_no()));
                    }
                }

                if(response.getShuka_data() != null){
                    dataTypes= Const.FLAG_DESTINATION_TYPE_PICK_UP;
                    for(PaletteReportDataModel.Shuka_datum item : response.getShuka_data()){
                        //add shuka data
                        onAddShukaData(item.getItem_name());
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

    protected void onAddShukaData(String label){

    }

    private void getData() {
        mDeliveryList = mFragmentDeliverReport.getSelectedDeliveryList();
    }


    private void addPaletteItem(String label,String paletteNo){
        CustomizePaletteItem item = new CustomizePaletteItem(mContext,paletteNo, (CustomizePaletteItem.OnItemDeliveryPaletteCallback) this, (CustomizePaletteItem.OnItemReturnPaletteCallback) this);
        item.setLabel(label);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        ArrayAdapter deliveryAdapter = createAdapter();
        item.setDeliveryAdapter(deliveryAdapter);
        ArrayAdapter returnAdapter = createAdapter();
        item.setReturnAdapter(returnAdapter);
        paletteItems.add(item);
        layoutPalette.addView(item, layoutParams);
    }

    private ArrayAdapter<String> createAdapter(){
        ArrayList<String> arrData = new ArrayList<String>();
        for(int i = 0; i <= 100; i++){
            arrData.add("" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, arrData);
        return adapter;
    }



    private void setupDeliverInfo() {
        checkNotNull(mDeliveryList);
        if (!mDeliveryList.isEmpty()) {
            //mStoreNameTV.setText(new MoprosFormatter(mContext).formatStoreName(mDeliveryList.get(0)));
        }
    }

    @OnClick(R.id.activity_report_palette_btn_done)
    public void onClick() {
//        mCallback.gotoExtraReportFragment();
//        PaletteResultReport objPallet =new PaletteResultReport("1","2","3","4","5");
//        listTotalPallet.add(objPallet);
        mFragmentDeliverReport.setListPallet(listTotalPallet);

        mFragmentDeliverReport.gotoPreviousFragment();
    }


    @Override
    public void onItemDeliveryClick(int position, String lblLabel, String strDelivery, String palletNo) {
        Log.d("ItemClick:",lblLabel);
        PaletteResultReport objPallet=null;
        if(listTotalPallet.size()==0){
            objPallet = new PaletteResultReport(palletNo, strDelivery, "", "", "");
        }else {
            for (int i = 0; i < listTotalPallet.size(); i++) {
                if (palletNo.equalsIgnoreCase(listTotalPallet.get(i).getPalette_no())) {
                    objPallet = new PaletteResultReport(palletNo, strDelivery, listTotalPallet.get(i).getKaishu_kago_cnt(), "", "");
                    listTotalPallet.remove(i);
                    break;
                } else {
                    objPallet = new PaletteResultReport(palletNo, "", strDelivery, "", "");
                }
            }
        }
        listTotalPallet.add(objPallet);
    }

    @Override
    public void onItemReturnClick(int position, String lblLabel, String strReturn,String palletNo) {
        Log.d("ItemClick:",lblLabel);
        boolean isDuplicate=false;
        PaletteResultReport objPallet=null;
        if(listTotalPallet.size()==0){
            objPallet = new PaletteResultReport(palletNo, "", strReturn, "", "");
        }else {
            for (int i = 0; i < listTotalPallet.size(); i++) {
                if (palletNo.equalsIgnoreCase(listTotalPallet.get(i).getPalette_no())) {
                    objPallet = new PaletteResultReport(palletNo, listTotalPallet.get(i).getJitsu_kago_cnt(), strReturn, "", "");
                    listTotalPallet.remove(i);
                    isDuplicate=true;
                    break;
                }
            }
            if(!isDuplicate)
                objPallet = new PaletteResultReport(palletNo, "", strReturn, "", "");
        }
        listTotalPallet.add(objPallet);
    }
}

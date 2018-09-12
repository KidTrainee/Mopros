package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.Injection;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseNoMessageActivity;
import jp.co.ienter.mopros.activity.deliver_report.data.RemoteDeliveryService;
import jp.co.ienter.mopros.adapter.PickUpListAdapter;
import jp.co.ienter.mopros.interfaces.IBasicApiNoDataCallback;
import jp.co.ienter.mopros.interfaces.ListCargoCallback;
import jp.co.ienter.mopros.models.MoprosCargo;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.CargoApi;
import jp.co.ienter.mopros.models.helpers.DeliverParser;
import jp.co.ienter.mopros.services.SortService;
import jp.co.ienter.mopros.services.contracts.IDeliverReportService;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/31/18.
 */

public class PickUpActivity extends BaseNoMessageActivity implements PickUpListAdapter.CargoItemClickListener {

    @BindView(R.id.include_general_info_tv_date_title)
    TextView tvDateTitle;
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;

    private RecyclerView rcPickUp;
    private RecyclerView.LayoutManager mLayoutManager;
    private PickUpListAdapter mAdapter;
    private List<MoprosDelivery> mCustomers;
    private List<MoprosCargo> listCargo;
    private RemoteDeliveryService mDeliveryReportService;
    private PreferencesHelper mPrefsHelper;
    private MoprosCargo objMoprosDelivery;
    private ArrayList<MoprosCargo> listCago;
    private DialogUtils dialogUtils = new DialogUtils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);
        ButterKnife.bind(this);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
        initDependencies();
        mDeliveryReportService = new Injection().provideDeliveryReportService(this);
        initViews();
        Bundle data = getIntent().getBundleExtra("BUNDLE");
        listCago =  data.getParcelableArrayList("listRemove");
        getListCargo();

    }

    private void getListCargo(){
        ArrayList<MoprosCargo> cargoList;
        if(listCago.size()>0) {
            cargoList=listCago;
        }else{
            cargoList = new ArrayList<>();
        }
//        cargoList.add(new MoprosCargo("301", "abc", "01"));
//        cargoList.add(new MoprosCargo("303", "abcd", "02"));
        dialogUtils.showProgress(PickUpActivity.this, getResources().getString(R.string.loading));
        if (!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
            dialogUtils.hideProgress();
            return;
        }
        CargoApi[] cargoApis = new DeliverParser().convertCargoListToArr(cargoList);
        SortService.getInstance().getCargoListData(PreferencesHelper.getInstance(
                getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(),
                cargoApis, new ListCargoCallback() {
                    @Override
                    public void onSuccess(ArrayList<MoprosCargo> listSort) {
                        dialogUtils.hideProgress();
                        listCargo=listSort;
                        setupRecyclerView();
                    }

                    @Override
                    public void onError(String error) {
                        dialogUtils.hideProgress();
                        new DialogUtils().showDialog(error, PickUpActivity.this);
                    }
                });
    }

    private void initDependencies() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
    }

    private void initViews() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
//        listRemove = (ArrayList<MoprosDelivery>) args.getSerializable("listRemove");
        tvDateTitle.setText(R.string.txt_pick_up_date);
    }


    @OnClick(R.id.btnCollectionSelect)
    public void onCLickBtnCollectionSelect(View view) {
        if (objMoprosDelivery != null) {
            if(!InternetManager.hasInternet(PickUpActivity.this)) {
                dialogUtils.showDialog(getResources().getString(R.string.no_internet),PickUpActivity.this);
                return;
            }
            SortService.getInstance().regPickup(PreferencesHelper.getInstance(
                    getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(),
                    objMoprosDelivery.getShuka_code(),
                    objMoprosDelivery.getSyaryo_code(),
                    objMoprosDelivery.getShuka_name() ,new IBasicApiNoDataCallback() {

                        @Override
                        public void onSuccess() {
                            dialogUtils.hideProgress();
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("objMoprosDelivery", objMoprosDelivery);
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }

                        @Override
                        public void onError(String error) {
                            dialogUtils.hideProgress();
                            dialogUtils.showDialog(error, PickUpActivity.this);
                        }
                    });

        } else {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

    }

    private void setupRecyclerView() {
        rcPickUp = findViewById(R.id.rcPickUp);
        rcPickUp.setHasFixedSize(false);
        rcPickUp.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        rcPickUp.setLayoutManager(mLayoutManager);
        mAdapter = new PickUpListAdapter(listCargo, this);
        rcPickUp.setAdapter(mAdapter);
    }

//    private void getDestinationList() {
//        BaseApi baseApi = new BaseApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate());
//        mDeliveryReportService.getSelectedDeliveryList(baseApi, new IDeliverReportService.LoadDeliveryListCallback() {
//                    @Override
//                    public void onLoading() {
//
//                    }
//
//                    @Override
//                    public void onSuccess(List<MoprosDelivery> deliveryList) {
//                        mCustomers = deliveryList;
//                        for (MoprosDelivery delivery : listRemove) {
//                            mCustomers.add(delivery);
//                        }
//                        setupRecyclerView();
//                    }
//
//                    @Override
//                    public void onError(String e) {
//
//                    }
//                }
//        );
//    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<MoprosCargo> listCargo = data.getExtras().getParcelable("listRemove");
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onItemClick(MoprosCargo delivery, int position) {
//        objMoprosDelivery = new MoprosCargo();
        objMoprosDelivery = delivery;
    }
}

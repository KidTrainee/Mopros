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
import jp.co.ienter.mopros.adapter.LoadDeliveryAdapter;
import jp.co.ienter.mopros.services.contracts.IDeliverReportService;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.models.helpers.DeliverParser;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/18/18.
 */

public class LoadSupplierActivity extends BaseNoMessageActivity implements LoadDeliveryAdapter.OnDestinationClickListener {
    @BindView(R.id.rcDestinationList)
    RecyclerView rcDestinationList;
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.tvTextTime)
    TextView txtTextTime;

    private DialogUtils dialogUtils = new DialogUtils();
    private RemoteDeliveryService mDeliveryReportService;
    private LoadDeliveryAdapter mLoadDeliveryAdapter;
    private ArrayList<SelectableDelivery> mSelectedDeliveryList;
    private PreferencesHelper mPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_suplier);
        ButterKnife.bind(this);

        initDependencies();
        initData();

        setupRecyclerView();
        getDestinationList();
    }

    private void initDependencies() {
        mPrefsHelper = PreferencesHelper.getInstance(this);
        mDeliveryReportService = new Injection().provideDeliveryReportService(this);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(mPrefsHelper.getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
        txtTextTime.setText(getIntent().getExtras().getString("texttime").equals("") ? "0" : getIntent().getExtras().getString("texttime"));
    }

    private void initData() {
        dialogUtils.showProgress(LoadSupplierActivity.this, getResources().getString(R.string.loading));
        mSelectedDeliveryList = new ArrayList<>();
        Bundle data = getIntent().getBundleExtra("BUNDLELOADLIST");
        mSelectedDeliveryList = data.getParcelableArrayList("listDeliveryChose");
    }

    private void setupRecyclerView() {
        mLoadDeliveryAdapter = new LoadDeliveryAdapter(new ArrayList<SelectableDelivery>(), this, this);
        rcDestinationList.setAdapter(mLoadDeliveryAdapter);
        rcDestinationList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDestinationList() {

        BaseApi baseApi = new BaseApi(mPrefsHelper.getUserID(), mPrefsHelper.getHaiSoDate());
        mDeliveryReportService.getDeliveryList(baseApi, new IDeliverReportService.LoadDeliveryListCallback() {

            @Override
            public void onLoading() {

            }

            @Override
            public void onSuccess(List<MoprosDelivery> deliveryList) {
                dialogUtils.hideProgress();
                mSelectedDeliveryList = new DeliverParser().convertFullDelivery(deliveryList, mSelectedDeliveryList);
                mLoadDeliveryAdapter.updateData(new DeliverParser().compareSelectDelivery(deliveryList, mSelectedDeliveryList));
            }

            @Override
            public void onError(String e) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(e, LoadSupplierActivity.this);
            }
        });
    }

    @OnClick(R.id.btnDecideReport)
    public void onCLickBtnDecideReport(View view) {
        Intent returnIntent = new Intent();
        Bundle args = new Bundle();
        args.putParcelableArrayList("selectedDeliveryList", mSelectedDeliveryList);
        returnIntent.putExtra("BUNDLERETURNLISTLIST", args);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    @Override
    public void onItemClick(SelectableDelivery delivery, int position) {
        if (!delivery.isSelected()) addToSelectedList(delivery);
        else removeFromSelectedList(delivery);
        delivery.setSelected(!delivery.isSelected());
    }

    private void addToSelectedList(SelectableDelivery delivery) {
        mSelectedDeliveryList.add(delivery);
    }

    private void removeFromSelectedList(SelectableDelivery delivery) {
        mSelectedDeliveryList.remove(delivery);
    }
}

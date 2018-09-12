package jp.co.ienter.mopros.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.fragments.deliver_record.FragmentDestinationChart1;
import jp.co.ienter.mopros.interfaces.IDeliverChartCallback;
import jp.co.ienter.mopros.interfaces.IDeliverChartDataChangeListener;
import jp.co.ienter.mopros.services.DeliverChartService;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.PreferencesHelper;
import jp.co.ienter.mopros.utils.ScreenTransition;

/**
 * Notice: Must put supplierCode type String with key "supplier_code"
 * when start this activity
 * Default: supplier_code = -1
 */
public class DeliveryChartActivity extends AppCompatActivity {

    @BindView(R.id.activity_destination_chart_fl_container)
    FrameLayout mFragmentContainerFL;
    @BindView(R.id.tv_delivery_address)
    TextView tvDeliveryAddress;
    @BindView(R.id.tv_delivery_zip)
    TextView tvDeliveryZip;
    @BindView(R.id.tv_delivery_name)
    TextView tvDeliveryName;


    private DialogUtils mDialogUtils;
    private IDeliverChartDataChangeListener dataChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_chart);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        setupViewPager();
        mDialogUtils = new DialogUtils(this);
        String supplierCode = getIntent().getStringExtra("supplier_code");
        //getDeliverChartInfo( "002", "11");

        // comment wait api
        if(supplierCode == null || supplierCode.isEmpty()){
        } else {
            getDeliverChartInfo(PreferencesHelper.getInstance(this).getUserID(), supplierCode);
        }
    }


    private void getDeliverChartInfo(String id, String supplierCode){
        mDialogUtils.showLoadingProgress();
        new DeliverChartService().getDeliverChartInfo(id, supplierCode, new IDeliverChartCallback() {
            @Override
            public void onSuccess(DeliverChartModel model) {
                mDialogUtils.hideProgress();
                if(model != null){
                    dataChangeListener.onChanged(model);
                    tvDeliveryAddress.setText(getResources().getString(R.string.delivery_chart_lable_adress) +
                            " " + model.getResult().get(0).getAddress());
                    tvDeliveryZip.setText(getResources().getString(R.string.delivery_chart_lable_zip) +
                            " " + model.getResult().get(0).getZip());
                    tvDeliveryName.setText(model.getResult().get(0).getNonyuName());
                }
            }

            @Override
            public void onError(String error) {
                mDialogUtils.hideProgress();
                mDialogUtils.showDialog(error,DeliveryChartActivity.this);
            }
        });
    }

    private void setupViewPager() {
        new ScreenTransition(getSupportFragmentManager()).replaceFragment(R.id.activity_destination_chart_fl_container,
                FragmentDestinationChart1.newInstance());
    }


    public void setDataChangeListener(IDeliverChartDataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

}

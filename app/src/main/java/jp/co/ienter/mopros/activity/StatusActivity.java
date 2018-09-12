package jp.co.ienter.mopros.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseActivity;
import jp.co.ienter.mopros.activity.base.BaseNoMessageActivity;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.interfaces.StatusCallback;
import jp.co.ienter.mopros.models.DeliveryStatus;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.services.MessageService;
import jp.co.ienter.mopros.services.StatusService;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/19/18.
 */

public class StatusActivity extends BaseNoMessageActivity {

    @BindView(R.id.include_general_info_tv_delivery_date) TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    private TableLayout tblStatus;
    private LinearLayout lblRowsData;
    private ArrayList<DeliveryStatus> listDeliveryStatus;
    private DialogUtils dialogUtils = new DialogUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
//        tblStatus=(TableLayout) findViewById(R.id.tblStatus);
        lblRowsData=(LinearLayout) findViewById(R.id.lblRowsData);
        ButterKnife.bind(this);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
        initView();
        loadData();

    }

    private void initView() {
        txtStatus.setText(getIntent().getExtras().getString("status"));
    }

    @SuppressLint("ResourceAsColor")
    private void setDataWithView(ArrayList<DeliveryStatus> listStatus){
        lblRowsData.removeAllViews();
        for (int i=0;i<listStatus.size();i++){
            if(listStatus.get(i).getKanryo_flg() != 2) {
                //line
                final TextView tvColumn1 = new TextView(this);

                tvColumn1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn1.setBackgroundColor(R.color.colorBorderTable);

                //line
                final TextView tvColumn2 = new TextView(this);

                tvColumn2.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn2.setBackgroundColor(R.color.colorBorderTable);

                //line
                final TextView tvColumn3 = new TextView(this);

                tvColumn3.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn3.setBackgroundColor(R.color.colorBorderTable);

                //line
                final TextView tvColumn4 = new TextView(this);

                tvColumn4.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn4.setBackgroundColor(R.color.colorBorderTable);

                //line
                final TextView tvColumn5 = new TextView(this);

                tvColumn5.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn5.setBackgroundColor(R.color.colorBorderTable);

                //line
                final TextView tvColumn6 = new TextView(this);

                tvColumn6.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT
                ));
                tvColumn6.setBackgroundColor(R.color.colorBorderTable);

                //row 1
                final TextView tv1 = new TextView(this);
                if (i == -1) {
                    tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv1.setPadding(5, 5, 0, 5);
                } else {
                    tv1.setLayoutParams(new TableRow.LayoutParams(0,
                            TableRow.LayoutParams.MATCH_PARENT, 1));
                    tv1.setPadding(5, 0, 0, 5);

                }

                tv1.setGravity(Gravity.CENTER);

                if (i == -1) {
                    tv1.setText("Customer");
                    tv1.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    tv1.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv1.setTextColor(Color.parseColor("#000000"));
                    tv1.setText(listStatus.get(i).getName());
                }

                //row 1
                final TextView tv2 = new TextView(this);
                if (i == -1) {
                    tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT, 1));
                    tv2.setPadding(5, 5, 0, 5);
                } else {
                    tv2.setLayoutParams(new TableRow.LayoutParams(0,
                            TableRow.LayoutParams.MATCH_PARENT, 1));
                    tv2.setPadding(5, 0, 0, 5);
                }

                tv2.setGravity(Gravity.CENTER);


                if (i == -1) {
                    tv2.setText("Customer");
                    tv2.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    tv2.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv2.setTextColor(Color.parseColor("#000000"));
                    tv2.setText(listStatus.get(i).getShitei_time());
                }

                //row 3
                final TextView tv3 = new TextView(this);
                if (i == -1) {
                    tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv3.setPadding(5, 5, 0, 5);
                } else {
                    tv3.setLayoutParams(new TableRow.LayoutParams(0,
                            TableRow.LayoutParams.MATCH_PARENT, 1));
                    tv3.setPadding(5, 0, 0, 5);
                }

                tv3.setGravity(Gravity.CENTER);


                if (i == -1) {
                    tv3.setText("Customer");
                    tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv3.setTextColor(Color.parseColor("#000000"));
                    tv3.setText(listStatus.get(i).getReach_time());
                }

                //row 4
                final TextView tv4 = new TextView(this);
                if (i == -1) {
                    tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv4.setPadding(5, 5, 0, 5);
                } else {
                    tv4.setLayoutParams(new TableRow.LayoutParams(0,
                            TableRow.LayoutParams.MATCH_PARENT, 1));
                    tv4.setPadding(5, 0, 0, 5);
                }

                tv4.setGravity(Gravity.CENTER);


                if (i == -1) {
                    tv4.setText("Customer");
                    tv4.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    tv4.setBackgroundColor(Color.parseColor("#f8f8f8"));
//                    tv4.setTextColor(Color.parseColor("#000000"));
//                    tv4.setText(String.valueOf(listStatus.get(i).getKanryo_flg()));
                    if(listStatus.get(i).getHoryu_flg() == 1){
                       tv4.setText(R.string.txt_pending_kanji);
                       tv4.setTextColor(getResources().getColor(R.color.colorRed));
                    } else {
                        if(listStatus.get(i).getKanryo_flg() == 1){
                            tv4.setText(R.string.txt_done_kanji);
                            tv4.setTextColor(Color.parseColor("#000000"));
                        } else {
                            tv4.setText(R.string.txt_undone_kanji);
                            tv4.setTextColor(getResources().getColor(R.color.colorRed));
                        }
                    }
                }

                //row 5
                final TextView tv5 = new TextView(this);
                if (i == -1) {
                    tv5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv5.setPadding(5, 5, 0, 5);
                } else {
                    tv5.setLayoutParams(new TableRow.LayoutParams(0,
                            TableRow.LayoutParams.MATCH_PARENT, 1));
                    tv5.setPadding(5, 0, 0, 5);
                }

                tv5.setGravity(Gravity.CENTER);


                if (i == -1) {
                    tv5.setText("Customer");
                    tv5.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    tv5.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv5.setTextColor(Color.parseColor("#000000"));
//                    tv5.setText(String.valueOf(listStatus.get(i).getHoryu_flg()));
                    if(listStatus.get(i).getSagyo_kbn().equals("")||listStatus.get(i).getSagyo_kbn() == null || listStatus.get(i).getSagyo_kbn().equals(getString(R.string.blank))){
                        tv5.setText(R.string.blank);
                    } else if(listStatus.get(i).getSagyo_kbn().equals("0")){
                        tv5.setText(R.string.txt_no_kanji);
                    } else  {
                        tv5.setText(R.string.txt_yes_kanji);
                    }
                }
                if ((i % 2) != 0) {
                    tv5.setBackgroundResource(R.color.colorBgTable);
                    tv4.setBackgroundResource(R.color.colorBgTable);
                    tv3.setBackgroundResource(R.color.colorBgTable);
                    tv2.setBackgroundResource(R.color.colorBgTable);
                    tv1.setBackgroundResource(R.color.colorBgTable);
                }

                //line
                final TextView tvLineBottom = new TextView(this);

                tvLineBottom.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                tvLineBottom.setBackgroundColor(R.color.colorBorderTable);

                // add table row
                final TableRow tr = new TableRow(this);
                tr.setId(i + 1);
                TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                tr.setPadding(0, 0, 0, 0);
//            tr.setBackgroundColor(R.color.colorAccent);

//            tr.setBackgroundResource(R.color.colorAccent);
                tr.setLayoutParams(trParams);

                tr.addView(tvColumn1);
                tr.addView(tv1);
                tr.addView(tvColumn2);
                tr.addView(tv2);
                tr.addView(tvColumn3);
                tr.addView(tv3);
                tr.addView(tvColumn4);
                tr.addView(tv4);
                tr.addView(tvColumn5);
                tr.addView(tv5);
                tr.addView(tvColumn6);

                lblRowsData.addView(tr);
                lblRowsData.addView(tvLineBottom);
            }
        }
    }


    private void loadData(){
        listDeliveryStatus=new ArrayList<>();
        dialogUtils.showProgress(StatusActivity.this, getResources().getString(R.string.loading));
        if (!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
            dialogUtils.hideProgress();
            return;
        }
        StatusService.getInstance().getStatus(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(), new StatusCallback() {
            @Override
            public void onSuccess(ArrayList<DeliveryStatus> listStatus) {
                dialogUtils.hideProgress();
                setDataWithView(listStatus);
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error,StatusActivity.this);
            }
        });
//        for (int i=0;i<20;i++){
//            DeliveryStatus objDeliveryStatus=new DeliveryStatus();
//            objDeliveryStatus.setName(i+"商店");
//            objDeliveryStatus.setArriveTime(i+":20");
//            objDeliveryStatus.setDeliverStatus(true);
//            objDeliveryStatus.setJobReport(false);
//            objDeliveryStatus.setDesignationTime(i+":50");
//            listDeliveryStatus.add(objDeliveryStatus);
//        }

    }


    @OnClick(R.id.btnStatusOK)
    public void onCLickBtnStatusOK(View view) {
        finish();
    }

}

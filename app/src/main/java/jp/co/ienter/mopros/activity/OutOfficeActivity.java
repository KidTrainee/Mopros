package jp.co.ienter.mopros.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class OutOfficeActivity extends AppCompatActivity {
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_office);
        ButterKnife.bind(this);
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
    }

    @OnClick(R.id.btnMainMenu)
    public void onCLickBtnMainMenu(View view) {
        finish();
    }

    @OnClick(R.id.btnReportIn)
    public void onCLickBtnReportIn(View view){
        Intent intentMainMenu=new Intent(this,OfficeReportActivity.class);
        intentMainMenu.putExtra("name",1);
        startActivity(intentMainMenu);
    }
    @OnClick(R.id.btnReportOut)
    public void onCLickBtnReportOut(View view){
        Intent intentMainMenu=new Intent(this,OfficeReportActivity.class);
        intentMainMenu.putExtra("name",2);
        startActivity(intentMainMenu);
    }
    @OnClick(R.id.btnMsg)
    public void onCLickBtnMsg(View view) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }
}

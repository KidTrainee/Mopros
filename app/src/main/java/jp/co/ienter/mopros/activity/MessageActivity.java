package jp.co.ienter.mopros.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.interfaces.MessageCallBack;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.services.MessageService;
import jp.co.ienter.mopros.utils.DateTimeConverter;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class MessageActivity extends AppCompatActivity {
    int i,j,check;
    List<Message> myStringList = new ArrayList<Message>();
    @BindView(R.id.layoutButtonResponse)
    LinearLayout layoutButton;
    @BindView(R.id.tvMessage) TextView txtMessage;
    @BindView(R.id.tvConfirm) TextView txtConfirm;
    @BindView(R.id.tvNext) TextView txtNext;
    @BindView(R.id.tvBack) TextView txtBack;
    @BindView(R.id.include_general_info_tv_delivery_date)
    TextView include_general_info_tv_delivery_date;
    @BindView(R.id.include_general_info_tv_id)
    TextView include_general_info_tv_id;
    @BindView(R.id.layoutError)
    RelativeLayout layoutError;
    @BindView(R.id.tvError)
    TextView txtError;
    private DialogUtils dialogUtils = new DialogUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        loadMessage(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate());
//        initView();
    }

    private void loadMessage(String id, String haisoDate) {
        dialogUtils.showProgress(MessageActivity.this, getResources().getString(R.string.loading));
        if (!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
            dialogUtils.hideProgress();
            return;
        }
        MessageService.getInstance().getMessage(id, haisoDate, new MessageCallBack() {
            @Override
            public void onSuccess(ArrayList<Message> listMessage) {
                dialogUtils.hideProgress();
                myStringList = listMessage;
                for(int q = 0;q<myStringList.size();q++){
                    if(myStringList.get(q).getIs_read()==0) check++;
                }
                initView();
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
//                LinearLayout layout = (LinearLayout) findViewById(R.id.layoutMessageScreen);
//                for (int q = 0; q < layout.getChildCount(); q++) {
//                    View child = layout.getChildAt(q);
//                    child.setVisibility(View.GONE);
//                }
//                dialogUtils.showDialog(error,MessageActivity.this);
                layoutError.setVisibility(View.VISIBLE);
                txtError.setText(error);
            }
        });
    }

    private void initView() {
        i = 0;
        setButton();
        setButtonResponse();
        txtMessage.setText(myStringList.get(i).getMessage());
        txtMessage.setMovementMethod(new ScrollingMovementMethod());
        DateTimeConverter dateTimeConverter = new DateTimeConverter();
        include_general_info_tv_delivery_date.setText(dateTimeConverter.convertHaisoDate(PreferencesHelper.getInstance(this).getHaiSoDate()));
        include_general_info_tv_id.setText(PreferencesHelper.getInstance(this).getUserID());
    }

    private void setButtonResponse() {
        j = 1;
        layoutButton.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        while (!myStringList.get(i).getRes_pattern().getPattern(j).equals("")){
            final Button btn = (Button) inflater.inflate(R.layout.button_response, layoutButton, false);
            btn.setText(myStringList.get(i).getRes_pattern().getPattern(j));
            if(j >1) {
                LinearLayout.LayoutParams buttonLayoutParams = (LinearLayout.LayoutParams) btn.getLayoutParams();
                buttonLayoutParams.setMargins(10, 0, 0, 0);
                btn.setLayoutParams(buttonLayoutParams);
            }
            if(myStringList.get(i).getIs_read()==1){
                btn.setEnabled(false);
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callApiResponse(PreferencesHelper.getInstance(getApplicationContext()).getUserID(), PreferencesHelper.getInstance(getApplicationContext()).getHaiSoDate(),
                            myStringList.get(i).getSeq_no(),btn.getText().toString());
                }
            });
            layoutButton.addView(btn);
            j++;
        }
    }

    private void callApiResponse(String id, String haisoDate,int seq_no,String response) {
        dialogUtils.showProgress(MessageActivity.this, getResources().getString(R.string.loading));
        if (!InternetManager.hasInternet(this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet), this);
            dialogUtils.hideProgress();
            return;
        }
        MessageService.getInstance().callApiResponse(id, haisoDate,String.valueOf(seq_no),response, new LoginCallback() {
            @Override
            public void onSuccess(String response) {
                check--;
               dialogUtils.hideProgress();
//               txtConfirm.setText(getResources().getString(R.string.txt_message_confirm));
//                myStringList.get(i).setIs_read(1);
//                for(int k=0;k<layoutButton.getChildCount();k++){
//                    View child=layoutButton.getChildAt(k);
//                    child.setEnabled(false);
//                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("check",check);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error,MessageActivity.this);
            }
        });
    }

    @OnClick(R.id.tvBack)
    public void onCLickBack(View view) {
        txtMessage.setText(myStringList.get(--i).getMessage());
        setButton();
        setButtonResponse();
    }

    @OnClick(R.id.tvNext)
    public void onCLickNext(View view) {
        txtMessage.setText(myStringList.get(++i).getMessage());
        setButton();
        setButtonResponse();
    }
    private void setButton() {
        txtConfirm.setText(myStringList.get(i).getIs_read() == 0?getResources().getString(R.string.txt_message):getResources().getString(R.string.txt_message_confirm));
        txtMessage.scrollTo(0,0);
        txtMessage.setMovementMethod(new ScrollingMovementMethod());
        if(i==0){
            txtBack.setVisibility(View.GONE);
            if(myStringList.size()==1) {
                txtNext.setVisibility(View.GONE);
            } else {
                txtNext.setVisibility(View.VISIBLE);
            }
        }
        else if(i== myStringList.size()-1){
            txtNext.setVisibility(View.GONE);
            txtBack.setVisibility(View.VISIBLE);
        } else {
            txtBack.setVisibility(View.VISIBLE);
            txtNext.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("check",check);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

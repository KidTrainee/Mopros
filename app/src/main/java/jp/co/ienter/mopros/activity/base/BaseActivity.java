package jp.co.ienter.mopros.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.MessageActivity;
import jp.co.ienter.mopros.interfaces.MessageCallBack;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.services.MessageService;
import jp.co.ienter.mopros.utils.AppConstants;
import jp.co.ienter.mopros.utils.PreferencesHelper;

/**
 * Created by thanhnv on 7/18/18.
 */

public abstract class BaseActivity extends LogcatActivity {
    private PreferencesHelper mPrefsHelper;
    String id = "";
    String haisoDate = "";
    public Button btnMessage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPrefsHelper = PreferencesHelper.getInstance(this);
        id = mPrefsHelper.getUserID();
        haisoDate = mPrefsHelper.getHaiSoDate();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btnMessage = findViewById(R.id.btnMsg);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, MessageActivity.class);
                startActivityForResult(intent, AppConstants.REQUEST_CODE_MESSAGE);
            }
        });
        checkMessage();
    }

    public void checkMessage(){
        MessageService.getInstance().getMessage(id, haisoDate, new MessageCallBack() {
            @Override
            public void onSuccess(ArrayList<Message> listMessage) {
                for(int i = 0;i<listMessage.size();i++){
                    if(listMessage.get(i).getIs_read() == 0){
                        btnMessage.setTextColor(getResources().getColor(R.color.colorBlack));
                        btnMessage.setBackgroundResource(R.drawable.background_message);
                        return;
                    }
                }
                btnMessage.setTextColor(getResources().getColor(R.color.colorWhite));
                btnMessage.setBackgroundResource(R.drawable.my_button_mainmenu);
            }

            @Override
            public void onError(String error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConstants.REQUEST_CODE_MESSAGE) {
            if(resultCode == Activity.RESULT_OK){
//                int check=data.getIntExtra("check",0);
//                if(check==0){
//                    btnMessage.setTextColor(getResources().getColor(R.color.colorWhite));
//                    btnMessage.setBackgroundResource(R.drawable.my_button_mainmenu);
//                } else {
//                    btnMessage.setTextColor(getResources().getColor(R.color.colorBlack));
//                    btnMessage.setBackgroundResource(R.drawable.background_message);
//                }
                checkMessage();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityR
}

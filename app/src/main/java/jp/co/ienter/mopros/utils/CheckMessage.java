package jp.co.ienter.mopros.utils;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.MessageActivity;
import jp.co.ienter.mopros.interfaces.MessageCallBack;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.services.MessageService;

/**
 * Created by donghv on 8/23/18.
 */

public class CheckMessage {
    static int check = 0;
    public static boolean checkMessage(String id, String haisoDate){
        MessageService.getInstance().getMessage(id, haisoDate, new MessageCallBack() {
            @Override
            public void onSuccess(ArrayList<Message> listMessage) {
                for(int i = 0;i<listMessage.size();i++){
                    if(listMessage.get(i).getIs_read() == 0){
                       check++;
                    }
                }
            }

            @Override
            public void onError(String error) {
//                dialogUtils.hideProgress();
            }
        });
        if(check==0){
            return false;
        }
        else {
            return true;
        }
    }
}

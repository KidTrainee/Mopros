package jp.co.ienter.mopros.services;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.interfaces.MessageCallBack;
import jp.co.ienter.mopros.interfaces.ReportLoadingCallback;
import jp.co.ienter.mopros.models.Message;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

/**
 * Created by donghv on 8/23/18.
 */

public class MessageService {
    private final String TAG = MessageService.class.getSimpleName();
    private static MessageService sInstance = new MessageService();

    public static MessageService getInstance() {
        if (sInstance == null) {
            sInstance = new MessageService();
        }
        return sInstance;
    }
    public void getMessage(final String id, final String haisoDate, final MessageCallBack callback){

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getMessage(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        JSONObject jsonObject = new JSONObject(response);
                        ArrayList<Message> listMessage =  new Gson().fromJson(jsonObject.getString("result"),
                                new TypeToken<List<Message>>(){}.getType());
                        callback.onSuccess(listMessage);
                    } else {
                        callback.onError(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(String.valueOf(e));
                }
            }

            @Override
            public void onError(String errors) {
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date",haisoDate);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }
    public void callApiResponse(final String id, final String haisoDate, final String seq_no,final String response, final LoginCallback callback){

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().responseMessage(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject myObject = new JSONObject(response);
                    String messages = String.valueOf(myObject.get("messages"));
                    if(messages.equals("")){
                        callback.onSuccess(response);
                    } else {
                        callback.onError(messages);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(String.valueOf(e));
                }
            }

            @Override
            public void onError(String errors) {
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("haiso_date",haisoDate);
                params.put("seq_no", seq_no);
                params.put("response",response);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }
}

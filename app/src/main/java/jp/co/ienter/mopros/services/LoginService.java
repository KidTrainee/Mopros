package jp.co.ienter.mopros.services;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.co.ienter.mopros.MoprosApp;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.interfaces.MainMenuCallBack;
import jp.co.ienter.mopros.models.MainMenuInfor;
import jp.co.ienter.mopros.utils.ConfigAPIs;
import jp.co.ienter.mopros.utils.CustomStringRequest;

/**
 * Created by thanhnv on 7/23/18.
 */

public class LoginService {

    private final String TAG = LoginService.class.getSimpleName();
    private static LoginService sInstance = new LoginService();

    public static LoginService getInstance(){
        if(sInstance == null){
            sInstance = new LoginService();
        }
        return sInstance;
    }

    public void getInfor(final String id, final String pass, final LoginCallback callback){
         Log.d("LoginService.class", "leaveApp ======>API : " +  ConfigAPIs.getInstance().login());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().login(), new CustomStringRequest.IResponseStringCallback() {

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
                Log.d("Response: ",errors);
                callback.onError(errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("pass",pass);
                return params;
            }
        };
        Log.d(TAG, "getInfor: " + (MoprosApp.getInstance()==null));
        Log.d(TAG, "getInfor: " + (baseStringRequest == null));
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

    public void logOut(final String id, final LoginCallback callback){
        Log.d("LoginService.class", "leaveApp ======>API : " +  ConfigAPIs.getInstance().login());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().logout(), new CustomStringRequest.IResponseStringCallback() {

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
                Log.d("Response: ",errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }

    public void getMainMenuInfor(final String id, final String haisoDate, final MainMenuCallBack callback){
        Log.d("LoginService.class", "leaveApp ======>API : " +  ConfigAPIs.getInstance().login());

        CustomStringRequest baseStringRequest = new CustomStringRequest(Request.Method.POST,
                ConfigAPIs.getInstance().getMainMenu(), new CustomStringRequest.IResponseStringCallback() {

            @Override
            public void onSuccess(String response) {
                Log.i(TAG + " Login", response);
                try {
                    JSONObject jsonResponse=new JSONObject(response);
                    String jsonResult=jsonResponse.getString("result");
                    if("".equalsIgnoreCase(jsonResponse.getString("messages"))) {
                        MainMenuInfor objMainMenuInfor = new Gson().fromJson(jsonResult, MainMenuInfor.class);
                        callback.onSuccess(objMainMenuInfor);
                    }else{
                        callback.onError(jsonResponse.getString("messages"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
//                    callback.onError(ResultCode.Failed.toString());
                }
            }

            @Override
            public void onError(String errors) {
                callback.onError(errors);
                Log.d("Response: ",errors);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("haiso_date", haisoDate);
                return params;
            }
        };
        MoprosApp.getInstance().addToRequestQueue(baseStringRequest);

    }
}

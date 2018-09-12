package jp.co.ienter.mopros.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.base.BaseNoMessageActivity;
import jp.co.ienter.mopros.interfaces.LoginCallback;
import jp.co.ienter.mopros.services.LoginService;
import jp.co.ienter.mopros.utils.DialogUtils;
import jp.co.ienter.mopros.utils.InputManager;
import jp.co.ienter.mopros.utils.InternetManager;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class LoginActivity extends BaseNoMessageActivity {
    @BindView(R.id.editID)
    EditText edtID;
    @BindView(R.id.editPassword)
    EditText edtPass;
    private PreferencesHelper mPrefsHelper;
    private DialogUtils dialogUtils = new DialogUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        checkLogin();
        mPrefsHelper = PreferencesHelper.getInstance(this);
        edtID.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("Keyboard", "" + actionId);
                edtPass.setImeOptions(EditorInfo.IME_ACTION_DONE);
                return false;
            }});
        edtPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("Keyboard", "" + actionId);
                loginMoporos();
                return false;
            }});


    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT){
            Log.d("Quang Huy", "Key Up : " + keyCode);
            if(edtPass.isFocused() && edtPass.getText().toString().isEmpty() ||
                    edtID.isFocused() && edtID.getText().toString().isEmpty()){
                return true;
            }
            if(edtPass.isFocused() && !edtPass.getText().toString().isEmpty()){
                if(edtPass.getSelectionEnd() == edtPass.getText().length()){
                    return true;
                }
            }
            if(edtID.isFocused() && !edtID.getText().toString().isEmpty()){
                if(edtID.getSelectionEnd() == edtID.getText().length()){
                    return true;
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT){
            Log.d("Quang Huy", "Key Down : " + keyCode);
            if(edtPass.isFocused() && edtPass.getText().toString().isEmpty() ||
                    edtID.isFocused() && edtID.getText().toString().isEmpty()){
                return true;
            }
            if(edtPass.isFocused() && !edtPass.getText().toString().isEmpty()){
                if(edtPass.getSelectionEnd() == edtPass.getText().length()){
                    return true;
                }
            }
            if(edtID.isFocused() && !edtID.getText().toString().isEmpty()){
                if(edtID.getSelectionEnd() == edtID.getText().length()){
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

//    private void checkLogin() {
//        mPrefsHelper = PreferencesHelper.getInstance(this);
//        if(mPrefsHelper.getUserLogin()){
//            loginComplete();
//        }
//    }

    @OnClick(R.id.btnLogin)
    public void onCLickBtnLogin(View view) {
        loginMoporos();
    }

    private void loginMoporos(){
        if(!InternetManager.hasInternet(LoginActivity.this)) {
            dialogUtils.showDialog(getResources().getString(R.string.no_internet),LoginActivity.this);
            return;
        }
        if(InputManager.validation(edtID.getText().toString())){
            dialogUtils.showDialog(getResources().getString(R.string.check_id_pass),LoginActivity.this);
            return;
        }
        dialogUtils.showProgress(LoginActivity.this,getResources().getString(R.string.loading));
        LoginService.getInstance().getInfor(edtID.getText().toString(),edtPass.getText().toString().trim(),new LoginCallback() {
            @Override
            public void onSuccess(String response) {
                dialogUtils.hideProgress();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    jsonObject.getJSONArray("result").optJSONObject(0).getString("haiso_date");
//                    mPrefsHelper.setHaiSoDate(jsonObject.getJSONArray("result").optJSONObject(0).getString("haiso_date"));

//                    mPrefsHelper.setUserLogin(PreferencesHelper.FLAG_USER_LOGIN_DECISION);
                    mPrefsHelper.setUserID(edtID.getText().toString());
//                    mPrefsHelper.setUserPass(edtPass.getText().toString());
                    loginComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                dialogUtils.hideProgress();
                dialogUtils.showDialog(error,LoginActivity.this);
            }

        });
    }

    private void loginComplete() {
        Intent intentMainMenu=new Intent(this,MainMenuActivity.class);
        startActivity(intentMainMenu);
    }

}

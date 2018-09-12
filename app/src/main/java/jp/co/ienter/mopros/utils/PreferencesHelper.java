package jp.co.ienter.mopros.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.SelectableDelivery;

import static jp.co.ienter.mopros.utils.PreConditions.checkNotNull;

public class PreferencesHelper {
    private static PreferencesHelper instance;
    private SharedPreferences mPrefs;
    private String FLAG_DELIVERY_REPORT = "flag_delivery_report";
    private String FLAG_USER_LOGIN = "flag_user_login";
    private String FLAG_USER_ID = "flag_user_id";
    private String FLAG_USER_PASS = "flag_user_pass";
    private String FLAG_DATE = "flag_date";
    private String FLAG_HAISO_DATE = "flag_haiso_date";
    private String FLAG_START_WORK_TIME = "flag_start_work";
    private String FLAG_DATE_START_WORK_TIME = "flag_date_start_work";
    private String FLAG_STATUS_HAISO_CNT = "flag_status_haiso_cnt";

    public static final int FLOW_DELIVERY_REPORT_DECISION = 0;
    public static final int FLOW_DELIVERY_REPORT_BREAK = 1;
    public static final int FLOW_DELIVERY_REPORT_RETURN = 2;
    public static final Boolean FLAG_USER_LOGIN_DECISION= true;
    public static final Boolean FLAG_USER_LOGIN_BREAK= false;
    private static final String FLAG_DELIVER_NONYU_CODE = "flag_deliver_nonyu_code";
    private static final String FLAG_LIST_SELECTED_DELIVER = "flag_list_selected_deliver";

    public static PreferencesHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesHelper(context);
        }
        return instance;
    }

    private PreferencesHelper(Context context) {
        checkNotNull(context);
        this.mPrefs = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     *
     * @param flag get one of values : {FLOW_DELIVERY_REPORT_DECISION,
     *             FLOW_DELIVERY_REPORT_BREAK,
     *             FLOW_DELIVERY_REPORT_RETURN}
     */
    public void setDeliveryReportFlag(int flag) {
        mPrefs.edit().putInt(FLAG_DELIVERY_REPORT, flag).apply();
    }

    public int getDeliveryReportFlag() {
        return mPrefs.getInt(FLAG_DELIVERY_REPORT, FLOW_DELIVERY_REPORT_DECISION);
    }
    public void setUserLogin(boolean flag) {
        mPrefs.edit().putBoolean(FLAG_USER_LOGIN, flag).apply();
    }

    public boolean getUserLogin() {
        return mPrefs.getBoolean(FLAG_USER_LOGIN, FLAG_USER_LOGIN_BREAK);
    }
    public void setUserID(String flag) {
        mPrefs.edit().putString(FLAG_USER_ID, flag).apply();
    }

    public String getUserID() {
        return mPrefs.getString(FLAG_USER_ID, "");
    }
    public void setUserPass(String flag) {
        mPrefs.edit().putString(FLAG_USER_PASS, flag).apply();
    }


    public String getUserPass() {
        return mPrefs.getString(FLAG_USER_PASS, "");
    }

    public String getDate() {
        return mPrefs.getString(FLAG_DATE, "11月29日");
    }
    public void setDate(String flag) {
        mPrefs.edit().putString(FLAG_DATE, flag).apply();
    }

    public String getHaiSoDate() {
        return mPrefs.getString(FLAG_HAISO_DATE, "2018/02/15");
    }
    public void setHaiSoDate(String flag) {
        mPrefs.edit().putString(FLAG_HAISO_DATE, flag).apply();
    }
    public void setStartWorkTime(String flag) {
        mPrefs.edit().putString(FLAG_START_WORK_TIME, flag).apply();
    }
    public String getStartWorkTime() {
        return mPrefs.getString(FLAG_START_WORK_TIME, "");
    }
    public void setDateStartWorkTime(String flag) {
        mPrefs.edit().putString(FLAG_DATE_START_WORK_TIME, flag).apply();
    }
    public String getDateStartWorkTime() {
        return mPrefs.getString(FLAG_DATE_START_WORK_TIME, "");
    }
    public void setStatusHaisocnt(boolean flag){
        mPrefs.edit().putBoolean(FLAG_STATUS_HAISO_CNT, flag).apply();
    }
    public Boolean getStatusHaisocnt() {
        return mPrefs.getBoolean(FLAG_STATUS_HAISO_CNT, false);
    }

    public String getNonyuCode() {
        return mPrefs.getString(FLAG_DELIVER_NONYU_CODE, "");
    }

    public void setNonyuCode(String nonyu_code) {
        mPrefs.edit().putString(FLAG_DELIVER_NONYU_CODE, nonyu_code).apply();
    }

    @Nullable
    public List<MoprosDelivery> getSelectedDeliverList() {
        try {
            String list = mPrefs.getString(FLAG_LIST_SELECTED_DELIVER, "");
            List<MoprosDelivery> deliveryList = new Gson().fromJson(list,
                    new TypeToken<List<MoprosDelivery>>() {}.getType());
            return deliveryList;
        } catch (IllegalStateException e) {
            return null;
        }
    }

    public void setSelectedDeliverList(List<MoprosDelivery> selectedList) {
        String jo = new Gson().toJson(selectedList);
        mPrefs.edit().putString(FLAG_LIST_SELECTED_DELIVER,
                jo).apply();
    }
}

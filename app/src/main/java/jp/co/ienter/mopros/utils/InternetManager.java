package jp.co.ienter.mopros.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetManager {

    public static boolean hasInternet(Activity activity) {
        if (activity == null) return false;
        if (!activity.isFinishing()) {
            ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        }
        return true;
    }

}

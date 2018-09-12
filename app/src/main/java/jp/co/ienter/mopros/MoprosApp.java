package jp.co.ienter.mopros;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import jp.co.ienter.mopros.models.apis.StatusInfo;

/**
 * Created by thanhnv on 7/23/18.
 */

public class MoprosApp extends Application {

    private static MoprosApp sInstance;
    private static String TAG = MoprosApp.class.getSimpleName();
//    private LoadingDebounce mLoadingDebounce;

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    private RequestQueue mRequestQueue;

    public static MoprosApp getInstance(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

//        mLoadingDebounce = LoadingDebounce.initialize(new DialogUtils(this));
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }



    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void clearAllQueueVolley(){
        if(mRequestQueue!=null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    Log.d("DEBUG","request running: "+request.getTag().toString());
                    return true;
                }
            });
        }
    }

    public <T> void addRequestQueueLogin(Request<T> req){
        clearAllQueueVolley();

        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setShouldCache(false);
        req.setTag(TAG);
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        getRequestQueue().add(req);
    }

    public void cancelPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }
}

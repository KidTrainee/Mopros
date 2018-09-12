package jp.co.ienter.mopros.activity.deliver_report.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.services.requests.JSONBaseRequest;
import jp.co.ienter.mopros.utils.DebugUtils;

public abstract class ResponseCallback implements JSONBaseRequest.IResponseJSONCallback {

    private String TAG = getClass().getSimpleName();
    private IUpdateCallback mUpdateViewCallback;
    protected int mStatus = -100;
    protected String mMessages;

    public ResponseCallback(IUpdateCallback updateViewCallback) {
        this.mUpdateViewCallback = updateViewCallback;
    }

    @Override
    public void onSuccess(JSONObject jo) {
        DebugUtils.logDebugs(jo.toString());
        try {
            mStatus = jo.getInt("status");
            mMessages = jo.getString("messages");
            switch (mStatus) {
                case 888:
                    // {"タイムアウトが発生しました。"}(đã phát sinh timeout)
                case 999:
                    // {"システムエラーが発生しました。"}( đã phát sinh system error)
                    mUpdateViewCallback.onError(new Throwable(mMessages));
            }
        } catch (JSONException e) {
            Log.e(TAG, "onSuccess: ", e);
            onError(e);
        }
    }

    @Override
    public void onError(Throwable errors) {
        Log.e(TAG, "onError: ", errors);
        mUpdateViewCallback.onError(new Throwable("Error! Please check your internet connection!"));
    }
}

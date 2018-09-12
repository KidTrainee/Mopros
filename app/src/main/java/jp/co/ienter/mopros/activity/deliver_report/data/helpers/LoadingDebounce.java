package jp.co.ienter.mopros.activity.deliver_report.data.helpers;

import android.os.Handler;
import android.util.Log;

import jp.co.ienter.mopros.utils.DialogUtils;

public class LoadingDebounce implements IDebounceHandler {

    private static final long MIN_TIME_EFFECT = 300;
    private static final String TAG = LoadingDebounce.class.getSimpleName();
    private static LoadingDebounce instance;

    public static int isBusy;

    private DialogUtils mDialogUtils;
    private Runnable mRunnable;

    public static LoadingDebounce getInstance() {
        if (instance == null) {
            throw new NullPointerException(LoadingDebounce.class.getSimpleName()
                    + " has not been initialized in this Application Context");
        }
        return instance;
    }

    public static void initialize(DialogUtils dialogUtils) {
        if (instance == null) {
            try {
                if (dialogUtils.getContext() == null) {
                    throw new NullPointerException(DialogUtils.class.getSimpleName() + " parameter must be initialized " +
                            "with this constructor: \n new DialogUtils(Context)");
                } else instance = new LoadingDebounce(dialogUtils);
            } catch (NullPointerException e) {
                throw new NullPointerException(DialogUtils.class.getSimpleName() + " hasn't been created");
            }
        }
    }

    public static void dispose() {
        if (instance != null) instance = null;
    }

    public LoadingDebounce(DialogUtils dialogUtils) {
        this.mDialogUtils = dialogUtils;
    }

    @Override
    public IDebounceHandler startLoading() {
        if (!isBusy()) {

            if (mDialogUtils != null) mDialogUtils.showLoadingProgress();

            isBusy++;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isBusy()) isBusy--;
                    if (!isBusy()) {
                        if (mDialogUtils != null) mDialogUtils.hideProgress();
                        if (mRunnable == null) {
                            new Handler().post(mRunnable);
                            mRunnable = null;
                        }
                    }
                    Log.d(TAG, "finishStartLoading: " + isBusy);
                }
            }, MIN_TIME_EFFECT);
            Log.d(TAG, "startLoading: " + isBusy);
        } else {
            Log.d(TAG, "startLoadingNoCall: " + isBusy);
        }
        return this;
    }

    @Override
    public boolean isBusy() {
        return isBusy != 0;
    }

    @Override
    public IDebounceHandler loadATask() {
        isBusy++;
        Log.d(TAG, "loadATask: " + isBusy);
        return this;
    }

    @Override
    public IDebounceHandler finishATask() {
        if (isBusy()) isBusy--;
        Log.d(TAG, "finishATask: " + isBusy);
        return this;
    }

    @Override
    public void finishIfNotBusy() {
        if (isBusy()) isBusy--;
        if (!isBusy() && mDialogUtils != null) mDialogUtils.hideProgress();
        Log.d(TAG, "finishIfNotBusy: " + isBusy);
    }

    @Override
    public void finish() {
        if (mDialogUtils != null) mDialogUtils.hideProgress();
        isBusy = 0;
        Log.d(TAG, "finish: " + isBusy);
    }

    public void finishAfterDebounce(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void execAfterLoading(Runnable r) {
        this.mRunnable = r;
    }
}

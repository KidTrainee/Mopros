package jp.co.ienter.mopros.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.Map;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.interfaces.ConfirmListener;

/**
 * Created by donghv on 8/2/18.
 */

public class DialogUtils {
    private Context mContext;

    public DialogUtils() {
    }

    public DialogUtils(Context context) {
        mContext = context;
    }

    public void showDialog(String msg, Context context) {
        getAlertDialogBuilder(msg, context)
                .setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void showDialog(String msg, Context context, final ConfirmListener callback) {
        getAlertDialogBuilder(msg, context)
                .setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onAgree();
                    }
                }).show();
    }

    public void showDialogConfirm(String msg, Context context, final ConfirmListener callback) {
        getAlertDialogBuilder(msg, context)
                .setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        callback.onAgree();
                    }
                })
                .setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }

    private ProgressDialog progressDialog;

    public void showProgress(Context activity, @Nullable String message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("");
        progressDialog.setIndeterminate(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void showLoadingProgress() {
        if (progressDialog == null || !progressDialog.isShowing())
            showProgress(mContext, null);
    }

    private AlertDialog.Builder getAlertDialogBuilder(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setCancelable(false);
        return builder;
    }

    public Context getContext() {
        return mContext;
    }
}

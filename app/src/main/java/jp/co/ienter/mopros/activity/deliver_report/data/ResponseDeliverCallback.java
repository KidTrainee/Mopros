package jp.co.ienter.mopros.activity.deliver_report.data;

import org.json.JSONObject;

import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.ResponseCallback;
import jp.co.ienter.mopros.models.apis.UpdateResult;

public class ResponseDeliverCallback extends ResponseCallback {

    private IUpdateCallback<UpdateResult> mCallback;

        public ResponseDeliverCallback(IUpdateCallback<UpdateResult> callback) {
            super(callback);
            mCallback = callback;
        }

        @Override
        public void onSuccess(JSONObject jo) {
            super.onSuccess(jo);
            switch (mStatus) {
                case 0:
                    // fail
                    mCallback.onError(new Throwable(mMessages));
                    break;
                case 1:
                    // メッセージなし（登録・更新成功）(không có messege(đăng ký/update thành công)
                    UpdateResult result = new UpdateResult();
                    mCallback.onSuccess(result);
                    break;
            }
        }
}

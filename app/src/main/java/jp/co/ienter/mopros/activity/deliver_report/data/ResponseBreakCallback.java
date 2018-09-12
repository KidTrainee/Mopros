package jp.co.ienter.mopros.activity.deliver_report.data;

import org.json.JSONObject;

import jp.co.ienter.mopros.activity.deliver_report.callbacks.IUpdateCallback;
import jp.co.ienter.mopros.activity.deliver_report.data.ResponseCallback;
import jp.co.ienter.mopros.models.apis.UpdateResult;
import jp.co.ienter.mopros.services.requests.JSONBaseRequest;

public class ResponseBreakCallback extends ResponseCallback implements JSONBaseRequest.IResponseJSONCallback {

    private IUpdateCallback<UpdateResult> mCallback;

    public ResponseBreakCallback(IUpdateCallback<UpdateResult> callback) {
        super(callback);
        this.mCallback = callback;
    }

    @Override
    public void onSuccess(JSONObject jo) {
        super.onSuccess(jo);
        if (mStatus != -100) {
            switch (mStatus) {
                case 1:
                    //メッセージなし（登録・更新成功）- No message (registration / update success)
                    UpdateResult result = new UpdateResult();
                    mCallback.onSuccess(result);
                    break;
                case 0:
                    // {"休憩回数の上限を超えています。"}(vượt quá giới hạn số lần nghỉ giải lao)
                    mCallback.onError(new Throwable("休憩回数の上限を超えています。"));
                    break;
            }
        }
    }
}

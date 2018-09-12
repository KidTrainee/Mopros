package jp.co.ienter.mopros.activity.deliver_report.data.helpers;

import org.json.JSONObject;

import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.apis.PendingDeliverApi;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.ReturnApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;

interface IJsonHelper {

    JSONObject convertDeliverArrApiToJO(DeliverArrApi updateApi);

    JSONObject convertBaseApiToJO(BaseApi breakApi);

    JSONObject convertDeliverApi(SimpleDeliverApi api);

    JSONObject convertPendingApiToJO(PendingDeliverApi pendingApi);

    JSONObject convertReturnApi(ReturnApi updateApi);
}

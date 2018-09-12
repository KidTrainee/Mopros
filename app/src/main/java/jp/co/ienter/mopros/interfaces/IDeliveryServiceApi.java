package jp.co.ienter.mopros.interfaces;

public interface IDeliveryServiceApi {
    void getListInfo(String id, String haso_date, IListSortCallBack callback);
}

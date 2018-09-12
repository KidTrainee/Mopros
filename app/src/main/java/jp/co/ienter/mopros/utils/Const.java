package jp.co.ienter.mopros.utils;

public class Const {
    public static final String SORT_IGNORE_NO_TRANSPORT = "2";
    public static final String FLAG_DESTINATION_TYPE_DELIVER = "1";
    public static final String FLAG_DESTINATION_TYPE_PICK_UP = "2";
    public static final String EXTRA_DELIVERY_LIST = "extra_delivery_list";

    public static final int TIME_TYPE_BEFORE_ARRIVAL = 1;
    public static final int TIME_TYPE_AFTER_ARRIVAL = 2;
    //    0:chưa hoàn thành 1:hoàn thành 2:không ship hàng　※「2:không ship hàng」chỉ là trường hợp data ship hàng
    public static final String KANRYO_FLAG_NOT_DONE = "0";
    public static final String KANRYO_FLAG_DONE = "1";
    public static final String KANRYO_FLAG_NOT_DELIVER = "2";

    public static final String IN_REPORT_FLAG_KEY = "in_report";
    public static final String HONRYU_FLAG_NO_PENDING = "0";
    public static final String HONRYU_FLAG_PENDING_PRE_ARRIVAL = "1";
    public static final String HONRYU_FLAG_PENDING_POST_ARRIVAL = "2";

    // JsonKeys
    public static final String RESULT = "result";
    public static final int RETURN_FLAG_DEPARTURE = 0;
    public static final int RETURN_FLAG_ARRIVAL = 1;
}

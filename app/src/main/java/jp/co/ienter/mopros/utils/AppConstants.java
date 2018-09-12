package jp.co.ienter.mopros.utils;

/**
 * Created by thanhnv on 7/20/18.
 */

public class AppConstants {

    public static final String API_GET_DELIVER_REPORTED_DATA = "api/delivery/getReportedData";
    public static int VOLLEY_TIMEOUT = 30000;
    public static int VOLLEY_MAX_NUM_RETRIES = 0;
    public static int REQUEST_CODE_MESSAGE = 2;
    public static int REQUEST_CODE_WORKTIME = 199;
    public static int REQUEST_CODE_LOAD_LIST = 200;
    public static final String ROOT_DATA_API_URL = "http://192.168.1.90:3636";
//    public static final String ROOT_DATA_API_URL = "https://comassort.net/KRS";
    public static final String API_DOMAIN = ROOT_DATA_API_URL + "/%s";

    //DialogUtils APIs
    public static final String API_GET_ITEM_PATTERN = ROOT_DATA_API_URL + "%s/api/login/authenticate" ;
    public static final String API_LOGOUT_ITEM_PATTERN = ROOT_DATA_API_URL + "%s/api/login/logout" ;
    public static final String API_MAINMENU_PATTERN = ROOT_DATA_API_URL + "%s/api/menu/getData" ;
    public static final String API_START_LOADING = ROOT_DATA_API_URL + "%s/api/loading/startLoading";
    public static final String API_END_LOADING = ROOT_DATA_API_URL + "%s/api/loading/endLoading";
    public static final String API_SORT_INFOR_PATTERN = ROOT_DATA_API_URL + "%s/api/delivery/getListData";
    public static final String API_SORT_DELETE_PICKUP_PATTERN = ROOT_DATA_API_URL + "%s/api/delivery/delCargoData";
    public static final String API_SORT_REGISTER_PICKUP_PATTERN = ROOT_DATA_API_URL + "%s/api/delivery/regCargoData";
    public static final String API_UPDATE_NO_DELIVERY_PATTERN = ROOT_DATA_API_URL + "%s/api/delivery/changeDeliveryStatus" ;
    public static final String API_GET_CARGO_LIST_DATA_PATTERN = ROOT_DATA_API_URL + "%s/api/delivery/getCargoListData" ;
    public static final String API_GET_REPORT_DETAIL_LIST = ROOT_DATA_API_URL+"%s/api/loading/getReportDetailList";
    public static final String API_START_WORK_TIME = ROOT_DATA_API_URL + "%s/api/worktime/startWork";
    public static final String API_END_WORK_TIME = ROOT_DATA_API_URL + "%s/api/worktime/finishWork" ;
    public static final String API_WORK_TIME_STATUS = ROOT_DATA_API_URL + "%s/api/worktime/getWorkTimeStatus";
    public static final String API_GET_REPORTED_DATA = ROOT_DATA_API_URL + "%s/api/loading/getReportedData";
    public static final String API_GET_SELECT_REPORT_LIST = ROOT_DATA_API_URL + "%s/api/loading/getSelectReportList";
    public static final String API_NO_REPORT_LOADING = ROOT_DATA_API_URL + "%s/api/loading/noReport";
    public static final String API_RESPONSE_MESSAGE = ROOT_DATA_API_URL + "%s/api/message/regResponseMessage";
    public static final String API_GET_MESSAGE = ROOT_DATA_API_URL + "%s/api/message/getMessageData";
    public static final String API_STATUS = ROOT_DATA_API_URL + "%s/api/referSituation/getListData";
    public static final String API_REG_REPORT_DECISION = "%s/api/transport/reportDecision";
    // Dev: Binh
    public static final String API_START_BREAK = "api/transport/startBreak";
    public static final String API_END_BREAK = "api/transport/endBreak";
    public static final String API_DELIVER_START = "api/transport/startDelivery";
    public static final String API_DELIVER_END = "api/transport/endDelivery";
    public static final String API_PENDING = "api/transport/pending";
    public static final String API_DELIVER_DEPARTURE = "api/transport/departure";
    public static final String API_DELIVER_ARRIVAL = "api/transport/arrival";
    public static final String API_TRANSPORT_NO_REPORT = "api/transport/noReport";

    public static final String API_CHECK_STATUS = "api/transport/getTransportStatus";
    public static final String API_DELIVER_CHART_INFO = "api/transport/getSupplierData";
    public static final String API_REG_WORKING_DATA = "api/transport/regWorkingData";

    //deliver chart
    public static final String KEY_DELIVER_CHART_MODEL = "deliver_chart_model";

    public static final String API_SORT_DELIVERY_DATA = "api/delivery/regSortedDeliveryData";
    public static final String API_REG_LOADING_REPORT = "api/loading/reportDecision";

    public static final String API_REPORT_WAITING_TIME = "api/transport/getReachStatus";

    public static final String API_LOADING_STATUS = "api/loading/getLoadingStatus";

    public static final String API_DELIVER_NO_REPORT = "api/transport/noReport";

    public static final String API_GET_WORKING_DATA = "api/transport/getWorkingData";

    public static final String API_RETURN = "api/transport/regReturn";

    public static final String API_REPORT_MASTER_DATA = "api/transport/getReportMasterData";

    public static final String API_PALETTE_MASTER_DATA = "api/transport/getPaletteMasterData";
}

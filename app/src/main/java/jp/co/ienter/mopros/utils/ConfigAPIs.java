package jp.co.ienter.mopros.utils;

/**
 * Created by thanhnv on 7/23/18.
 */

public class ConfigAPIs {

    private static ConfigAPIs sInstance;

    public static ConfigAPIs getInstance() {
        if (sInstance == null) sInstance = new ConfigAPIs();
        return sInstance;
    }

    public String login() {
        return String.format(AppConstants.API_GET_ITEM_PATTERN, "");
    }

    public String logout() {
        return String.format(AppConstants.API_LOGOUT_ITEM_PATTERN, "");
    }

    public String getMainMenu() {
        return String.format(AppConstants.API_MAINMENU_PATTERN, "");
    }

    public String getSortInfor() {
        return String.format(AppConstants.API_SORT_INFOR_PATTERN, "");
    }

    public String getSortRegisterPickup() {
        return String.format(AppConstants.API_SORT_REGISTER_PICKUP_PATTERN, "");
    }

    public String getSortDeletePickup() {
        return String.format(AppConstants.API_SORT_DELETE_PICKUP_PATTERN, "");
    }

    public String updateNoDeliveryUrl() {
        return String.format(AppConstants.API_UPDATE_NO_DELIVERY_PATTERN, "");
    }

    public String getCargoListDataUrl() {
        return String.format(AppConstants.API_GET_CARGO_LIST_DATA_PATTERN, "");
    }

    public String startLoading() {
        return String.format(AppConstants.API_START_LOADING, "");
    }

    public String endLoading() {
        return String.format(AppConstants.API_END_LOADING, "");
    }

    public String getReportDetailList() {
        return String.format(AppConstants.API_GET_REPORT_DETAIL_LIST, "");
    }

    public String getSelectReportList() {
        return String.format(AppConstants.API_GET_SELECT_REPORT_LIST, "");
    }

    public String noReportLoading() {
        return String.format(AppConstants.API_NO_REPORT_LOADING, "");
    }

    public String startWorkTime() {
        return String.format(AppConstants.API_START_WORK_TIME, "");
    }

    public String endWorkTime() {
        return String.format(AppConstants.API_END_WORK_TIME, "");
    }

    public String statusWorkTime() {
        return String.format(AppConstants.API_WORK_TIME_STATUS, "");
    }

    public String getStatus() {
//        return "https://5b88c07d1863df001433e844.mockapi.io/api/test/getListData";
        return String.format(AppConstants.API_STATUS, "");
    }

    public String getReportedData() {
        return String.format(AppConstants.API_GET_REPORTED_DATA, "");
    }

    public String responseMessage() {
        return String.format(AppConstants.API_RESPONSE_MESSAGE, "");
    }

    public String getMessage() {
        return String.format(AppConstants.API_GET_MESSAGE, "");
    }

    // Dev: Binh
    public String getStartBreakUrl() {
        return createUrl(AppConstants.API_START_BREAK);
    }

    public String getEndBreakUrl() {
        return createUrl(AppConstants.API_END_BREAK);
    }

    public String getPendingUrl() {
        return createUrl(AppConstants.API_PENDING);
    }

    public String getDeliverDepartureUrl() {
        return createUrl(AppConstants.API_DELIVER_DEPARTURE);
    }

    public String getDeliverArrivalUrl() {
        return createUrl(AppConstants.API_DELIVER_ARRIVAL);
    }

    public String getDeliverStartUrl() {
        return createUrl(AppConstants.API_DELIVER_START);
    }

    public String getDeliverEndUrl() {
        return createUrl(AppConstants.API_DELIVER_END);
    }

    public String getCheckStatusUrl() {
        return createUrl(AppConstants.API_CHECK_STATUS);
    }

    public String getReportWaitingTimeUrl() {
        return createUrl(AppConstants.API_REPORT_WAITING_TIME);
    }

    public String getNoReportUrl() {
        return createUrl(AppConstants.API_TRANSPORT_NO_REPORT);
    }

    private String createUrl(String api_path) {
        return String.format(AppConstants.API_DOMAIN, api_path);
    }

    public String getDeliverChartInfo() {
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_DELIVER_CHART_INFO);
    }

    public String getSortDeliveryData() {
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_SORT_DELIVERY_DATA);
    }

    public String getLoadingStatusUrl() {
        //return "https://5b88c07d1863df001433e844.mockapi.io/api/test/loadstatus";
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_LOADING_STATUS);
    }

    public String getDeliverNoReportUrl() {
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_DELIVER_NO_REPORT);
    }

    public String getRegWorkingDataUrl() {
        return createUrl(AppConstants.API_REG_WORKING_DATA);
    }

    public String regLoadingReport() {
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_REG_LOADING_REPORT);
    }

    public String getWorkingDataUrl() {
        return createUrl(AppConstants.API_GET_WORKING_DATA);
    }

    public String getReturnUrl() {
        return createUrl(AppConstants.API_RETURN);
    }
    public String getReportedDataUrl() {
        return createUrl(AppConstants.API_GET_DELIVER_REPORTED_DATA);
    }
    public String regReportDecision(){
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_REG_REPORT_DECISION);
    }
    public String getReportMasterDataUrl(){
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_REPORT_MASTER_DATA);
    }

    public String getPaletteReportMasterUrl(){
        return String.format(AppConstants.API_DOMAIN, AppConstants.API_PALETTE_MASTER_DATA);
    }
}

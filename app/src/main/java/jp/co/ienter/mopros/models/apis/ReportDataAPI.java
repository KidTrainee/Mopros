package jp.co.ienter.mopros.models.apis;

/**
 * Created by thanhnv on 9/12/18.
 */

public class ReportDataAPI {
    String id, haiso_date, data_type;
    ReportDataItem[] transport_data;

    public ReportDataAPI(String id, String haiso_date, String data_type, ReportDataItem[] transport_data) {
        this.id = id;
        this.haiso_date = haiso_date;
        this.data_type = data_type;
        this.transport_data = transport_data;
    }
}

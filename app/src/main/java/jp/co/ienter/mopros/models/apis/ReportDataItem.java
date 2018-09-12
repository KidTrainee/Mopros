package jp.co.ienter.mopros.models.apis;

/**
 * Created by thanhnv on 9/12/18.
 */

public class ReportDataItem {
    String course_cd1_after, course_cd2_after, haiso_order_no, trip, transport_code, gosha;

    public ReportDataItem(String course_cd1_after, String course_cd2_after, String haiso_order_no, String trip, String transport_code, String gosha) {
        this.course_cd1_after = course_cd1_after;
        this.course_cd2_after = course_cd2_after;
        this.haiso_order_no = haiso_order_no;
        this.trip = trip;
        this.transport_code = transport_code;
        this.gosha = gosha;
    }
}

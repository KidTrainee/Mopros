package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;

public class ReportWaitingTimeApi extends BaseApi {

    String course_cd1_after, course_cd2_after, haiso_order_no, trip, transport_code;
    String data_type;


    public ReportWaitingTimeApi(String id, String haiso_date, String  data_type,
                                String course_cd1_after, String course_cd2_after,
                                String haiso_order_no, String trip, String transport_code) {
        super(id, haiso_date);
        this.data_type = data_type;
        this.course_cd1_after = course_cd1_after;
        this.course_cd2_after = course_cd2_after;
        this.haiso_order_no = haiso_order_no;
        this.trip = trip;
        this.transport_code = transport_code;
    }

    protected ReportWaitingTimeApi(Parcel in) {
        super(in);
        this.data_type = in.readString();
        this.course_cd1_after = in.readString();
        this.course_cd2_after = in.readString();
        this.haiso_order_no = in.readString();
        this.trip = in.readString();
        this.transport_code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(data_type);
        parcel.writeString(course_cd1_after);
        parcel.writeString(course_cd2_after);
        parcel.writeString(haiso_order_no);
        parcel.writeString(trip);
        parcel.writeString(transport_code);
    }
}

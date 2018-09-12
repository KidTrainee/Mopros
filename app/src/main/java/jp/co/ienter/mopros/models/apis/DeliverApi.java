package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;
import android.os.Parcelable;

public class DeliverApi implements Parcelable {

    private String course_cd1_after, course_cd2_after, haiso_order_no, trip, transport_code;
    private String gosha;

    public DeliverApi(String course_cd1_after, String course_cd2_after, String haiso_order_no,
                      String trip, String transport_code, String gosha) {
        this.course_cd1_after = course_cd1_after;
        this.course_cd2_after = course_cd2_after;
        this.haiso_order_no = haiso_order_no;
        this.trip = trip;
        this.transport_code = transport_code;
        this.gosha = gosha;
    }

    protected DeliverApi(Parcel in) {
        course_cd1_after = in.readString();
        course_cd2_after = in.readString();
        haiso_order_no = in.readString();
        trip = in.readString();
        transport_code = in.readString();
        gosha = in.readString();
    }

    public static final Creator<DeliverApi> CREATOR = new Creator<DeliverApi>() {
        @Override
        public DeliverApi createFromParcel(Parcel in) {
            return new DeliverApi(in);
        }

        @Override
        public DeliverApi[] newArray(int size) {
            return new DeliverApi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(course_cd1_after);
        parcel.writeString(course_cd2_after);
        parcel.writeString(haiso_order_no);
        parcel.writeString(trip);
        parcel.writeString(transport_code);
        parcel.writeString(gosha);
    }

    public String getCourse_cd1_after() {
        return course_cd1_after;
    }

    public String getCourse_cd2_after() {
        return course_cd2_after;
    }

    public String getHaiso_order_no() {
        return haiso_order_no;
    }

    public String getTrip() {
        return trip;
    }

    public String getTransport_code() {
        return transport_code;
    }

    public String getGosha() {
        return gosha;
    }

    public void setGosha(String gosha) {
        this.gosha = gosha;
    }
}

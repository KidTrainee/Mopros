package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;
import android.util.Log;

public class SimpleDeliverApi extends BaseApi {

    private static final String TAG = SimpleDeliverApi.class.getSimpleName();
    private String data_type, course_cd1_after, course_cd2_after, haiso_order_no, trip, transport_code, gosha;

    public SimpleDeliverApi(String id, String haiso_date, String data_type, String course_cd1_after,
                            String course_cd2_after, String haiso_order_no, String trip,
                            String transport_code, String gosha) {
        super(id, haiso_date);
        this.data_type = data_type;
        this.course_cd1_after = course_cd1_after;
        this.course_cd2_after = course_cd2_after;
        this.haiso_order_no = haiso_order_no;
        this.trip = trip;
        this.transport_code = transport_code;
        this.gosha = gosha;
    }

    public SimpleDeliverApi(Parcel in) {
        super(in);
        this.data_type = in.readString();
        this.course_cd1_after = in.readString();
        this.course_cd2_after = in.readString();
        this.haiso_order_no = in.readString();
        this.trip = in.readString();
        this.transport_code = in.readString();
        this.gosha = in.readString();
    }

    public static final Creator<SimpleDeliverApi> CREATOR = new Creator<SimpleDeliverApi>() {
        @Override
        public SimpleDeliverApi createFromParcel(Parcel in) {
            return new SimpleDeliverApi(in);
        }

        @Override
        public SimpleDeliverApi[] newArray(int size) {
            return new SimpleDeliverApi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
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
        parcel.writeString(gosha);
    }

    public void incrementGosha() {
        try {
            gosha = String.valueOf(Integer.parseInt(gosha) + 1);
        } catch (NumberFormatException e) {
            Log.e(TAG, "incrementGosha: ", e);
        }
    }
}

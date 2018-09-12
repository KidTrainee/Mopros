package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;
import android.os.Parcelable;

public class RegWorkingDataItem implements Parcelable {

    private String course_cd1_after, course_cd2_after, haiso_order_no, trip, shuka_code;

    public RegWorkingDataItem(String course_cd1_after, String course_cd2_after, String haiso_order_no,
                              String trip, String shuka_code) {
        this.course_cd1_after = course_cd1_after;
        this.course_cd2_after = course_cd2_after;
        this.haiso_order_no = haiso_order_no;
        this.trip = trip;
        this.shuka_code = shuka_code;
    }

    protected RegWorkingDataItem(Parcel in) {
        course_cd1_after = in.readString();
        course_cd2_after = in.readString();
        haiso_order_no = in.readString();
        trip = in.readString();
        shuka_code = in.readString();
    }

    public static final Creator<RegWorkingDataItem> CREATOR = new Creator<RegWorkingDataItem>() {
        @Override
        public RegWorkingDataItem createFromParcel(Parcel in) {
            return new RegWorkingDataItem(in);
        }

        @Override
        public RegWorkingDataItem[] newArray(int size) {
            return new RegWorkingDataItem[size];
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
        parcel.writeString(shuka_code);
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

    public String getShuka_code() {
        return shuka_code;
    }
}

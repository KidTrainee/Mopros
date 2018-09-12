package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseApi implements Parcelable {
    protected String id, haiso_date;

    public BaseApi(String id, String haiso_date) {
        this.id = id;
        this.haiso_date = haiso_date;
    }

    protected BaseApi(Parcel in) {
        id = in.readString();
        haiso_date = in.readString();
    }

    public static final Creator<BaseApi> CREATOR = new Creator<BaseApi>() {
        @Override
        public BaseApi createFromParcel(Parcel in) {
            return new BaseApi(in);
        }

        @Override
        public BaseApi[] newArray(int size) {
            return new BaseApi[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHaiso_date() {
        return haiso_date;
    }

    public void setHaiso_date(String haiso_date) {
        this.haiso_date = haiso_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(haiso_date);
    }

    @Override
    public String toString() {
        return "BaseApi{" +
                "id='" + id + '\'' +
                ", haiso_date='" + haiso_date + '\'' +
                '}';
    }
}

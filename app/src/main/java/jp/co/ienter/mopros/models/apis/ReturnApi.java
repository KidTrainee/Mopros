package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;

public class ReturnApi extends BaseApi {

    private int is_arrival; // 1: 出発、2: 到着 || 1: departure, 2: arrival

    public ReturnApi(String id, String haiso_date, int is_arrival) {
        super(id, haiso_date);
        this.is_arrival = is_arrival;
    }

    public ReturnApi(Parcel in) {
        super(in);
        is_arrival = in.readInt();
    }

    public static final Creator<ReturnApi> CREATOR = new Creator<ReturnApi>() {
        @Override
        public ReturnApi createFromParcel(Parcel in) {
            return new ReturnApi(in);
        }

        @Override
        public ReturnApi[] newArray(int size) {
            return new ReturnApi[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(is_arrival);
    }

    @Override
    public String toString() {
        return "ReturnApi{" +
                "is_arrival=" + is_arrival +
                ", id='" + id + '\'' +
                ", haiso_date='" + haiso_date + '\'' +
                '}';
    }
}

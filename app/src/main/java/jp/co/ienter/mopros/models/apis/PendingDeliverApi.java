package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;

import java.util.Arrays;

public class PendingDeliverApi extends DeliverArrApi {

    private int time_type;

    public PendingDeliverApi(String id, String haiso_date, String data_type, int time_type, DeliverApi[] transport_data) {
        super(id, haiso_date, data_type, transport_data);
        this.time_type = time_type;
    }

    public PendingDeliverApi(DeliverArrApi deliverArrApi, int time_type) {
        this(deliverArrApi.getId(), deliverArrApi.getHaiso_date(), deliverArrApi.getData_type(),
                time_type, deliverArrApi.getTransport_data());
    }

    protected PendingDeliverApi(Parcel in) {
        super(in);
        time_type = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(time_type);
    }

    @Override
    public String toString() {
        return "PendingDeliverApi{" +
                ", id='" + id + '\'' +
                ", haiso_date='" + haiso_date + '\'' +
                ", data_type='" + data_type + '\''  +
                ", time_type=" + time_type +
                ", transport_data=" + Arrays.toString(transport_data) +
                '}';
    }
}

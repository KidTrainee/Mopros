package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;

import java.util.Arrays;

public class RegWorkingDataApi extends BaseApi {

    protected RegWorkingDataItem[] transport_data;
    protected String data_type;

    public RegWorkingDataApi(String id, String haiso_date, String data_type, RegWorkingDataItem[] transport_data) {
        super(id, haiso_date);
        this.data_type = data_type;
        this.transport_data = transport_data;
    }

    protected RegWorkingDataApi(Parcel in) {
        super(in);
        transport_data = in.createTypedArray(RegWorkingDataItem.CREATOR);
        data_type = in.readString();
    }

    public RegWorkingDataItem[] getTransport_data() {
        return transport_data;
    }

    public String getData_type() {
        return data_type;
    }

    @Override
    public String toString() {
        return "DeliverArrApi{" +
                "transport_data=" + Arrays.toString(transport_data) +
                ", data_type=" + data_type +
                ", id='" + id + '\'' +
                ", haiso_date='" + haiso_date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedArray(transport_data, i);
        parcel.writeString(data_type);
    }
}

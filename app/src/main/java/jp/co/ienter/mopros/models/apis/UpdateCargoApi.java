package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;

/**
 * Created by thanhnv on 8/29/18.
 */

public class UpdateCargoApi extends BaseApi {

    private CargoApi[] selected_data;

    public UpdateCargoApi(String id, String haiso_date, CargoApi[] selected_data) {
        super(id, haiso_date);
        this.selected_data = selected_data;
    }

    protected UpdateCargoApi(Parcel in) {
        super(in);
        selected_data = in.createTypedArray(CargoApi.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedArray(selected_data, i);
    }
}

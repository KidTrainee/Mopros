package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thanhnv on 8/29/18.
 */

public class CargoApi implements Parcelable {
    String shuka_code;

    public CargoApi(String shuka_code) {
        this.shuka_code = shuka_code;
    }

    protected CargoApi(Parcel in) {
        shuka_code = in.readString();
    }

    public static final Creator<CargoApi> CREATOR = new Creator<CargoApi>() {
        @Override
        public CargoApi createFromParcel(Parcel in) {
            return new CargoApi(in);
        }

        @Override
        public CargoApi[] newArray(int size) {
            return new CargoApi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shuka_code);
    }
}

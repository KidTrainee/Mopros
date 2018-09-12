package jp.co.ienter.mopros.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thanhnv on 8/29/18.
 */

public class MoprosCargo implements Parcelable{
    private String shuka_code, shuka_name, syaryo_code;

    protected MoprosCargo(Parcel in) {
        shuka_code = in.readString();
        shuka_name = in.readString();
        syaryo_code = in.readString();
    }

    public static final Creator<MoprosCargo> CREATOR = new Creator<MoprosCargo>() {
        @Override
        public MoprosCargo createFromParcel(Parcel in) {
            return new MoprosCargo(in);
        }

        @Override
        public MoprosCargo[] newArray(int size) {
            return new MoprosCargo[size];
        }
    };

    public String getShuka_name() {
        return shuka_name;
    }

    public String getSyaryo_code() {
        return syaryo_code;
    }

    public MoprosCargo(String shuka_code, String shuka_name, String syaryo_code) {
        this.shuka_code = shuka_code;
        this.shuka_name = shuka_name;
        this.syaryo_code = syaryo_code;
    }

    public String getShuka_code() {
        return shuka_code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shuka_code);
        parcel.writeString(shuka_name);
        parcel.writeString(syaryo_code);
    }
}

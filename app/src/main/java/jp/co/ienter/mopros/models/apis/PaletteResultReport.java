package jp.co.ienter.mopros.models.apis;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thanhnv on 9/12/18.
 */

public class PaletteResultReport implements Parcelable{
    private  String palette_no,jitsu_kago_cnt,kaishu_kago_cnt,item_code,shuka_cnt;

    public PaletteResultReport(String palette_no, String jitsu_kago_cnt, String kaishu_kago_cnt, String item_code, String shuka_cnt) {
        this.palette_no = palette_no;
        this.jitsu_kago_cnt = jitsu_kago_cnt;
        this.kaishu_kago_cnt = kaishu_kago_cnt;
        this.item_code = item_code;
        this.shuka_cnt = shuka_cnt;
    }

    public PaletteResultReport(Parcel in) {
        palette_no = in.readString();
        jitsu_kago_cnt = in.readString();
        kaishu_kago_cnt = in.readString();
        item_code = in.readString();
        shuka_cnt = in.readString();
    }

    public static final Creator<PaletteResultReport> CREATOR = new Creator<PaletteResultReport>() {
        @Override
        public PaletteResultReport createFromParcel(Parcel in) {
            return new PaletteResultReport(in);
        }

        @Override
        public PaletteResultReport[] newArray(int size) {
            return new PaletteResultReport[size];
        }
    };


    public String getPalette_no() {
        return palette_no;
    }

    public void setPalette_no(String palette_no) {
        this.palette_no = palette_no;
    }

    public String getJitsu_kago_cnt() {
        return jitsu_kago_cnt;
    }

    public void setJitsu_kago_cnt(String jitsu_kago_cnt) {
        this.jitsu_kago_cnt = jitsu_kago_cnt;
    }

    public String getKaishu_kago_cnt() {
        return kaishu_kago_cnt;
    }

    public void setKaishu_kago_cnt(String kaishu_kago_cnt) {
        this.kaishu_kago_cnt = kaishu_kago_cnt;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getShuka_cnt() {
        return shuka_cnt;
    }

    public void setShuka_cnt(String shuka_cnt) {
        this.shuka_cnt = shuka_cnt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(palette_no);
        parcel.writeString(jitsu_kago_cnt);
        parcel.writeString(kaishu_kago_cnt);
        parcel.writeString(item_code);
        parcel.writeString(shuka_cnt);
    }
}

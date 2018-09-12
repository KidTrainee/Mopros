package jp.co.ienter.mopros.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import jp.co.ienter.mopros.utils.Const;

/**
 * Created by thanhnv on 8/3/18.
 */

public class MoprosDelivery implements Parcelable {

    private static final String TAG = MoprosDelivery.class.getSimpleName();
    //PK
    protected String order_no_old;
    protected String haiso_order_no;
    @SerializedName("course_cd1_after")
    protected String course_code_1;
    @SerializedName ("course_cd2_after")
    protected String course_code_2;
    protected String trip;
    //normal
    protected String order_no;
    protected String nonyu_code;
    protected String shuka_code;
    protected  String sharyo_code;
    protected String nonyu_name;
    protected String shitei_time;
    protected String case_cnt;
    protected String weight_g;
    protected String weight_kg;
    protected String kanryo_flg;
    protected String nonyu_address;
    protected String horyu_flg, horyu_time;
    protected String gosha;
    protected String data_type = Const.FLAG_DESTINATION_TYPE_DELIVER;// destination types: either "Delivery[配送]" or "Pickup[集荷]"

    public MoprosDelivery() {
    }

//    public MoprosDelivery(String order_no, String nonyu_code, String nonyu_name,
//                          String shitei_time, String case_cnt, String weight_g, String weight_kg,
//                          String nonyu_address, String kanryo_flg, String data_type) {
//        this.order_no = order_no;
//        this.nonyu_code = nonyu_code;
//        this.nonyu_name = nonyu_name;
//        this.shitei_time = shitei_time;
//        this.case_cnt = case_cnt;
//        this.weight_g = weight_g;
//        this.weight_kg = weight_kg;
//        this.kanryo_flg = kanryo_flg;
//        this.nonyu_address = nonyu_address;
//        this.data_type = data_type;
//    }

//    public MoprosDelivery(String order_no, String order_no_old, String course_code_1, String course_code_2,
//                          String haiso_order_no, String trip, String data_type, String nonyu_code, String shuka_code,
//                          String nonyu_name, String nonyu_address, String shitei_time,
//                          String case_cnt, String kanryo_flg) {
//        this(order_no, nonyu_code, nonyu_name, shitei_time, case_cnt, "",
//                "", nonyu_address, kanryo_flg, data_type);
//        this.order_no_old = order_no_old;
//        this.haiso_order_no = haiso_order_no;
//        this.course_code_1 = course_code_1;
//        this.course_code_2 = course_code_2;
//        this.shuka_code = shuka_code;
//        this.trip = trip;
//    }


    public MoprosDelivery(String order_no_old, String haiso_order_no, String course_code_1,
                          String course_code_2, String trip, String order_no, String nonyu_code,
                          String shuka_code, String nonyu_name, String shitei_time, String case_cnt,
                          String weight_g, String weight_kg, String kanryo_flg, String nonyu_address,
                          String horyu_flg, String horyu_time, String data_type, String gosha) {
        this.order_no_old = order_no_old;
        this.haiso_order_no = haiso_order_no;
        this.course_code_1 = course_code_1;
        this.course_code_2 = course_code_2;
        this.trip = trip;
        this.order_no = order_no;
        this.nonyu_code = nonyu_code;
        this.shuka_code = shuka_code;
        this.nonyu_name = nonyu_name;
        this.shitei_time = shitei_time;
        this.case_cnt = case_cnt;
        this.weight_g = weight_g;
        this.weight_kg = weight_kg;
        this.kanryo_flg = kanryo_flg;
        this.nonyu_address = nonyu_address;
        this.horyu_flg = horyu_flg;
        this.horyu_time = horyu_time;
        this.data_type = data_type;
        this.gosha = gosha;
    }

    protected MoprosDelivery(Parcel in) {
        order_no_old = in.readString();
        haiso_order_no = in.readString();
        course_code_1 = in.readString();
        course_code_2 = in.readString();
        trip = in.readString();
        order_no = in.readString();
        nonyu_code = in.readString();
        nonyu_name = in.readString();
        shitei_time = in.readString();
        case_cnt = in.readString();
        weight_g = in.readString();
        weight_kg = in.readString();
        kanryo_flg = in.readString();
        nonyu_address = in.readString();
        data_type = in.readString();
        shuka_code = in.readString();
        horyu_flg = in.readString();
        horyu_time = in.readString();
        sharyo_code=in.readString();
    }

    public static final Creator<MoprosDelivery> CREATOR = new Creator<MoprosDelivery>() {
        @Override
        public MoprosDelivery createFromParcel(Parcel in) {
            return new MoprosDelivery(in);
        }

        @Override
        public MoprosDelivery[] newArray(int size) {
            return new MoprosDelivery[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(order_no_old);
        parcel.writeString(haiso_order_no);
        parcel.writeString(course_code_1);
        parcel.writeString(course_code_2);
        parcel.writeString(trip);
        parcel.writeString(order_no);
        parcel.writeString(nonyu_code);
        parcel.writeString(nonyu_name);
        parcel.writeString(shitei_time);
        parcel.writeString(case_cnt);
        parcel.writeString(weight_g);
        parcel.writeString(weight_kg);
        parcel.writeString(kanryo_flg);
        parcel.writeString(nonyu_address);
        parcel.writeString(data_type);
        parcel.writeString(shuka_code);
        parcel.writeString(horyu_flg);
        parcel.writeString(horyu_time);
        parcel.writeString(sharyo_code);
    }

    public String getOrder_no() {
        return order_no;
    }

    public String getNonyu_code() {
        return nonyu_code;
    }

    public String getNonyu_name() {
        return nonyu_name;
    }

    public String getShitei_time() {
        return shitei_time;
    }

    public String getCase_cnt() {
        return case_cnt;
    }

    public String getWeight_g() {
        return weight_g;
    }

    public String getWeight_kg() {
        return weight_kg;
    }

    public String getKanryo_flg() {
        return kanryo_flg;
    }

    public String getData_type() {
        return data_type;
    }

    public String getNonyu_address() {
        return nonyu_address;
    }

    public String getOrder_no_old() {
        return order_no_old;
    }

    public String getHaiso_order_no() {
        return haiso_order_no;
    }

    public String getCourse_code_1() {
        return course_code_1;
    }

    public String getCourse_code_2() {
        return course_code_2;
    }

    public String getTrip() {
        return trip;
    }

    public String getShuka_code() {
        return shuka_code;
    }

    public void setKanryo_flg(String kanryo_flg) {
        this.kanryo_flg = kanryo_flg;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public void setNonyu_code(String nonyu_code) {
        this.nonyu_code = nonyu_code;
    }

    public void setShuka_code(String shuka_code) {
        this.shuka_code = shuka_code;
    }

    public void setNonyu_name(String nonyu_name) {
        this.nonyu_name = nonyu_name;
    }
    public void setShitei_time(String shitei_time){
        this.shitei_time = shitei_time;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getHoryu_flg() {
        return horyu_flg;
    }

    public String getHoryu_time() {
        return horyu_time;
    }

    public String getSharyo_code() {
        return sharyo_code;
    }

    public void setSharyo_code(String sharyo_code) {
        this.sharyo_code = sharyo_code;
    }

    public String getGosha() {
        return gosha;
    }

    @Override
    public String toString() {
        return "MoprosDelivery{" +
                "order_no_old='" + order_no_old + '\'' +
                ", haiso_order_no='" + haiso_order_no + '\'' +
                ", course_code_1='" + course_code_1 + '\'' +
                ", course_code_2='" + course_code_2 + '\'' +
                ", trip='" + trip + '\'' +
                ", order_no='" + order_no + '\'' +
                ", nonyu_code='" + nonyu_code + '\'' +
                ", shuka_code='" + shuka_code + '\'' +
                ", nonyu_name='" + nonyu_name + '\'' +
                ", shitei_time='" + shitei_time + '\'' +
                ", case_cnt='" + case_cnt + '\'' +
                ", weight_g='" + weight_g + '\'' +
                ", weight_kg='" + weight_kg + '\'' +
                ", kanryo_flg='" + kanryo_flg + '\'' +
                ", nonyu_address='" + nonyu_address + '\'' +
                ", data_type='" + data_type + '\'' +
                ", horyu_flg='" + horyu_flg + '\'' +
                ", horyu_time='" + horyu_time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        try{
            MoprosDelivery moprosDelivery = (MoprosDelivery) obj;
            return this.course_code_1.equals(moprosDelivery.getCourse_code_1()) &&
                    this.course_code_2.equals(moprosDelivery.getCourse_code_2()) &&
                    this.haiso_order_no.equals(moprosDelivery.getHaiso_order_no()) &&
                    this.trip.equals(moprosDelivery.getTrip()) &&
                    this.nonyu_code.equals(moprosDelivery.getNonyu_code());
        } catch (Exception e){
            return  false;
        }
    }

    public void incrementGosha() {
        try {
            gosha = String.valueOf(Integer.parseInt(gosha) + 1);
        } catch (NumberFormatException e) {
            Log.e(TAG, "incrementGosha: ", e);
        }
    }
}

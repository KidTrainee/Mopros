package jp.co.ienter.mopros.models;

import android.os.Parcel;

import jp.co.ienter.mopros.models.base.ISelectable;
import jp.co.ienter.mopros.utils.AppConstants;
import jp.co.ienter.mopros.utils.Const;

public class SelectableDelivery extends MoprosDelivery implements ISelectable {
    private boolean isSelected;

    public SelectableDelivery(String order_no_old, String haiso_order_no, String course_code_1,
                              String course_code_2, String trip, String order_no, String nonyu_code,
                              String shuka_code, String nonyu_name, String shitei_time, String case_cnt,
                              String weight_g, String weight_kg, String kanryo_flg,
                              String nonyu_address, String horyu_flg, String horyu_time,
                              String data_type, String gosha,
                              boolean isSelected) {
        super(order_no_old, haiso_order_no, course_code_1, course_code_2,
                trip, order_no, nonyu_code, shuka_code, nonyu_name, shitei_time, case_cnt,
                weight_g, weight_kg, kanryo_flg, nonyu_address, horyu_flg, horyu_time, data_type, gosha);
        this.isSelected = isSelected;
    }

    public SelectableDelivery(MoprosDelivery delivery, boolean isSelected) {
        this(delivery.getOrder_no_old(), delivery.getHaiso_order_no(),
                delivery.getCourse_code_1(), delivery.getCourse_code_2(), delivery.getTrip(),
                delivery.getOrder_no(), delivery.getNonyu_code(), delivery.getShuka_code(),
                delivery.getNonyu_name(), delivery.getShitei_time(), delivery.getCase_cnt(),
                delivery.getWeight_g(), delivery.getWeight_kg(), delivery.getKanryo_flg(),
                delivery.getNonyu_address(), delivery.getHoryu_flg(), delivery.getHoryu_time(),
                delivery.getData_type(),
                delivery.getGosha(),isSelected);
    }

    protected SelectableDelivery(Parcel in) {
        super(in);
        isSelected = in.readByte() != 0;
    }

    public static final Creator<SelectableDelivery> CREATOR = new Creator<SelectableDelivery>() {
        @Override
        public SelectableDelivery createFromParcel(Parcel in) {
            return new SelectableDelivery(in);
        }

        @Override
        public SelectableDelivery[] newArray(int size) {
            return new SelectableDelivery[size];
        }
    };

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        order_no, nonyu_code, nonyu_name, shitei_time, case_cnt, weight_g, weight_kg, kanryo_flg
        super.writeToParcel(parcel, i);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public String toString() {
        return "SelectableDelivery{" +
                "isSelected=" + isSelected +
                ", order_no_old='" + order_no_old + '\'' +
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
                ", horyu_flg='" + horyu_flg + '\'' +
                ", horyu_time='" + horyu_time + '\'' +
                ", data_type='" + data_type + '\'' +
                ", data_type='" + gosha + '\'' +
                '}';
    }

    public boolean isDone() {
        return kanryo_flg.equals(Const.KANRYO_FLAG_DONE);
    }

    public boolean isNotDeliver() {
        return data_type.equals(Const.FLAG_DESTINATION_TYPE_DELIVER)
                && kanryo_flg.equals(Const.KANRYO_FLAG_NOT_DELIVER);
    }

    public boolean isPending() {
        return !isDone()
                && !horyu_flg.equals(Const.HONRYU_FLAG_NO_PENDING);
    }

    public boolean isDelivery() {
        return !data_type.equals(Const.FLAG_DESTINATION_TYPE_DELIVER);
    }
}

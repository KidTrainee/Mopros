package jp.co.ienter.mopros.models;

/**
 * Created by donghv on 9/10/18.
 */

public class ReportedData {
    private String sagyo_code;
    private String sagyo_time;
    private String item_name;
    private String item_value;

    public ReportedData(String sagyo_code, String sagyo_time, String item_name, String item_value) {
        this.sagyo_code = sagyo_code;
        this.sagyo_time = sagyo_time;
        this.item_name = item_name;
        this.item_value = item_value;
    }

    public String getSagyo_code() {
        return sagyo_code;
    }

    public void setSagyo_code(String sagyo_code) {
        this.sagyo_code = sagyo_code;
    }

    public String getSagyo_time() {
        return sagyo_time;
    }

    public void setSagyo_time(String sagyo_time) {
        this.sagyo_time = sagyo_time;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
    }
}


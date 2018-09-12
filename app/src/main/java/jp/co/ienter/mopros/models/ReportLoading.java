package jp.co.ienter.mopros.models;

/**
 * Created by donghv on 8/3/18.
 */

public class ReportLoading {
    private String sagyo_time;
    private String item_name;
    private String item_value;

    public ReportLoading(String sagyo_time, String item_name, String item_value) {
        this.sagyo_time = sagyo_time;
        this.item_name = item_name;
        this.item_value = item_value;
    }

    public ReportLoading() {
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

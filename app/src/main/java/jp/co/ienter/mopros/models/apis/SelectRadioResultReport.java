package jp.co.ienter.mopros.models.apis;

/**
 * Created by thanhnv on 9/12/18.
 */

public class SelectRadioResultReport {
    private  String sagyo_code,change_kbn;

    public SelectRadioResultReport(String sagyo_code, String change_kbn) {
        this.sagyo_code = sagyo_code;
        this.change_kbn = change_kbn;
    }

    public String getSagyo_code() {
        return sagyo_code;
    }

    public void setSagyo_code(String sagyo_code) {
        this.sagyo_code = sagyo_code;
    }

    public String getChange_kbn() {
        return change_kbn;
    }

    public void setChange_kbn(String change_kbn) {
        this.change_kbn = change_kbn;
    }
}

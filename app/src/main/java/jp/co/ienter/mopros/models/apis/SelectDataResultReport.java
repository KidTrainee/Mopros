package jp.co.ienter.mopros.models.apis;

/**
 * Created by thanhnv on 9/12/18.
 */

public class SelectDataResultReport {
    private String sagyo_code,sagyo_time;

    public SelectDataResultReport(String sagyo_code, String sagyo_time) {
        this.sagyo_code = sagyo_code;
        this.sagyo_time = sagyo_time;
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
}

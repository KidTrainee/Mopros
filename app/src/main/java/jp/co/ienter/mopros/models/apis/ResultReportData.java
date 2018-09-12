package jp.co.ienter.mopros.models.apis;

/**
 * Created by thanhnv on 9/11/18.
 */

public class ResultReportData {
    private  String nohin_time;
    protected SelectDataResultReport[] select_data;
    protected SelectRadioResultReport[] radio_data;
    protected PaletteResultReport[] palette_data;

    public String getNohin_time() {
        return nohin_time;
    }

    public void setNohin_time(String nohin_time) {
        this.nohin_time = nohin_time;
    }

    public SelectDataResultReport[] getSelect_data() {
        return select_data;
    }

    public void setSelect_data(SelectDataResultReport[] select_data) {
        this.select_data = select_data;
    }

    public SelectRadioResultReport[] getRadio_data() {
        return radio_data;
    }

    public void setRadio_data(SelectRadioResultReport[] radio_data) {
        this.radio_data = radio_data;
    }

    public PaletteResultReport[] getPalette_data() {
        return palette_data;
    }

    public void setPalette_data(PaletteResultReport[] palette_data) {
        this.palette_data = palette_data;
    }
}

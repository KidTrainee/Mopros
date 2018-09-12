
package jp.co.ienter.mopros.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaletteReportDataModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("palette_data")
    @Expose
    private List<Palette_datum> palette_data = null;
    @SerializedName("shuka_data")
    @Expose
    private List<Shuka_datum> shuka_data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<Palette_datum> getPalette_data() {
        return palette_data;
    }

    public void setPalette_data(List<Palette_datum> palette_data) {
        this.palette_data = palette_data;
    }

    public List<Shuka_datum> getShuka_data() {
        return shuka_data;
    }

    public void setShuka_data(List<Shuka_datum> shuka_data) {
        this.shuka_data = shuka_data;
    }

    public class Palette_datum {

        @SerializedName("palette_no")
        @Expose
        private Integer palette_no;
        @SerializedName("palette_name")
        @Expose
        private String palette_name;

        public Integer getPalette_no() {
            return palette_no;
        }

        public void setPalette_no(Integer palette_no) {
            this.palette_no = palette_no;
        }

        public String getPalette_name() {
            return palette_name;
        }

        public void setPalette_name(String palette_name) {
            this.palette_name = palette_name;
        }

    }

    public class Shuka_datum {

        @SerializedName("item_code")
        @Expose
        private String item_code;
        @SerializedName("item_name")
        @Expose
        private String item_name;

        public String getItem_code() {
            return item_code;
        }

        public void setItem_code(String item_code) {
            this.item_code = item_code;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

    }

}
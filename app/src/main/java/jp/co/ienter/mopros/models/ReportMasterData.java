package jp.co.ienter.mopros.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * this object for API 042
 */
public class ReportMasterData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("select_item")
    @Expose
    private List<Select_item> select_item = null;
    @SerializedName("radio_item")
    @Expose
    private List<Radio_item> radio_item = null;

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

    public List<Select_item> getSelect_item() {
        return select_item;
    }

    public void setSelect_item(List<Select_item> select_item) {
        this.select_item = select_item;
    }

    public List<Radio_item> getRadio_item() {
        return radio_item;
    }

    public void setRadio_item(List<Radio_item> radio_item) {
        this.radio_item = radio_item;
    }

    public class Radio_item {

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

    public class Select_item {

        @SerializedName("sagyo_code")
        @Expose
        private String sagyo_code;
        @SerializedName("item_name")
        @Expose
        private String item_name;
        @SerializedName("sub_item_list")
        @Expose
        private List<Sub_item_list> sub_item_list = null;

        public String getSagyo_code() {
            return sagyo_code;
        }

        public void setSagyo_code(String sagyo_code) {
            this.sagyo_code = sagyo_code;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public List<Sub_item_list> getSub_item_list() {
            return sub_item_list;
        }

        public void setSub_item_list(List<Sub_item_list> sub_item_list) {
            this.sub_item_list = sub_item_list;
        }

    }

    public static class Sub_item_list {

        @SerializedName("sub_item_code")
        @Expose
        private String sub_item_code;
        @SerializedName("sub_item_name")
        @Expose
        private String sub_item_name;
        @SerializedName("sub_item_value")
        @Expose
        private String sub_item_value;

        public Sub_item_list(String sub_item_code, String sub_item_name, String sub_item_value) {
            this.sub_item_code = sub_item_code;
            this.sub_item_name = sub_item_name;
            this.sub_item_value = sub_item_value;
        }

        public String getSub_item_code() {
            return sub_item_code;
        }

        public void setSub_item_code(String sub_item_code) {
            this.sub_item_code = sub_item_code;
        }

        public String getSub_item_name() {
            return sub_item_name;
        }

        public void setSub_item_name(String sub_item_name) {
            this.sub_item_name = sub_item_name;
        }

        public String getSub_item_value() {
            return sub_item_value;
        }

        public void setSub_item_value(String sub_item_value) {
            this.sub_item_value = sub_item_value;
        }

    }
}
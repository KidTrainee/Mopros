
package jp.co.ienter.mopros.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model for API_LOADING_STATUS
 */
public class LoadingStatusModel {

    /**
     * status for load
     */
    public static class LOAD{
        public static final int BEFORE_START_LOAD = 0;
        public static final int LOADING = 1;
        public static final int FINISH_LOAD = 2;
    };

    /**
     * status for load report
     */
    public static class REPORT{
        public static final int YET_REPORT = 0;
        public static final int NO_REPORT = 1;
        public static final int REPORTED = 2;
    }


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("result")
    @Expose
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {

        @SerializedName("in_loading")
        @Expose
        private Integer in_loading;
        @SerializedName("in_report")
        @Expose
        private Integer in_report;

        public Integer getIn_loading() {
            return in_loading;
        }

        public void setIn_loading(Integer in_loading) {
            this.in_loading = in_loading;
        }

        public Integer getIn_report() {
            return in_report;
        }

        public void setIn_report(Integer in_report) {
            this.in_report = in_report;
        }

    }

}
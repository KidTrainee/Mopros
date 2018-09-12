package jp.co.ienter.mopros.activity.deliver_chart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliverChartModel implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    protected DeliverChartModel(Parcel in) {
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        messages = in.readString();
    }

    public static final Creator<DeliverChartModel> CREATOR = new Creator<DeliverChartModel>() {
        @Override
        public DeliverChartModel createFromParcel(Parcel in) {
            return new DeliverChartModel(in);
        }

        @Override
        public DeliverChartModel[] newArray(int size) {
            return new DeliverChartModel[size];
        }
    };

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        dest.writeString(messages);
    }
}
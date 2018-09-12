package jp.co.ienter.mopros.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class WorkingData implements Parcelable {

    private Integer status;
    private String messages;
    private List<MoprosDelivery> result = null;

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

    public List<MoprosDelivery> getResult() {
        return result;
    }

    public void setResult(List<MoprosDelivery> result) {
        this.result = result;
    }

    protected WorkingData(Parcel in) {
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        messages = in.readString();
    }

    public static final Creator<WorkingData> CREATOR = new Creator<WorkingData>() {
        @Override
        public WorkingData createFromParcel(Parcel in) {
            return new WorkingData(in);
        }

        @Override
        public WorkingData[] newArray(int size) {
            return new WorkingData[size];
        }
    };

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
        dest.writeTypedList(result);
    }
}

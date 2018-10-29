package lifestyle.com.lifestyle.base.api;


import com.google.gson.annotations.SerializedName;

import lifestyle.com.lifestyle.helper.Utils;

public class BaseResponse<T> {

    @SerializedName("status")
    private boolean status;
    @SerializedName("message_en")
    private String messageEn;
    @SerializedName("message_ar")
    private String messageAr;
    @SerializedName("data")
    private T data;

    public String getMessageEn() {
        return Utils.checkString(messageEn);
    }

    public String getMessageAr() {
        return Utils.checkString(messageAr);
    }

    public boolean getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}

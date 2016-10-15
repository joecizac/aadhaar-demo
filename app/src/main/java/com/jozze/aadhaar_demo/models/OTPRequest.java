package com.jozze.aadhaar_demo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jozze on 15/10/16.
 */

public class OTPRequest {

    @SerializedName("aadhaar-id")
    private String aadhaarID;
    @SerializedName("device-id")
    private String deviceID;
    @SerializedName("certificate-type")
    private String certType;
    @SerializedName("channel")
    private String channel;

    public void setAadhaarID(String aadhaarID) {
        this.aadhaarID = aadhaarID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}

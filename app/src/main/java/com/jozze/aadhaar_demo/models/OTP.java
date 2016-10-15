package com.jozze.aadhaar_demo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jozze on 15/10/16.
 */

public class OTP {

    @SerializedName("success")
    private boolean success;
    @SerializedName("aadhaar-reference-code")
    private String refCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

}

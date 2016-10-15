package com.jozze.aadhaar_demo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jozze on 15/10/16.
 */

public class KYCRequest {

    @SerializedName("consent")
    private String consent;
    @SerializedName("auth-capture-request")
    private Request request;

    public void setConsent(String consent) {
        this.consent = consent;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public static class Request {
        @SerializedName("aadhaar-id")
        private String aadhaarID;
        @SerializedName("modality")
        private String modality;
        @SerializedName("certificate-type")
        private String certType;
        @SerializedName("otp")
        private String otp;
        @SerializedName("location")
        private Location location;

        public void setAadhaarID(String aadhaarID) {
            this.aadhaarID = aadhaarID;
        }

        public void setModality(String modality) {
            this.modality = modality;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public static class Location {
            @SerializedName("type")
            private String type;
            @SerializedName("pincode")
            private String pincode;

            public void setType(String type) {
                this.type = type;
            }

            public void setPincode(String pincode) {
                this.pincode = pincode;
            }
        }
    }

}

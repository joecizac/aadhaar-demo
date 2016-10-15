package com.jozze.aadhaar_demo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jozze on 15/10/16.
 */

public class KYC {

    @SerializedName("success")
    private boolean success;
    @SerializedName("aadhaar-id")
    private String aadhaarID;
    @SerializedName("aadhaar-reference-code")
    private String refCode;
    @SerializedName("kyc")
    private EKYC kyc;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAadhaarID() {
        return aadhaarID;
    }

    public void setAadhaarID(String aadhaarID) {
        this.aadhaarID = aadhaarID;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public EKYC getKyc() {
        return kyc;
    }

    public void setKyc(EKYC kyc) {
        this.kyc = kyc;
    }

    public static class EKYC {
        @SerializedName("photo")
        private String imageByteArray;
        @SerializedName("poi")
        private Personal personal;
        @SerializedName("poa")
        private Address address;

        public String getImageByteArray() {
            return imageByteArray;
        }

        public void setImageByteArray(String imageByteArray) {
            this.imageByteArray = imageByteArray;
        }

        public Personal getPersonal() {
            return personal;
        }

        public void setPersonal(Personal personal) {
            this.personal = personal;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public static class Personal {
            @SerializedName("name")
            private String name;
            @SerializedName("dob")
            private String birthDate;
            @SerializedName("gender")
            private String gender;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(String birthDate) {
                this.birthDate = birthDate;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }

        public static class Address {
            @SerializedName("co")
            private String careOf;
            @SerializedName("street")
            private String street;
            @SerializedName("house")
            private String house;
            @SerializedName("vtc")
            private String area;
            @SerializedName("subdist")
            private String subDistrict;
            @SerializedName("dist")
            private String district;
            @SerializedName("state")
            private String state;
            @SerializedName("pc")
            private String pincode;
            @SerializedName("po")
            private String postOffice;

            public String getCareOf() {
                return careOf;
            }

            public void setCareOf(String careOf) {
                this.careOf = careOf;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getHouse() {
                return house;
            }

            public void setHouse(String house) {
                this.house = house;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getSubDistrict() {
                return subDistrict;
            }

            public void setSubDistrict(String subDistrict) {
                this.subDistrict = subDistrict;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPincode() {
                return pincode;
            }

            public void setPincode(String pincode) {
                this.pincode = pincode;
            }

            public String getPostOffice() {
                return postOffice;
            }

            public void setPostOffice(String postOffice) {
                this.postOffice = postOffice;
            }
        }
    }

}

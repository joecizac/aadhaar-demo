package com.jozze.aadhaar_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jozze.aadhaar_demo.models.KYC;
import com.jozze.aadhaar_demo.models.KYCRequest;
import com.jozze.aadhaar_demo.models.OTP;
import com.jozze.aadhaar_demo.models.OTPRequest;
import com.jozze.aadhaar_demo.utils.LogUtil;
import com.jozze.aadhaar_demo.utils.SnackBarUtil;
import com.jozze.aadhaar_demo.utils.network.NetworkRequestHelper;
import com.jozze.aadhaar_demo.utils.network.NetworkResponseCallback;
import com.jozze.aadhaar_demo.utils.parser.ClassTag;

public class AadhaarActivity extends AppCompatActivity implements NetworkResponseCallback {

    private EditText aadharEditText;
    private EditText otpEditText;
    private boolean isOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar);
        Button submit = (Button) findViewById(R.id.submit);
        otpEditText = (EditText) findViewById(R.id.otpEditText);
        aadharEditText = (EditText) findViewById(R.id.aadharEditText);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aadharEditText.getText().toString().length() > 0) {

                    if (!isOTP)
                        requestOTP();
                    else
                        requestUserKYC();
                }
            }
        });
    }

    private void requestOTP() {
        OTPRequest request = new OTPRequest();
        request.setAadhaarID(aadharEditText.getText().toString());
//        request.setAadhaarID("993053006811");
        request.setDeviceID("");
        request.setCertType("preprod");
        request.setChannel("SMS");

        NetworkRequestHelper.getInstance(this).setCallback(this).makeRawPostRequest("http://139.59.30.133:9090/otp",
                new Gson().toJson(request), ClassTag.TAG_OTP);
    }

    private void requestUserKYC() {
        KYCRequest request = new KYCRequest();
        request.setConsent("Y");

        KYCRequest.Request kycRequest = new KYCRequest.Request();
        kycRequest.setAadhaarID(aadharEditText.getText().toString());
//        kycRequest.setAadhaarID("993053006811");
        kycRequest.setModality("otp");
        kycRequest.setCertType("preprod");
        kycRequest.setOtp(otpEditText.getText().toString());
//        kycRequest.setOtp("350203");

        KYCRequest.Request.Location location = new KYCRequest.Request.Location();
        location.setType("pincode");
        location.setPincode("423703");

        kycRequest.setLocation(location);
        request.setRequest(kycRequest);

        NetworkRequestHelper.getInstance(this).setCallback(this).makeRawPostRequest("http://139.59.30.133:9090/kyc/raw",
                new Gson().toJson(request), ClassTag.TAG_KYC);
    }

    @Override
    public <T> void onResponseSuccess(T obj, int tag) {
        switch (tag) {
            case ClassTag.TAG_OTP:
                otpEditText.setVisibility(View.VISIBLE);
                OTP otp = (OTP) obj;
                LogUtil.debug("" + otp.getRefCode());
                LogUtil.debug("" + otp.isSuccess());
                isOTP = true;
                break;

            case ClassTag.TAG_KYC:
                KYC kyc = (KYC) obj;
                LogUtil.debug("" + kyc.getAadhaarID());
                Intent intent = new Intent(AadhaarActivity.this, HomeScreenActivity.class);
                intent.putExtra("Data", new Gson().toJson(kyc));
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onResponseError(String error) {
        SnackBarUtil.shortSnack(aadharEditText, "some error occurred");
    }
}

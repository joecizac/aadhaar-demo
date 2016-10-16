package com.jozze.aadhaar_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
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

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class AadhaarActivity extends AppCompatActivity implements NetworkResponseCallback, BarcodeRetriever {

    private EditText aadharEditText;
    private EditText otpEditText;
    private boolean isOTP;
    private boolean isScan = false;
    private BarcodeCapture barcodeCapture;
    private EditText pincodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aadhaar);

        final android.support.v4.app.Fragment scanfragment = getSupportFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture = (BarcodeCapture) scanfragment;
        barcodeCapture.setRetrieval(this);
//        barcodeCapture.setShowDrawRect(true);
//        barcodeCapture.setSupportMultipleScan(true);
//        barcodeCapture.setTouchAsCallback(true);
//        barcodeCapture.shouldAutoFocus(true);
//        barcodeCapture.setShouldShowText(true);
//        barcodeCapture.refresh();

        Button submit = (Button) findViewById(R.id.submit);
        otpEditText = (EditText) findViewById(R.id.otpEditText);
        pincodeEditText = (EditText) findViewById(R.id.pincodeEditText);
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

        findViewById(R.id.btn_qr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.scan_layout).setVisibility(View.VISIBLE);
                isScan = true;
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
        location.setPincode(pincodeEditText.getText().toString());

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
                pincodeEditText.setVisibility(View.VISIBLE);
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

    @Override
    public void onRetrieved(Barcode barcode) {
        String data = barcode.displayValue;

        LogUtil.debug("onRetrieved");
        LogUtil.debug("displayValue : " + barcode.displayValue);

        if(data.contains("PrintLetterBarcodeData uid")) {
            data = data.substring((data.indexOf("uid=") + 5),
                    (data.indexOf("uid=") + 17));
            final String finalData = data;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    aadharEditText.setText(finalData);
                    findViewById(R.id.scan_layout).setVisibility(View.GONE);
                    isScan = false;
                }
            });
        }
    }

    @Override
    public void onRetrievedMultiple(Barcode barcode, List<BarcodeGraphic> list) {
        LogUtil.debug("onRetrievedMultiple");
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        LogUtil.debug("onBitmapScanned");
    }

    @Override
    public void onRetrievedFailed(String s) {
        LogUtil.debug("onRetrievedFailed");
        SnackBarUtil.shortSnack(aadharEditText, "error reading QR");
    }

}

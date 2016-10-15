package com.jozze.aadhaar_demo.utils.parser;

import com.google.gson.Gson;
import com.jozze.aadhaar_demo.models.Auth;
import com.jozze.aadhaar_demo.models.KYC;
import com.jozze.aadhaar_demo.models.OTP;
import com.jozze.aadhaar_demo.utils.network.NetworkResponseCallback;

/**
 * Created by jozze on 4/19/16.
 */
public class ParseHelper {

    private static ParseHelper mHelper;
    private NetworkResponseCallback mCallback;
    private Gson mGson;

    private ParseHelper() {
        mGson = new Gson();
    }

    public static ParseHelper getInstance() {
        if (mHelper == null)
            mHelper = new ParseHelper();

        return mHelper;
    }

    public ParseHelper setCallback(NetworkResponseCallback callback) {
        mCallback = callback;
        return mHelper;
    }

    public void parse(String response, int tagID) {
        try {
            switch (tagID) {
                case ClassTag.TAG_OTP:
                    OTP otp = mGson.fromJson(response, OTP.class);
                    mCallback.onResponseSuccess(otp, tagID);
                    break;

                case ClassTag.TAG_AUTH:
                    Auth auth = mGson.fromJson(response, Auth.class);
                    mCallback.onResponseSuccess(auth, tagID);
                    break;

                case ClassTag.TAG_KYC:
                    KYC kyc = mGson.fromJson(response, KYC.class);
                    mCallback.onResponseSuccess(kyc, tagID);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(null != e.getMessage())
                mCallback.onResponseError(e.getMessage());
        }
    }

}

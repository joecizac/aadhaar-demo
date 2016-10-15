package com.jozze.aadhaar_demo.utils.network;

/**
 * Created by jozze on 8/7/15.
 */
public interface NetworkResponseCallback {

    <T> void onResponseSuccess(T obj, int tag);

    void onResponseError(String error);

}

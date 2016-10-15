package com.jozze.aadhaar_demo.utils;

import android.util.Log;

import com.jozze.aadhaar_demo.AppConstant;

public class LogUtil {

    public static void info(String msg) {
        Log.i(AppConstant.TAG_LOG, msg);
    }

    public static void debug(String msg) {
        Log.d(AppConstant.TAG_LOG, msg);
    }

    public static void warn(String msg) {
        Log.w(AppConstant.TAG_LOG, msg);
    }

    public static void error(String msg) {
        Log.e(AppConstant.TAG_LOG, msg);
    }

}
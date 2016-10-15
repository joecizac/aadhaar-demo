package com.jozze.aadhaar_demo.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by jozze on 9/18/16.
 */

public class SnackBarUtil {

    public static void shortSnack(View rootView, String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();

    }

    public static void longSnack(View rootView, String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

}

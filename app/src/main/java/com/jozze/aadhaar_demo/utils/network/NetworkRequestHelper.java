package com.jozze.aadhaar_demo.utils.network;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jozze.aadhaar_demo.AppConstant;
import com.jozze.aadhaar_demo.utils.LogUtil;
import com.jozze.aadhaar_demo.utils.NetworkUtil;
import com.jozze.aadhaar_demo.utils.SnackBarUtil;
import com.jozze.aadhaar_demo.utils.parser.ParseHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jozze on 8/7/15.
 */
public class NetworkRequestHelper {

    private static NetworkRequestHelper mRequest;
    private NetworkResponseCallback mCallback;
    private Context mContext;

    private final String TAG = NetworkRequestHelper.class.getSimpleName();

    private NetworkRequestHelper(Context context) {
        mContext = context;
    }

    public static NetworkRequestHelper getInstance(Context context) {
        if (mRequest == null)
            mRequest = new NetworkRequestHelper(context);

        return mRequest;
    }

    public NetworkRequestHelper setCallback(NetworkResponseCallback callback) {
        mCallback = callback;
        return mRequest;
    }

    public void getRequest(final String url, final HashMap<String, String> headers, final int classTag) {
        makeRequest(Request.Method.GET, url, headers, null, classTag);
    }

    public void postRequest(final String url, final HashMap<String, String> headers,
                           final HashMap<String, String> params, final int classTag) {
        makeRequest(Request.Method.POST, url, headers, params, classTag);
    }

    private void makeRequest(final int methodType, final String url,
                             final HashMap<String, String> headers, final HashMap<String,
                                String> params, final int classTag) {
        String formattedUrl = "";
        try {
            formattedUrl = url.replaceAll(" ", "%20");
//            formattedUrl = URLEncoder.encode(url, "utf-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if(NetworkUtil.isConnected(mContext)) {
            LogUtil.debug("SERVER URL :: " + formattedUrl);
            StringRequest request = new StringRequest(methodType, formattedUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LogUtil.debug("SERVER RESPONSE :: " + response);
                            ParseHelper.getInstance().setCallback(mCallback).parse(response, classTag);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error != null && error.getMessage() != null) {
                                LogUtil.error("SERVER ERROR :: " + error.getMessage());
                            }

                            String errorMsg = "";
                            if(error != null)
                                errorMsg = error.getMessage();

                            mCallback.onResponseError(errorMsg);
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headerMap = new HashMap<>();

                    if(headers != null)
                        headerMap.putAll(headers);

                    return headerMap;
                }

                protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                    HashMap<String, String> paramMap = new HashMap<>();

                    if(params != null)
                        paramMap.putAll(params);

                    return paramMap;
                }
            };

            request.setTag(url);
            request.setRetryPolicy(new DefaultRetryPolicy(AppConstant.REQUEST_TIMEOUT,
                    AppConstant.MAX_RETRIES, AppConstant.BACKOFF_MULT));

            if(mContext != null)
                Volley.newRequestQueue(mContext).add(request);
        } else {
            SnackBarUtil.shortSnack(((Activity) mContext).getWindow().getDecorView().
                    findViewById(android.R.id.content),
                    AppConstant.MSG_NO_NETWORK);
        }
    }

    public void cancelAllRequests(String url) {
        if (url != null && !TextUtils.isEmpty(url)) {
            try {
                Volley.newRequestQueue(mContext).cancelAll(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public RequestQueue getVolleyRequestQueue() {
//        if (mRequestQueue == null) {
//            mRequestQueue = Volley.newRequestQueue
//                    (this, new OkHttpStack(new OkHttpClient()));
//        }
//
//        return mRequestQueue;
//    }

}

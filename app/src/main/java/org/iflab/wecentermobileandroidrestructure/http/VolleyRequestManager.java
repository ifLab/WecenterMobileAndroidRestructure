package org.iflab.wecentermobileandroidrestructure.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hcjcch on 15/7/7.
 */
public class VolleyRequestManager {
    private static RequestQueue mRequestQueue;

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    private static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    private static void addRequest(Request<?> request) {
        addRequest(request, null);
    }

    private static void cancleAll(Object tag){
        mRequestQueue.cancelAll(tag);
    }

}
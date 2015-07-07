package org.iflab.wecentermobileandroidrestructure.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by hcjcch on 15/7/7.
 */
public class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> listener;
    private final Gson gson = new Gson();
    private final Class<T> mclass;
    private Map<String, String> params;
    private final Map<String, String> headers;

    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> mclass, Map<String, String> params, Map<String, String> headers) {
        super(method, url, listener);
        this.listener = listener1;
        this.mclass = mclass;
        this.params = params;
        this.headers = headers;
    }

    public GsonRequest(String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> mclass, Map<String, String> params, Map<String, String> headers) {
        this(Method.GET, url, listener, listener1, mclass, params, headers);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(gson.fromJson(jsonString, mclass), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        listener.onResponse(t);
    }


}
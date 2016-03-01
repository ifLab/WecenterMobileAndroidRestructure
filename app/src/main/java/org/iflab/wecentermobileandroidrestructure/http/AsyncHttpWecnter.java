package org.iflab.wecentermobileandroidrestructure.http;

/**
 * Created by hcjcch on 15/5/17.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hcjcch on 2015/2/13.
 * 网络访问类，使用第三方库AsyncHttpClient
 */

public class AsyncHttpWecnter {
    public static final String TAG = "AsyncHttpWecnter";
    public static final String BASE_URL = "http://www.fanfan.cn/";
    public static final String SIGN = "LyD1Hw77VL95xKN9H2ydlyQKH1";
    public static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * get方式
     *
     * @param url             访问的地址
     * @param params          参数
     * @param responseHandler 回调
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * post方式
     *
     * @param url             访问的地址
     * @param params          参数
     * @param responseHandler 回调
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public enum Request {
        Get, Post
    }

    /**
     * 再次封装
     *
     * @param context
     * @param url
     * @param params
     * @param type
     * @param netWork
     */
    public static void loadData(final Context context, final String url, RequestParams params, Request type, final NetWork netWork) {
        JsonHttpResponseHandler jsonHttpResponseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                netWork.start();
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "success   [url:" + url + "]" + response);
                try {
                    int error = response.getInt("errno");
                    if (error == -1) {
                        if (response.getString("err") != null) {
                            String err = response.getString("err");
                            Toast.makeText(context, err, Toast.LENGTH_SHORT).show();
                        } else {
                            //TODO error
                            Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                    JSONObject rsm = response.getJSONObject("rsm");
                    netWork.parseJson(rsm);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "数据错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "失败   [url:" + url +" ]" + responseString);
                //TODO 访问失败
                netWork.failure();
            }

            @Override
            public void onFinish() {
                netWork.finish();
                super.onFinish();
            }
        };
        switch (type) {
            case Get:
                client.get(getAbsoluteUrl(url), params, jsonHttpResponseHandler);
                break;
            case Post:
                client.post(getAbsoluteUrl(url), params, jsonHttpResponseHandler);
                break;
        }
    }

    /**
     * 获取网络资源的绝对路径，因为所有的资源路径，起始部分相同
     *
     * @param relativeUrl 相对路径
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void setCookieStore(PersistentCookieStore cookieStore) {
        client.setCookieStore(cookieStore);
    }

    public static void clear(Context context){
        client.clearBasicAuth();
        client.cancelAllRequests(true);
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        myCookieStore.clear();
    }

    public static void cancelAllRequest() {
        client.cancelAllRequests(true);
    }
}
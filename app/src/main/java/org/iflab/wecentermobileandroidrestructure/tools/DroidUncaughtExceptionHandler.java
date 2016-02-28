package org.iflab.wecentermobileandroidrestructure.tools;

/**
 * Created by Lyn on 15/12/10.
 */
import android.app.ActivityManager;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;

public class DroidUncaughtExceptionHandler  implements  Thread.UncaughtExceptionHandler{
    private static final String LOGTAG = "DroidUncaughtExceptionHandler";
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;
    private Context mAppContext;

    public DroidUncaughtExceptionHandler(Context context) {
        mAppContext = context.getApplicationContext();
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static String getProcessName(Context appContext) {
        String currentProcessName = "";
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                currentProcessName = processInfo.processName;
                break;
            }
        }
        return currentProcessName;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String trace = "";
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        for(int i=0;i < stackTraceElements .length;i++){
            trace += stackTraceElements[i];
        }
        Log.v("see me",ex.getMessage()+"\n"+trace);
        sendCrashLog("[Android]["+Global.TimeStamp2Date(System.currentTimeMillis(),"yyyy-MM-dd")+"] "
                + ex.getMessage()+"\n"+trace);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void sendCrashLog(String content){
        RequestParams params = new RequestParams();
        params.put("content",content);
        Log.v("RelativeUrl.CRASH_LOG",RelativeUrl.CRASH_LOG);
        AsyncHttpWecnter.post(RelativeUrl.CRASH_LOG, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String res = new String(responseBody);
            }
        });
    }

}
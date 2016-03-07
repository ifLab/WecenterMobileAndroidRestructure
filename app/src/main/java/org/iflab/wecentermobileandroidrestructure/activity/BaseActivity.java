package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.leakcanary.RefWatcher;

import org.iflab.wecentermobileandroidrestructure.application.WecenterApplication;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hcjcch on 15/5/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * json预处理方法
     *
     * @param json 要处理的json
     */
    protected boolean jsonPreproccess(String json) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            int error = jsonObject.getInt("errno");
            if (error == -1) {
                if (jsonObject.getString("err") != null) {
                    toast(jsonObject.getString("err"));
                } else {
                    toast("error");
                }
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            toast("数据异常");
            return true;
        }
        return false;
    }

    /**
     * @param toastString
     */

    public void toast(String toastString) {
        Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
    }

    public void toastNo200() {
        Toast.makeText(getApplicationContext(), "与服务器连接错误", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = WecenterApplication.getRefWatcher(getApplication());
//        refWatcher.watch(this);
    }

    public void setUpToolBar(Toolbar toolBar){
        if(toolBar != null) {
            setSupportActionBar(toolBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public boolean checkIsLogin(Context context){
        PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
        if (myCookieStore.getCookies().size() == 0) {
            startActivity(new Intent(context, LoginActivity.class));
            return false;
        }
        return true;
    }

    public boolean checkLoginStatus(){
        PersistentCookieStore myCookieStore  = new PersistentCookieStore(getApplicationContext());
        return myCookieStore.getCookies().size() > 0;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if(level >=TRIM_MEMORY_MODERATE){
            ImageLoader.getInstance().clearDiskCache();
            ImageLoader.getInstance().clearMemoryCache();
            Log.e("TRIM_MEMORY_MODERATE","clear ImageLoader Cache...");
        }
    }
}
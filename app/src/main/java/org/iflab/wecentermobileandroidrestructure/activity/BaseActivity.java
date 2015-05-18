package org.iflab.wecentermobileandroidrestructure.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    protected void findViews() {

    }

    protected void setViews() {

    }

    protected void setListeners() {

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
}

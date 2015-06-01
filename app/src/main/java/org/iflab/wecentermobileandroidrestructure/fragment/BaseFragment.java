package org.iflab.wecentermobileandroidrestructure.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hcjcch on 15/6/1.
 */
public class BaseFragment extends Fragment {
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
        Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();
    }
}
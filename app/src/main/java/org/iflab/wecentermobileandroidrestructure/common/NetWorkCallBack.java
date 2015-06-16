package org.iflab.wecentermobileandroidrestructure.common;

import org.json.JSONObject;

/**
 * Created by hcjcch on 15/6/16.
 */
public interface NetWorkCallBack {
    void parseJson(JSONObject response);

    void start();

    void finish();

    void failure();
}
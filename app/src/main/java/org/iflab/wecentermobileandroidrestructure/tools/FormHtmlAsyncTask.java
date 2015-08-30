package org.iflab.wecentermobileandroidrestructure.tools;

import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/30 16:32
 */

public class FormHtmlAsyncTask extends AsyncTask<String, Void, Spanned> {

    private WecenterImageGetter wecenterImageGetter;
    private TextView textView;

    public FormHtmlAsyncTask(WecenterImageGetter wecenterImageGetter, TextView textView) {
        this.wecenterImageGetter = wecenterImageGetter;
        this.textView = textView;
    }


    @Override
    protected Spanned doInBackground(String... params) {
        return Html.fromHtml(params[0], wecenterImageGetter, null);
    }

    @Override
    protected void onPostExecute(Spanned s) {
        textView.setText(s);
        super.onPostExecute(s);
    }
}

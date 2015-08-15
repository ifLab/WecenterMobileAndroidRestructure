package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PhotoPickActivity;
import org.iflab.wecentermobileandroidrestructure.application.WecenterApplication;

/**
 * Created by hcjcch on 15/5/23.
 */
public class PhotolGridAllAdapter extends PhotoPickGridAdapter {
    public PhotolGridAllAdapter(Context context, Cursor c, boolean autoRequery, PhotoPickActivity activity) {
        super(context, c, autoRequery, activity);
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position > 0) {
            return super.getItem(position - 1);
        } else {
            return super.getItem(position);
        }
    }

    @Override
    public long getItemId(int position) {
        if (position > 0) {
            return super.getItemId(position - 1);
        } else {
            return -1;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (position > 0) {
            return super.getDropDownView(position - 1, convertView, parent);
        } else {
            return getView(position, convertView, parent);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position > 0) {
            return super.getView(position - 1, convertView, parent);
        } else {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.item_photopick_camera, parent, false);
                convertView.getLayoutParams().height = WecenterApplication.sWidthPix / 3;
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.camera();
                    }
                });
            }

            return convertView;
        }
    }
}
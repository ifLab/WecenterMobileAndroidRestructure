package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PhotoPickActivity;
import org.iflab.wecentermobileandroidrestructure.application.WecenterApplication;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;

/**
 * Created by hcjcch on 15/5/22.
 */
public class PhotoPickGridAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;

    public PhotoPickGridAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = layoutInflater.inflate(R.layout.item_photopick_gridlist, parent, false);
        convertView.getLayoutParams().height = WecenterApplication.sWidthPix / 3;
        GridViewHolder holder = new GridViewHolder();
        holder.icon = (ImageView) convertView.findViewById(R.id.icon);
        holder.iconFore = (ImageView) convertView.findViewById(R.id.iconFore);
        holder.check = (CheckBox) convertView.findViewById(R.id.check);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GridViewHolder holder;
        holder = (GridViewHolder) view.getTag();
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = ImageInfo.pathAddPreFix(cursor.getString(1));
        System.out.println(cursor);
        System.out.println(path);
        imageLoader.displayImage(path, holder.icon, PhotoPickActivity.optionsImage);
    }

    static class GridViewHolder {
        ImageView icon;
        ImageView iconFore;
        CheckBox check;
    }
}
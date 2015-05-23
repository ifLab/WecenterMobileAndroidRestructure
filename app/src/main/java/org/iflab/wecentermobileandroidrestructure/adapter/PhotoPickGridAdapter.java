package org.iflab.wecentermobileandroidrestructure.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import org.iflab.wecentermobileandroidrestructure.activity.PhotoPickActivity.GridViewCheckTag;

/**
 * Created by hcjcch on 15/5/22.
 */
public class PhotoPickGridAdapter extends CursorAdapter {

    protected LayoutInflater layoutInflater;
    private PhotoPickActivity activity;

    public PhotoPickGridAdapter(Context context, Cursor c, boolean autoRequery, PhotoPickActivity activity) {
        super(context, c, autoRequery);
        layoutInflater = LayoutInflater.from(context);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = layoutInflater.inflate(R.layout.item_photopick_gridlist, parent, false);
        convertView.getLayoutParams().height = WecenterApplication.sWidthPix / 3;
        GridViewHolder holder = new GridViewHolder();
        holder.icon = (ImageView) convertView.findViewById(R.id.icon);
        holder.iconFore = (ImageView) convertView.findViewById(R.id.iconFore);
        holder.check = (CheckBox) convertView.findViewById(R.id.check);
        holder.check.setOnClickListener(mClickItem);
        GridViewCheckTag checkTag = new GridViewCheckTag(holder.iconFore);
        holder.check.setTag(checkTag);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GridViewHolder holder;
        holder = (GridViewHolder) view.getTag();
        ImageLoader imageLoader = ImageLoader.getInstance();
        String path = ImageInfo.pathAddPreFix(cursor.getString(1));
        imageLoader.displayImage(path, holder.icon, PhotoPickActivity.optionsImage);
        ((GridViewCheckTag) holder.check.getTag()).path = path;
        boolean picked = activity.isPicked(path);
        holder.check.setChecked(picked);
        holder.iconFore.setVisibility(picked ? View.VISIBLE : View.INVISIBLE);
    }

    static class GridViewHolder {
        ImageView icon;
        ImageView iconFore;
        CheckBox check;
    }


    View.OnClickListener mClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activity.clickPhotoItem(v);
        }
    };
}
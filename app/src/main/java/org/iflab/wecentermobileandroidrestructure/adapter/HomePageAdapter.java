package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.iflab.wecentermobileandroidrestructure.R;

/**
 * Created by hcjcch on 15/5/19.
 */
public class HomePageAdapter extends BaseAdapter {
    private Context context;

    public HomePageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.home_page_two_cell,parent,false);
        return convertView;
    }
}
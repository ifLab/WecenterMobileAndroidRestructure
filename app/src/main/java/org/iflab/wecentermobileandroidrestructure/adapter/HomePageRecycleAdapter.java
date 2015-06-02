package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecentermobileandroidrestructure.R;

/**
 * Created by hcjcch on 15/6/2.
 */
public class HomePageRecycleAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;


    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_TEXT.ordinal() : ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
    }

    public HomePageRecycleAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new Holder1(mLayoutInflater.inflate(R.layout.home_page_two_cell, viewGroup, false));
        } else {
            return new Holder2(mLayoutInflater.inflate(R.layout.home_page_one_cell, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class Holder1 extends RecyclerView.ViewHolder {

        public Holder1(View itemView) {
            super(itemView);
        }
    }

    public static class Holder2 extends RecyclerView.ViewHolder {

        public Holder2(View itemView) {
            super(itemView);
        }
    }
}

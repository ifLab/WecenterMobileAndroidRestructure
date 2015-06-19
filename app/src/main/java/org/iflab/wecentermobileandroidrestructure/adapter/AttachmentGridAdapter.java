package org.iflab.wecentermobileandroidrestructure.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PublishAnswerArticleActivity;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;
import org.iflab.wecentermobileandroidrestructure.tools.Global;

import java.util.ArrayList;

/**
 * Created by hcjcch on 15/5/23.
 */
public class AttachmentGridAdapter extends BaseAdapter {

    private ArrayList<PublishAnswerArticleActivity.PhotoData> datas;

    public AttachmentGridAdapter(ArrayList<PublishAnswerArticleActivity.PhotoData> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImageView image = (ImageView) inflater.inflate(R.layout.item_public_answer_attachment, parent, false);
        if (position == getCount() - 1) {
            if (getCount() == PublishAnswerArticleActivity.PHOTO_MAX_COUNT + 1) {
                image.setVisibility(View.GONE);
            } else {
                image.setVisibility(View.VISIBLE);
                image.setImageResource(R.mipmap.publish_answer_article);
            }
        } else {
            image.setVisibility(View.VISIBLE);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(datas.get(position).getUri().toString(), image);
        }
        return image;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
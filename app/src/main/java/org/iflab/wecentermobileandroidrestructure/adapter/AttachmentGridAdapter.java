package org.iflab.wecentermobileandroidrestructure.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PublishAnswerArticle;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;

import java.util.ArrayList;

/**
 * Created by hcjcch on 15/5/23.
 */
public class AttachmentGridAdapter extends BaseAdapter {

    private ArrayList<ImageInfo> datas;
    private LayoutInflater inflater;

    public AttachmentGridAdapter(ArrayList<ImageInfo> datas) {
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
        inflater = LayoutInflater.from(parent.getContext());
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            holder.image = (ImageView) inflater.inflate(R.layout.item_public_answer_attachment, parent, false);
            holder.image.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == getCount() - 1){
            if (getCount() == PublishAnswerArticle.PHOTO_MAX_COUNT + 1){
                holder.image.setVisibility(View.GONE);
            }else {
                holder.image.setVisibility(View.VISIBLE);
                holder.image.setImageResource(R.mipmap.publish_answer_article);
            }
        }else {
            holder.image.setVisibility(View.VISIBLE);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.loadImage(datas.get(position).path,new ImageSize(120,120),new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

//                    super.onLoadingComplete(imageUri, view, loadedImage);
                }
            });
        }
        return holder.image;
    }

    class ViewHolder {
        ImageView image;
    }
}
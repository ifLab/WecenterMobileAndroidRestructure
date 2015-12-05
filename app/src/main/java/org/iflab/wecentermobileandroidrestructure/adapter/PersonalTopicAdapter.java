package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.TopicsActivity;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalQuestion;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalTopic;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/22 01:09
 */

public class PersonalTopicAdapter extends RecyclerView.Adapter {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<PersonalTopic.RowsEntity> datas;
    private String userName;
    String avatar;

    public PersonalTopicAdapter(Context mContext, List<PersonalTopic.RowsEntity> datas, String userName,String avatar) {
        this.mContext = mContext;
        this.datas = datas;
        this.userName = userName;
        this.avatar = avatar;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<PersonalTopic.RowsEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalTopicHolder(mLayoutInflater.inflate(R.layout.item_personal_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((PersonalTopicHolder) holder).txt_topic_name.setText(datas.get(position).getTopic_title() + "\n"+
                datas.get(position).getTopic_description());
        ((PersonalTopicHolder) holder).linear_topic_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TopicsActivity.class);
                intent.putExtra("topic_id",datas.get(position).getTopic_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class PersonalTopicHolder extends RecyclerView.ViewHolder {

        TextView txt_topic_name;
        ImageView image_avatar;
        LinearLayout linear_topic_item;

        public PersonalTopicHolder(View itemView) {
            super(itemView);
            txt_topic_name = (TextView) itemView.findViewById(R.id.txt_topic_name);
            image_avatar = (ImageView)itemView.findViewById(R.id.image_avatar);
            linear_topic_item = (LinearLayout)itemView.findViewById(R.id.linear_topic_item);
        }
    }
}
package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;
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

    public PersonalTopicAdapter(Context mContext, List<PersonalTopic.RowsEntity> datas, String userName) {
        this.mContext = mContext;
        this.datas = datas;
        this.userName = userName;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PersonalTopicHolder) holder).txt_topic_name.setText(datas.get(position).getTopic_title());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class PersonalTopicHolder extends RecyclerView.ViewHolder {

        TextView txt_topic_name;

        public PersonalTopicHolder(View itemView) {
            super(itemView);
            txt_topic_name = (TextView) itemView.findViewById(R.id.txt_topic_name);
        }
    }
}
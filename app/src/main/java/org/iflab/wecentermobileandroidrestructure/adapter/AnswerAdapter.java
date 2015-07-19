package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.question.AnswerInfo;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by client on 15/7/18.
 */
public class AnswerAdapter extends BaseAdapter {
    List<AnswerInfo> answerInfoList = new ArrayList<>();
    Context context;

    public  AnswerAdapter(Context context,List<AnswerInfo> answerInfoList){
        this.answerInfoList = answerInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answerInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return answerInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_answers_listview, parent, false);
            holder = new ViewHolder();
            holder.profileImageView = (ImageView) convertView.findViewById(R.id.image_profile);
            holder.contentTextView = (TextView) convertView.findViewById(R.id.txt_user_action_content);
            holder.userNameTextView = (TextView) convertView.findViewById(R.id.txt_user_name);
            holder.agreeTextView = (TextView) convertView.findViewById(R.id.txt_agree_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AnswerInfo answerInfo = answerInfoList.get(position);
        
        holder.contentTextView.setText(answerInfo.getAnswer_content());
        holder.userNameTextView.setText(answerInfo.getUser_name());
        holder.agreeTextView.setText(answerInfo.getAgree_count()+"");
        ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + answerInfo.getAvatar_file(),holder.profileImageView, ImageOptions.optionsImage);

        return convertView;
    }

    class ViewHolder{
        TextView userNameTextView;
        TextView contentTextView;
        ImageView profileImageView;
        TextView agreeTextView;

    }
}

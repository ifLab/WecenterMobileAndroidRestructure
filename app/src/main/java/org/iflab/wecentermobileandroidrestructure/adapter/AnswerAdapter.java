package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionAnswerActivity;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.question.AnswerInfo;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyn on 15/7/18.
 */
public class AnswerAdapter extends BaseAdapter {
    List<AnswerInfo> answerInfoList = new ArrayList<>();
    Context context;
    String questionContent;

    public  AnswerAdapter(Context context,List<AnswerInfo> answerInfoList,String questionContent){
        this.answerInfoList = answerInfoList;
        this.context = context;
        this.questionContent = questionContent;
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
            holder.answerRel = (RelativeLayout)convertView.findViewById(R.id.rel_answer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final AnswerInfo answerInfo = answerInfoList.get(position);
        
        holder.contentTextView.setText(answerInfo.getAnswer_content());
        holder.userNameTextView.setText(answerInfo.getUser_name());
        holder.agreeTextView.setText(answerInfo.getAgree_count() + "");
        ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + answerInfo.getAvatar_file(), holder.profileImageView, ImageOptions.optionsImage);
        holder.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonalCenterActivity.openPersonalCenter(context,answerInfo.getUid());
            }
        });

        holder.answerRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionAnswerActivity.class);
                intent.putExtra("answer_id",answerInfo.getAnswer_id());
                intent.putExtra("question_title",questionContent);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView userNameTextView;
        TextView contentTextView;
        ImageView profileImageView;
        TextView agreeTextView;
        RelativeLayout answerRel;
    }
}

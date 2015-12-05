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
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionAnswerActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionDetailActivity;
import org.iflab.wecentermobileandroidrestructure.model.personal.TopicDetailAnswer;

import java.util.List;

/**
 * Created by Lyn on 15/12/3.
 */
public class TopicDetailAdapter extends RecyclerView.Adapter{
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<TopicDetailAnswer.RowsEntity> datas;

    public TopicDetailAdapter(List<TopicDetailAnswer.RowsEntity> datas,
                                 Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalAnswerHolder(mLayoutInflater.inflate(R.layout.item_personal_answer, parent, false));
    }

    public void setData(List<TopicDetailAnswer.RowsEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TopicDetailAnswer.RowsEntity entity = datas.get(position);
        ((PersonalAnswerHolder) holder).txt_question_title.setText(entity.getQuestion_info().getQuestion_content());
        ((PersonalAnswerHolder) holder).txt_user_name.setText(entity.getUser_info().getUser_name());
//        ((PersonalAnswerHolder) holder).txt_signature.setText(signature);
        ((PersonalAnswerHolder) holder).txt_agree_count.setText(entity.getAnswer_info().getAgree_count()+"");
        ImageLoader.getInstance().displayImage(entity.getUser_info().getAvatar_file(),((PersonalAnswerHolder) holder).img_user);
        ((PersonalAnswerHolder) holder).txt_answer_content.setText(entity.getAnswer_info().getAnswer_content());
        ((PersonalAnswerHolder) holder).rel_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDetailActivity.openQuestionDetail(mContext, entity.getUser_info().getUid(),entity.getQuestion_info().getQuestion_id());
            }
        });
        ((PersonalAnswerHolder) holder).rel_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalCenterActivity.openPersonalCenter(mContext, entity.getUser_info().getUid());
            }
        });

        ((PersonalAnswerHolder) holder).rel_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("answer_id", entity.getAnswer_info().getAnswer_id());
                intent.putExtra("question_title", entity.getQuestion_info().getQuestion_content());
                intent.setClass(mContext, QuestionAnswerActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class PersonalAnswerHolder extends RecyclerView.ViewHolder {

        LinearLayout rel_left;
        RelativeLayout rel_top;
        RelativeLayout rel_right;
        TextView txt_question_title;
        TextView txt_user_name;
        //        TextView txt_signature;
        TextView txt_answer_content;
        TextView txt_agree_count;
        ImageView img_user;


        public PersonalAnswerHolder(View itemView) {
            super(itemView);
            rel_top = (RelativeLayout) itemView.findViewById(R.id.rel_top);
            rel_left = (LinearLayout) itemView.findViewById(R.id.rel_left);
            rel_right = (RelativeLayout) itemView.findViewById(R.id.rel_right);
            txt_question_title = (TextView) itemView.findViewById(R.id.txt_question_title);
            txt_user_name = (TextView) itemView.findViewById(R.id.txt_user_name);
//            txt_signature = (TextView) itemView.findViewById(R.id.txt_signature);
            txt_answer_content = (TextView) itemView.findViewById(R.id.txt_answer_content);
            img_user = (ImageView)itemView.findViewById(R.id.img_user);
            txt_agree_count = (TextView) itemView.findViewById(R.id.txt_agree_count);

        }
    }
}


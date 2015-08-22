package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionAnswerActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionDetailActivity;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalAnswer;

import java.util.List;


/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/17 23:50
 */

public class PersonalAnswerAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<PersonalAnswer.RowsEntity> datas;
    private String userName;
    private String signature;
    private String uid;

    public PersonalAnswerAdapter(String signature, String userName, List<PersonalAnswer.RowsEntity> datas, Context mContext, String uid) {
        this.signature = signature;
        this.userName = userName;
        this.datas = datas;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.uid = uid;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalAnswerHolder(mLayoutInflater.inflate(R.layout.item_personal_answer, parent, false));
    }

    public void setData(List<PersonalAnswer.RowsEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PersonalAnswer.RowsEntity entity = datas.get(position);
        Log.d("huangchen", (((PersonalAnswerHolder) holder).txt_question_title == null) + "");
        ((PersonalAnswerHolder) holder).txt_question_title.setText(entity.getQuestion_title());
        ((PersonalAnswerHolder) holder).txt_user_name.setText(userName);
        ((PersonalAnswerHolder) holder).txt_signature.setText(signature);
        ((PersonalAnswerHolder) holder).txt_answer_content.setText(entity.getAnswer_content());
        ((PersonalAnswerHolder) holder).rel_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDetailActivity.openQuestionDetail(mContext, Integer.parseInt(uid), Integer.parseInt(entity.getQuestion_id()));
            }
        });
        ((PersonalAnswerHolder) holder).rel_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalCenterActivity.openPersonalCenter(mContext, Integer.parseInt(uid));
            }
        });

        ((PersonalAnswerHolder) holder).rel_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("answer_id", Integer.parseInt(entity.getAnswer_id()));
                intent.putExtra("question_title", entity.getQuestion_title());
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
        TextView txt_signature;
        TextView txt_answer_content;
        TextView img_user;


        public PersonalAnswerHolder(View itemView) {
            super(itemView);
            rel_top = (RelativeLayout) itemView.findViewById(R.id.rel_top);
            rel_left = (LinearLayout) itemView.findViewById(R.id.rel_left);
            rel_right = (RelativeLayout) itemView.findViewById(R.id.rel_right);
            txt_question_title = (TextView) itemView.findViewById(R.id.txt_question_title);
            txt_user_name = (TextView) itemView.findViewById(R.id.txt_user_name);
            txt_signature = (TextView) itemView.findViewById(R.id.txt_signature);
            txt_answer_content = (TextView) itemView.findViewById(R.id.txt_answer_content);
        }
    }
}

package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.ArticleActivity;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalArticle;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalQuestion;
import org.iflab.wecentermobileandroidrestructure.tools.Global;

import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/8 20:47
 */

public class PersonalArticleAdapter extends RecyclerView.Adapter {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<PersonalArticle.RowsEntity> datas;
    private String userName;
    private String userAvatar;
    private int uid;

    public PersonalArticleAdapter(Context mContext, List<PersonalArticle.RowsEntity> datas, String userName, String userAvatar, int uid) {
        this.mContext = mContext;
        this.datas = datas;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.uid = uid;
    }

    public void setData(List<PersonalArticle.RowsEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalQuestionHolder(mLayoutInflater.inflate(R.layout.item_personal_question, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((PersonalQuestionHolder) holder).txt_user_name.setText(userName);
        ((PersonalQuestionHolder) holder).txt_question_title.setText(datas.get(position).getArticle_info().getTitle());
        ((PersonalQuestionHolder) holder).txt_question_content.setText(datas.get(position).getArticle_info().getMessage());
        ((PersonalQuestionHolder) holder).ask_time_txt.setText(Global.TimeStamp2Date(datas.get(position).getArticle_info().getAdd_time()+"","yyyy-MM-dd"));
        ImageLoader.getInstance().displayImage(userAvatar,((PersonalQuestionHolder) holder).img_user);
        ((PersonalQuestionHolder) holder).relLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalCenterActivity.openPersonalCenter(mContext, uid);
            }
        });
        ((PersonalQuestionHolder) holder).relRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleActivity.openArticle(mContext, datas.get(position).getArticle_info().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class PersonalQuestionHolder extends RecyclerView.ViewHolder {

        private RelativeLayout relLeft;
        private RelativeLayout relRight;
        private TextView txt_user_name;
        private TextView ask_time_txt;
        private TextView txt_question_title;
        private TextView txt_question_content;
        private ImageView img_user;

        public PersonalQuestionHolder(View itemView) {
            super(itemView);
            relLeft = (RelativeLayout) itemView.findViewById(R.id.rel_left);
            relRight = (RelativeLayout) itemView.findViewById(R.id.rel_right);
            txt_user_name = (TextView) itemView.findViewById(R.id.txt_user_name);
            ask_time_txt = (TextView) itemView.findViewById(R.id.ask_time_txt);
            txt_question_title = (TextView) itemView.findViewById(R.id.txt_question_title);
            txt_question_content = (TextView) itemView.findViewById(R.id.txt_question_content);
            img_user = (ImageView) itemView.findViewById(R.id.img_user);
        }
    }
}
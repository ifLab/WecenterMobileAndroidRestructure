package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.content.Intent;
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
import org.iflab.wecentermobileandroidrestructure.activity.QuestionAnswerActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionDetailActivity;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.found.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.found.BaseFoundInfo;
import org.iflab.wecentermobileandroidrestructure.model.found.QuestionInfo;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;

import java.util.List;

/**
 * Created by hcjcch on 15/7/8.
 */
public class FoundAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<BaseFoundInfo> datas;

    public FoundAdapter(Context mContext, List<BaseFoundInfo> datas) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoundHolder(mLayoutInflater.inflate(R.layout.item_found, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (datas.get(position).getType().equalsIgnoreCase("article")) {
            final ArticleInfo articleInfo = (ArticleInfo) datas.get(position);
            ((FoundHolder) holder).txt_user_name.setText(articleInfo.getUserName());
//            ((FoundHolder) holder).rel_bottom.setVisibility(View.GONE);
            ((FoundHolder) holder).txt_user_action_content.setText(articleInfo.getArticleMessage());
            ((FoundHolder) holder).txt_cell_type_question_or_article.setText("文章");
            ImageLoader.getInstance().displayImage(articleInfo.getAvatarFile(), ((FoundHolder) holder).profile_image, ImageOptions.optionsImage);
            ((FoundHolder) holder).profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalCenterActivity.openPersonalCenter(mContext, articleInfo.getUid());
                }
            });
            ((FoundHolder) holder).rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleActivity.openArticle(mContext, articleInfo.getArticleId());
                }
            });
        } else {
            final QuestionInfo questionInfo = (QuestionInfo) datas.get(position);
            ((FoundHolder) holder).txt_user_name.setText(questionInfo.getPublishUserName());
            ((FoundHolder) holder).txt_user_action_content.setText(questionInfo.getQuestionContent());
            ((FoundHolder) holder).txt_cell_type_question_or_article.setText("问题");
            ImageLoader.getInstance().displayImage(questionInfo.getPublishAvatarFile(), ((FoundHolder) holder).profile_image, ImageOptions.optionsImage);
            ((FoundHolder) holder).profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalCenterActivity.openPersonalCenter(mContext, questionInfo.getPublishUid());
                }
            });
            ((FoundHolder) holder).rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionDetailActivity.openQuestionDetail(mContext, questionInfo.getPublishUid(), questionInfo.getQuestionId());
                }
            });
//            if (questionInfo.getAnswerUserName() != null) {
//                ((FoundHolder) holder).rel_bottom.setVisibility(View.VISIBLE);
//                ((FoundHolder) holder).txt_answer_user_name.setText(questionInfo.getAnswerUserName());
//                ((FoundHolder) holder).txt_answer.setText(questionInfo.getAnswerContent());
//                ImageLoader.getInstance().displayImage(questionInfo.getAnswerAvatarFile(), ((FoundHolder) holder).profile_image_answer, ImageOptions.optionsImage);
//                ((FoundHolder) holder).profile_image_answer.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        PersonalCenterActivity.openPersonalCenter(mContext, questionInfo.getAnswerUid());
//                    }
//                });
                ((FoundHolder) holder).rel_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.putExtra("answer_id",questionInfo.get);
//                        intent.putExtra("question_title", questionInfo.getques);
//                        intent.setClass(mContext, QuestionAnswerActivity.class);
//                        mContext.startActivity(intent);
                    }
                });
//            } else {
//                ((FoundHolder) holder).rel_bottom.setVisibility(View.GONE);
//            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class FoundHolder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView txt_user_name;
        TextView txt_user_action_content;
        TextView txt_cell_type_question_or_article;
//        TextView txt_cell_type_answer;
//        TextView txt_answer_user_name;
//        ImageView profile_image_answer;
//        TextView txt_answer;
//        RelativeLayout rel_bottom;
        RelativeLayout rel;
        RelativeLayout rel_1;

        public FoundHolder(View itemView) {
            super(itemView);
            profile_image = (ImageView) itemView.findViewById(R.id.image_profile);
            txt_user_name = (TextView) itemView.findViewById(R.id.txt_user_name);
            txt_user_action_content = (TextView) itemView.findViewById(R.id.txt_user_action_content);
            txt_cell_type_question_or_article = (TextView) itemView.findViewById(R.id.txt_cell_type_question_or_article);
//            txt_cell_type_answer = (TextView) itemView.findViewById(R.id.txt_answer_user_name);
//            txt_answer_user_name = (TextView) itemView.findViewById(R.id.txt_answer_user_name);
//            profile_image_answer = (ImageView) itemView.findViewById(R.id.profile_image_answer);
//            txt_answer = (TextView) itemView.findViewById(R.id.txt_answer);
//            rel_bottom = (RelativeLayout) itemView.findViewById(R.id.rel_bottom);
            rel = (RelativeLayout) itemView.findViewById(R.id.rel);
            rel_1 = (RelativeLayout) itemView.findViewById(R.id.rel_1);
        }

    }
}

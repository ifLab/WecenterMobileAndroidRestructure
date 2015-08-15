package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.ArticleActivity;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionDetailActivity;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.homepage.HomePage;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;

import java.util.List;

/**
 * Created by hcjcch on 15/6/2.
 */
public class HomePageRecycleAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<HomePage> homePages;


    public enum ITEM_TYPE {
        HOME_PAGE_TWO_CELL,
        HOME_PAGE_ONE_CELL
    }

    @Override
    public int getItemViewType(int position) {
        int action = homePages.get(position).getAssociateAction();
        if (action == 201 || action == 204) {
            return ITEM_TYPE.HOME_PAGE_TWO_CELL.ordinal();
        }
        return ITEM_TYPE.HOME_PAGE_ONE_CELL.ordinal();
    }

    public HomePageRecycleAdapter(Context mContext, List<HomePage> homePages) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.homePages = homePages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == ITEM_TYPE.HOME_PAGE_TWO_CELL.ordinal()) {
            return new HomePageTwoCellHolder(mLayoutInflater.inflate(R.layout.home_page_two_cell, viewGroup, false));
        } else {
            return new HomePageOneCellHolder(mLayoutInflater.inflate(R.layout.home_page_one_cell, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int userAction = homePages.get(i).getAssociateAction();
         final HomePage homePage = homePages.get(i);
        if (viewHolder instanceof HomePageTwoCellHolder) {
            ((HomePageTwoCellHolder) viewHolder).userName.setText(homePage.getUserInfo().getUserName());
            ((HomePageTwoCellHolder) viewHolder).rel_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PersonalCenterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", homePage.getUid());
                    intent.putExtra("bundle", bundle);
                    mContext.startActivity(intent);
                }
            });
            ((HomePageTwoCellHolder) viewHolder).userActionContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, QuestionDetailActivity.class);
                    intent.putExtra("uid", homePage.getUid());
                    intent.putExtra("question_id",homePage.getQuestionInfo().getQuestionId());
                    mContext.startActivity(intent);
                }
            });
            ((HomePageTwoCellHolder) viewHolder).userAnswerContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ArticleActivity.class);
                    intent.putExtra("article_id", homePage.getAnswerInfo().getAnswerId());
                    mContext.startActivity(intent);
                }
            });
            switch (userAction) {
                case 201:
                    ((HomePageTwoCellHolder) viewHolder).userAction.setText("回答了问题");
                    ((HomePageTwoCellHolder) viewHolder).userActionContent.setText(homePage.getQuestionInfo().getQuestionContent());
                    ((HomePageTwoCellHolder) viewHolder).userAnswerContent.setText(homePage.getAnswerInfo().getAnswerContent());
                    ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + homePage.getUserInfo().getUserAvatar(), ((HomePageTwoCellHolder) viewHolder).userProfile, ImageOptions.optionsImage);
                    break;
                case 204:
                    ((HomePageTwoCellHolder) viewHolder).userAction.setText("赞同了回答");
                    ((HomePageTwoCellHolder) viewHolder).userActionContent.setText(homePage.getQuestionInfo().getQuestionContent());
                    ((HomePageTwoCellHolder) viewHolder).userAnswerContent.setText(homePage.getAnswerInfo().getAnswerContent());
                    ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + homePage.getUserInfo().getUserAvatar(), ((HomePageTwoCellHolder) viewHolder).userProfile, ImageOptions.optionsImage);
                    break;
            }
        } else {
            ((HomePageOneCellHolder) viewHolder).userName.setText(homePage.getUserInfo().getUserName());
            ((HomePageOneCellHolder) viewHolder).userProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PersonalCenterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", homePage.getUid());
                    intent.putExtra("bundle", bundle);
                    mContext.startActivity(intent);
                }
            });
            switch (userAction) {
                case 101:
                    ((HomePageOneCellHolder) viewHolder).userName.setText(homePage.getUserInfo().getUserName());
                    ((HomePageOneCellHolder) viewHolder).userAction.setText("发布了问题");
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setText(homePage.getQuestionInfo().getQuestionContent());
                    ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + homePage.getUserInfo().getUserAvatar(), ((HomePageOneCellHolder) viewHolder).userProfile, ImageOptions.optionsImage);
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, QuestionDetailActivity.class);
                            intent.putExtra("uid", homePage.getUid());
                            intent.putExtra("question_id",homePage.getQuestionInfo().getQuestionId());
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case 105:
                    ((HomePageOneCellHolder) viewHolder).userName.setText(homePage.getUserInfo().getUserName());
                    ((HomePageOneCellHolder) viewHolder).userAction.setText("关注了问题");
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setText(homePage.getQuestionInfo().getQuestionContent());
                    ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + homePage.getUserInfo().getUserAvatar(), ((HomePageOneCellHolder) viewHolder).userProfile, ImageOptions.optionsImage);
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, QuestionDetailActivity.class);
                            intent.putExtra("uid", homePage.getUid());
                            intent.putExtra("question_id",homePage.getQuestionInfo().getQuestionId());
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case 501:
                    ((HomePageOneCellHolder) viewHolder).userName.setText(homePage.getUserInfo().getUserName());
                    ((HomePageOneCellHolder) viewHolder).userAction.setText("发布了文章");
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setText(homePage.getArticleInfo().getArticleTitle());
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, ArticleActivity.class);
                            intent.putExtra("article_id", homePage.getArticleInfo().getArticleId());
                            mContext.startActivity(intent);
                        }
                    });
                    ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + homePage.getUserInfo().getUserAvatar(), ((HomePageOneCellHolder) viewHolder).userProfile, ImageOptions.optionsImage);
                    break;
                case 502:
                    ((HomePageOneCellHolder) viewHolder).userName.setText(homePage.getUserInfo().getUserName());
                    ((HomePageOneCellHolder) viewHolder).userAction.setText("赞同了了文章");
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setText(homePage.getArticleInfo().getArticleTitle());
                    ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + homePage.getUserInfo().getUserAvatar(), ((HomePageOneCellHolder) viewHolder).userProfile, ImageOptions.optionsImage);
                    ((HomePageOneCellHolder) viewHolder).userActionContent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, ArticleActivity.class);
                            intent.putExtra("article_id", homePage.getArticleInfo().getArticleId());
                            mContext.startActivity(intent);
                        }
                    });
                    break;
            }
        }
//        if (i + 1 == homePages.size()) {
//            onLoadMore(1);
//        }
    }

//    public void onLoadMore(int current_page) {
//
//    }

    @Override
    public int getItemCount() {
        return homePages.size();
    }

    public class HomePageTwoCellHolder extends RecyclerView.ViewHolder {

        ImageView userProfile;
        TextView userName;
        TextView userAction;
        TextView userActionContent;
        TextView userAnswerContent;
        TextView userAgreeCount;
        RelativeLayout rel_left;

        public HomePageTwoCellHolder(View itemView) {
            super(itemView);
            userProfile = (ImageView) itemView.findViewById(R.id.image_profile);
            userName = (TextView) itemView.findViewById(R.id.txt_user_name);
            userAction = (TextView) itemView.findViewById(R.id.txt_user_action);
            userActionContent = (TextView) itemView.findViewById(R.id.txt_user_action_content);
            userAgreeCount = (TextView) itemView.findViewById(R.id.txt_agree_count);
            userAnswerContent = (TextView) itemView.findViewById(R.id.txt_answer_content);
            rel_left = (RelativeLayout) itemView.findViewById(R.id.rel_left);
        }
    }

    public class HomePageOneCellHolder extends RecyclerView.ViewHolder {

        ImageView userProfile;
        TextView userName;
        TextView userAction;
        TextView userActionContent;
        RelativeLayout rel;

        public HomePageOneCellHolder(View itemView) {
            super(itemView);
            userProfile = (ImageView) itemView.findViewById(R.id.image_profile);
            userName = (TextView) itemView.findViewById(R.id.txt_user_name);
            userAction = (TextView) itemView.findViewById(R.id.txt_user_action);
            userActionContent = (TextView) itemView.findViewById(R.id.txt_user_action_content);
            rel = (RelativeLayout) itemView.findViewById(R.id.rel);
        }
    }
}

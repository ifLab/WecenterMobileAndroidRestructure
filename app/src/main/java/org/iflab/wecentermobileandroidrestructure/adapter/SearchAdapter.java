package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.ArticleActivity;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.activity.QuestionDetailActivity;
import org.iflab.wecentermobileandroidrestructure.activity.SearchActivity;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchArticles;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchBase;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchQuestions;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchTopics;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchUsers;
import org.iflab.wecentermobileandroidrestructure.model.homepage.HomePage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lyn on 15/9/14.
 */
public class SearchAdapter extends RecyclerView.Adapter{
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<SearchBase> searchList;
    private static int ARTICLES = 1;
    private static int QUESTIONS = 2;
    private static int TOPICS = 3;
    private static int USERS = 4;

    @Override
    public int getItemViewType(int position) {
        SearchBase searchBase = searchList.get(position);
        if (searchBase instanceof SearchArticles) {
            return ARTICLES;
        }else if(searchBase instanceof SearchQuestions) {
            return QUESTIONS;
        }else if(searchBase instanceof SearchTopics) {
            return TOPICS;
        }else if(searchBase instanceof SearchUsers){
            return USERS;
        }else
            return 0;
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public SearchAdapter(Context mContext, List<SearchBase> searchList) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.searchList = searchList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ARTICLES) {
            return new ArticlesCellHolder(mLayoutInflater.inflate(R.layout.aricles_search_cell, parent, false));
        }else if(viewType == QUESTIONS) {
            return new QuestionsCellHolder(mLayoutInflater.inflate(R.layout.question_search_cell, parent, false));
        }else if(viewType == TOPICS) {
            return new TopicsCellHolder(mLayoutInflater.inflate(R.layout.topics_search_cell, parent, false));
        }else if(viewType == USERS){
            return new UsersCellHolder(mLayoutInflater.inflate(R.layout.users_search_cell, parent, false));
        }else {
            Log.e("type","type获取失败");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchBase searchBase = searchList.get(position);
        if (searchBase instanceof SearchArticles) {
            final SearchArticles articles = (SearchArticles)searchBase;
            ((ArticlesCellHolder)holder).txtArticleView.setText(articles.getName());
            ((ArticlesCellHolder)holder).rlAricles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleActivity.openArticle(mContext,articles.getSearch_id());

                }
            });
        }else if(searchBase instanceof SearchQuestions) {
            final SearchQuestions searchQuestions = (SearchQuestions)searchBase;
            ((QuestionsCellHolder) holder).txtQuestionView.setText(searchQuestions.getName());
            ((QuestionsCellHolder) holder).rlQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionDetailActivity.openQuestionDetail(mContext,searchQuestions.getUid(),searchQuestions.getSearch_id());
                }
            });
        }else if(searchBase instanceof SearchTopics) {
            SearchTopics searchTopics = (SearchTopics)searchBase;
            ((TopicsCellHolder)holder).txtTopicView.setText(searchTopics.getName());
//            ((TopicsCellHolder)holder).rlTopics.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }else if(searchBase instanceof SearchUsers){
            final SearchUsers searchUsers = (SearchUsers)searchBase;
            ((UsersCellHolder)holder).txtUserName.setText(searchUsers.getName());
            ((UsersCellHolder)holder).txtSignature.setText(searchUsers.getDetail().getSignature());
            ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + searchUsers.getDetail().getAvatar_file(),((UsersCellHolder)holder).imageUser);
                    ((UsersCellHolder) holder).rlUsers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalCenterActivity.openPersonalCenter(mContext,searchUsers.getUid());
                }
            });
        }
    }

    public static class ArticlesCellHolder extends RecyclerView.ViewHolder{
        RelativeLayout rlAricles;
        TextView txtArticleView;
        public ArticlesCellHolder(View itemView) {
            super(itemView);
            txtArticleView = (TextView)itemView.findViewById(R.id.txt_aricle);
            rlAricles = (RelativeLayout)itemView.findViewById(R.id.rl_aricles);
        }
    }

    public static class QuestionsCellHolder extends RecyclerView.ViewHolder{
        TextView txtQuestionView;
        RelativeLayout rlQuestion;
        public QuestionsCellHolder(View itemView) {
            super(itemView);
            txtQuestionView = (TextView)itemView.findViewById(R.id.txt_question);
            rlQuestion = (RelativeLayout)itemView.findViewById(R.id.rl_question);
        }
    }

    public static class TopicsCellHolder extends RecyclerView.ViewHolder{
        TextView txtTopicView;
        RelativeLayout rlTopics;
        public TopicsCellHolder(View itemView) {
            super(itemView);
            txtTopicView = (TextView)itemView.findViewById(R.id.txt_topic);
            rlTopics = (RelativeLayout)itemView.findViewById(R.id.rl_topics);
        }
    }

    public static class UsersCellHolder extends RecyclerView.ViewHolder{
        TextView txtUserName;
        TextView txtSignature;
        RelativeLayout rlUsers;
        CircleImageView imageUser;
        public UsersCellHolder(View itemView) {
            super(itemView);
            txtUserName = (TextView)itemView.findViewById(R.id.txt_user_name);
            txtSignature = (TextView)itemView.findViewById(R.id.txt_user_signature);
            rlUsers = (RelativeLayout)itemView.findViewById(R.id.rl_users);
            imageUser = (CircleImageView)itemView.findViewById(R.id.image_users);
        }
    }

    public void clear(){
        searchList.clear();
        notifyDataSetChanged();
    }

}

package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.common.OnClickItemCallBack;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleComment;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleComment.UserInfo;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyn on 15/8/1.
 */
public class ArticleCommentsAdapter extends RecyclerView.Adapter<ArticleCommentsAdapter.CommentsViewHolder>{
    List<ArticleComment> ArticleCommentList = new ArrayList<>();
    Context context;
    LayoutInflater mLayoutInflater;
    OnClickItemCallBack callBack;

    public ArticleCommentsAdapter(Context context,List<ArticleComment> ArticleCommentList,OnClickItemCallBack callBack){
        this.ArticleCommentList = ArticleCommentList;
        this.context = context;
        this.callBack = callBack;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public ArticleCommentsAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsViewHolder(mLayoutInflater.inflate(R.layout.item_comments_listview, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticleCommentsAdapter.CommentsViewHolder holder, int position) {
        ArticleComment articleComment = ArticleCommentList.get(position);
        holder.commentTextView.setText(articleComment.getMessage());

        final UserInfo userinfo = articleComment.getUser_info();

        if(userinfo != null) {
            holder.userNameTextView.setText(userinfo.getUser_name());
            ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + userinfo.getAvatar_file(), holder.profileImageView, ImageOptions.optionsImagePersonalDetailAvatar);

            holder.profileImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PersonalCenterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", userinfo.getUid());
                    intent.putExtra("bundle", bundle);
                    context.startActivity(intent);
                }
            });

            holder.answerRel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.clickItemCallBack(userinfo.getUser_name(), userinfo.getUid());
                }
            });
        }else{
            Log.e("userinfo","userinfo is null");
        }
    }

    @Override
    public int getItemCount() {
        return ArticleCommentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder{

        ImageView profileImageView;
        TextView userNameTextView;
        TextView commentTextView;
        RelativeLayout answerRel;

        public CommentsViewHolder(View itemView) {
            super(itemView);

            profileImageView = (ImageView)itemView.findViewById(R.id.image_profile);
            userNameTextView = (TextView)itemView.findViewById(R.id.txt_user_name);
            commentTextView = (TextView)itemView.findViewById(R.id.txt_user_comment);
            answerRel = (RelativeLayout)itemView.findViewById(R.id.rel_answer);
        }
    }
}

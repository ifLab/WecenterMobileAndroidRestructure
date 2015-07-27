package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.question.CommentInfo;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyn on 15/7/23.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{
    List<CommentInfo> commentInfoList = new ArrayList<>();
    Context context;
    LayoutInflater mLayoutInflater;

    public CommentsAdapter(Context context,List<CommentInfo> commentInfoList){
        this.commentInfoList = commentInfoList;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsViewHolder(mLayoutInflater.inflate(R.layout.item_comments_listview, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.CommentsViewHolder holder, int position) {
        CommentInfo commentInfo = commentInfoList.get(position);
        holder.commentTextView.setText(commentInfo.getContent());
        holder.userNameTextView.setText(commentInfo.getUser_name());
//        ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + commentInfo.get(), holder.profileImageView, ImageOptions.optionsImage);
        /**
         * http://we.bistu.edu.cn/api/answer_comment.php?id=7  没有头像地址！！
         */



    }

    @Override
    public int getItemCount() {
        return commentInfoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder{

        ImageView profileImageView;
        TextView userNameTextView;
        TextView commentTextView;

        public CommentsViewHolder(View itemView) {
            super(itemView);

            profileImageView = (ImageView)itemView.findViewById(R.id.image_profile);
            userNameTextView = (TextView)itemView.findViewById(R.id.txt_user_name);
            commentTextView = (TextView)itemView.findViewById(R.id.txt_user_comment);
        }
    }
}

package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;

/**
 * Created by hcjcch on 15/7/8.
 */
public class FoundAdapter extends RecyclerView.Adapter {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;

    public FoundAdapter(Context mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FoundHolder(mLayoutInflater.inflate(R.layout.item_found, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class FoundHolder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView txt_user_name;
        TextView txt_user_action_content;
        TextView txt_cell_type_question_or_article;
        TextView txt_cell_type_answer;
        TextView txt_answer_user_name;
        ImageView profile_image_answer;
        TextView txt_answer;

        public FoundHolder(View itemView) {
            super(itemView);
            profile_image = (ImageView) itemView.findViewById(R.id.image_profile);
            txt_user_name = (TextView) itemView.findViewById(R.id.txt_user_name);
            txt_user_action_content = (TextView) itemView.findViewById(R.id.txt_user_action_content);
            txt_cell_type_question_or_article = (TextView) itemView.findViewById(R.id.txt_cell_type_question_or_article);
            txt_cell_type_answer = (TextView) itemView.findViewById(R.id.txt_answer_user_name);
            txt_answer_user_name = (TextView) itemView.findViewById(R.id.txt_answer_user_name);
            profile_image_answer = (ImageView) itemView.findViewById(R.id.profile_image_answer);
            txt_answer = (TextView) itemView.findViewById(R.id.txt_answer);
        }

    }
}

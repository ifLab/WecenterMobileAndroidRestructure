package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PersonalCenterActivity;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalArticle;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalFollowing;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalQuestion;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/8 20:47
 */

public class PersonalFollowingAdapter extends RecyclerView.Adapter {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<PersonalFollowing.RowsEntity> datas;

    public PersonalFollowingAdapter(Context mContext, List<PersonalFollowing.RowsEntity> datas) {
        this.mContext = mContext;
        this.datas = datas;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<PersonalFollowing.RowsEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalFollowingHolder(mLayoutInflater.inflate(R.layout.item_personal_following, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PersonalFollowing.RowsEntity rowsEntity = datas.get(position);
        ((PersonalFollowingHolder) holder).txt_user_name.setText(rowsEntity.getUser_name());
        ((PersonalFollowingHolder) holder).txt_signature.setText(rowsEntity.getSignature());
        ((PersonalFollowingHolder) holder).avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalCenterActivity.openPersonalCenter(mContext, Integer.parseInt(rowsEntity.getUid()));
            }
        });
        ((PersonalFollowingHolder) holder).rel_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalCenterActivity.openPersonalCenter(mContext, Integer.parseInt(rowsEntity.getUid()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class PersonalFollowingHolder extends RecyclerView.ViewHolder {

        private CircleImageView avatar;
        private TextView txt_user_name;
        private TextView txt_signature;
        private RelativeLayout rel_right;

        public PersonalFollowingHolder(View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            txt_user_name = (TextView) itemView.findViewById(R.id.txt_user_name);
            txt_signature = (TextView) itemView.findViewById(R.id.txt_signature);
            rel_right = (RelativeLayout) itemView.findViewById(R.id.rel_right);
        }
    }
}
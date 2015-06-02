package org.iflab.wecentermobileandroidrestructure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.model.homepage.HomePage;

import java.util.List;

/**
 * Created by hcjcch on 15/5/19.
 */

public class HomePageAdapter extends BaseAdapter {
    private Context context;
    private List<HomePage> homePages;
    private static final int ONE_CELL = 1;
    private static final int TWO_CELL = 2;

    public HomePageAdapter(Context context, List<HomePage> homePages) {
        this.context = context;
        this.homePages = homePages;
    }

    @Override
    public int getCount() {
        return homePages.size();
    }

    @Override
    public Object getItem(int position) {
        return homePages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }

    @Override
    public int getItemViewType(int position) {
        int userAction = homePages.get(position).getAssociateAction();
        if (userAction == 201 || userAction == 204) {
            return TWO_CELL;
        } else {
            return ONE_CELL;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int userAction = homePages.get(position).getAssociateAction();
        int cellType = getItemViewType(position);
        switch (cellType) {
            case TWO_CELL:
                ViewHolderTwoCell viewHolderTwoCell;
                if (convertView == null) {
                    viewHolderTwoCell = new ViewHolderTwoCell();
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_page_two_cell, parent, false);
                    viewHolderTwoCell.userProfile = (ImageView) convertView.findViewById(R.id.profile_image);
                    viewHolderTwoCell.userName = (TextView) convertView.findViewById(R.id.txt_user_name);
                    viewHolderTwoCell.userAction = (TextView) convertView.findViewById(R.id.txt_user_action);
                    viewHolderTwoCell.userActionContent = (TextView) convertView.findViewById(R.id.txt_user_action_content);
                    viewHolderTwoCell.userAgreeCount = (TextView) convertView.findViewById(R.id.txt_agree_count);
                    viewHolderTwoCell.userAnswerContent = (TextView) convertView.findViewById(R.id.txt_answer_content);
                    convertView.setTag(viewHolderTwoCell);
                } else {
                    viewHolderTwoCell = (ViewHolderTwoCell) convertView.getTag();
                }
                switch (userAction) {
                    case 201:
                        viewHolderTwoCell.userName.setText(homePages.get(position).getUserInfo().getUserName());
                        viewHolderTwoCell.userAction.setText("回答了问题");
                        viewHolderTwoCell.userActionContent.setText(homePages.get(position).getQuestionInfo().getQuestionContent());
                        // viewHolderTwoCell.userAnswerContent.setText(homePages.get(position).getAnswerInfo().getAnswerContent());
                        break;
                    case 204:
                        viewHolderTwoCell.userName.setText(homePages.get(position).getUserInfo().getUserName());
                        viewHolderTwoCell.userAction.setText("赞同了回答");
                        viewHolderTwoCell.userActionContent.setText(homePages.get(position).getQuestionInfo().getQuestionContent());
                        viewHolderTwoCell.userAnswerContent.setText(homePages.get(position).getAnswerInfo().getAnswerContent());
                        break;
                }
                break;
            case ONE_CELL:
                ViewHolderTwoCell viewHolderOneCell;
                if (convertView == null) {
                    viewHolderOneCell = new ViewHolderTwoCell();
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_page_one_cell, parent, false);
                    viewHolderOneCell.userProfile = (ImageView) convertView.findViewById(R.id.profile_image);
                    viewHolderOneCell.userName = (TextView) convertView.findViewById(R.id.txt_user_name);
                    viewHolderOneCell.userAction = (TextView) convertView.findViewById(R.id.txt_user_action);
                    viewHolderOneCell.userActionContent = (TextView) convertView.findViewById(R.id.txt_user_action_content);
                    convertView.setTag(viewHolderOneCell);
                } else {
                    viewHolderOneCell = (ViewHolderTwoCell) convertView.getTag();

                }
                switch (userAction) {
                    case 101:
                        viewHolderOneCell.userName.setText(homePages.get(position).getUserInfo().getUserName());
                        viewHolderOneCell.userAction.setText("发布了问题");
                        viewHolderOneCell.userActionContent.setText(homePages.get(position).getQuestionInfo().getQuestionContent());
                        break;
                    case 105:
                        viewHolderOneCell.userName.setText(homePages.get(position).getUserInfo().getUserName());
                        viewHolderOneCell.userAction.setText("关注了问题");
                        viewHolderOneCell.userActionContent.setText(homePages.get(position).getQuestionInfo().getQuestionContent());
                        break;
                    case 501:
                        viewHolderOneCell.userName.setText(homePages.get(position).getUserInfo().getUserName());
                        viewHolderOneCell.userAction.setText("发布了文章");
                        viewHolderOneCell.userActionContent.setText(homePages.get(position).getArticleInfo().getArticleTitle());
                        break;
                    case 502:
                        viewHolderOneCell.userName.setText(homePages.get(position).getUserInfo().getUserName());
                        viewHolderOneCell.userAction.setText("赞同了了文章");
                        viewHolderOneCell.userActionContent.setText(homePages.get(position).getArticleInfo().getArticleTitle());
                        break;
                }
                break;
        }
        return convertView;
    }

//    static class ViewHolderOneCell {
//        ImageView userProfile;
//        TextView userName;
//        TextView userAction;
//        TextView userActionContent;
//    }

    static class ViewHolderTwoCell {
        ImageView userProfile;
        TextView userName;
        TextView userAction;
        TextView userActionContent;
        TextView userAnswerContent;
        TextView userAgreeCount;
    }
}
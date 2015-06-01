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
                        break;
                    case 204:

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
                        break;
                    case 105:

                        break;
                    case 501:

                        break;
                    case 502:

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
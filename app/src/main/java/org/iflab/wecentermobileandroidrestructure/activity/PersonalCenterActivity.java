package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;

/**
 * Created by hcjcch on 15/5/21.
 */
public class PersonalCenterActivity extends BaseActivity{
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView ask;
    private TextView askCount;
    private TextView answer;
    private TextView answerCount;
    private TextView article;
    private TextView articleCount;
    private TextView topic;
    private TextView topicCount;
    private TextView atteneion;
    private TextView attentionCount;
    private TextView follower;
    private TextView followerCount;
    private ImageView answerFavorite;
    private TextView answerFavoriteCount;
    private ImageView agree;
    private TextView agreeCount;
    private ImageView thanks;
    private TextView thanksCount;
    private ImageView hasFocus;
    private TextView hasFocusCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        findViews();
        setViews();
    }

    private void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swi_personal_center);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.in_answer_count);
        askCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        ask = (TextView)relativeLayout.findViewById(R.id.txt_ask);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_answer_count);
        answer = (TextView) relativeLayout.findViewById(R.id.txt_ask);
        answerCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_article_count);
        article = (TextView) relativeLayout.findViewById(R.id.txt_ask);
        articleCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_topic_count);
        topic = (TextView) relativeLayout.findViewById(R.id.txt_ask);
        topicCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_attention_count);
        atteneion = (TextView) relativeLayout.findViewById(R.id.txt_ask);
        attentionCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_follower_count);
        follower = (TextView) relativeLayout.findViewById(R.id.txt_ask);
        followerCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        relativeLayout = (RelativeLayout)findViewById(R.id.in_answer_favorite);
        answerFavorite = (ImageView)relativeLayout.findViewById(R.id.img_answer_love);
        answerFavoriteCount = (TextView)relativeLayout.findViewById(R.id.txt_answer_love_count);
        relativeLayout = (RelativeLayout)findViewById(R.id.in_agree);
        agree = (ImageView)relativeLayout.findViewById(R.id.img_answer_love);
        agreeCount = (TextView)relativeLayout.findViewById(R.id.txt_answer_love_count);
        relativeLayout = (RelativeLayout)findViewById(R.id.in_thanks);
        thanks = (ImageView)relativeLayout.findViewById(R.id.img_answer_love);
        thanksCount = (TextView)relativeLayout.findViewById(R.id.txt_answer_love_count);
        relativeLayout = (RelativeLayout)findViewById(R.id.in_focus);
        hasFocus = (ImageView)relativeLayout.findViewById(R.id.img_answer_love);
        hasFocusCount = (TextView)relativeLayout.findViewById(R.id.txt_answer_love_count);
    }

    private void setViews() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.YELLOW,Color.GREEN,Color.RED);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        ask.setText("提问");
        askCount.setText("4");
        answer.setText("回答");
        answerCount.setText("0");
        article.setText("文章");
        articleCount.setText("5");
        topic.setText("话题");
        topicCount.setText("11");
        atteneion.setText("关注中");
        attentionCount.setText("4");
        follower.setText("追随者");
        followerCount.setText("1");
        answerFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.love_icon));
        answerFavoriteCount.setText("0");
        agree.setImageDrawable(getResources().getDrawable(R.mipmap.like_icon));
        agreeCount.setText("0");
        thanks.setImageDrawable(getResources().getDrawable(R.mipmap.star_icon));
        thanksCount.setText("0");
        hasFocus.setImageDrawable(getResources().getDrawable(R.mipmap.tick_icon));
        hasFocusCount.setText("0");
    }
}
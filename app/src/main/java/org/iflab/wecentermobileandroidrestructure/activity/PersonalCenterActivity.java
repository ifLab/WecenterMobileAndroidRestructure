package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.personal.UserPersonal;
import org.iflab.wecentermobileandroidrestructure.tools.HawkControl;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;
import org.json.JSONException;
import org.json.JSONObject;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by hcjcch on 15/5/21.
 */

public class PersonalCenterActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView userImage;
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
    private int uid;
    private TextView useredit;
    private Bundle bundle;
    private boolean isOwner;
    private RelativeLayout rel_marz;
    private RelativeLayout relContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        uid = bundle.getInt("uid", -1);
        isOwner = bundle.getBoolean("isOwner");
        findViews();
        setViews();
        setToolBar();
        if (uid != -1) {
            loadData();
        } else {
            //TODO 用户错误
        }
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#00ffffff"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("个人中心");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
    }

    private void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swi_personal_center);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.in_answer_count);
        askCount = (TextView) relativeLayout.findViewById(R.id.txt_ask_count);
        ask = (TextView) relativeLayout.findViewById(R.id.txt_ask);
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
        relativeLayout = (RelativeLayout) findViewById(R.id.in_answer_favorite);
        answerFavorite = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
        answerFavoriteCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_agree);
        agree = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
        agreeCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_thanks);
        thanks = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
        thanksCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
        relativeLayout = (RelativeLayout) findViewById(R.id.in_focus);
        hasFocus = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
        hasFocusCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
        useredit = (TextView) findViewById(R.id.txt_user_edit);
        rel_marz = (RelativeLayout) findViewById(R.id.rel_marz);
        relContainer = (RelativeLayout) findViewById(R.id.rel);
        userImage = (ImageView) findViewById(R.id.img_user);
    }

    private void setViews() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        useredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, PersonalCenterEditActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wencenter, menu);
        return true;
    }

    private void loadData() {
        AsyncHttpWecnter.get(RelativeUrl.USER_INFO, getParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                relContainer.setEnabled(false);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(json);
                if (jsonProgress) return;
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONObject rsm = jsonObject.getJSONObject("rsm");
                    UserPersonal user = new UserPersonal(rsm);
                    HawkControl.saveUserCount(user);
                    setData(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("failure");
            }

            @Override
            public void onFinish() {
                rel_marz.setVisibility(View.GONE);
                relContainer.setEnabled(true);
                swipeRefreshLayout.setRefreshing(false);
                super.onFinish();
            }
        });
    }

    private RequestParams getParams() {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        return params;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        swipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    private void setData(UserPersonal user) {
        answerFavoriteCount.setText(user.getAnswer_favorite_count() + "");
        agreeCount.setText(user.getAgree_count() + "");
        thanksCount.setText(user.getThanks_count() + "");
        hasFocusCount.setText(user.getHas_focus() + "");
        askCount.setText(user.getQuestion_count() + "");
        answerCount.setText(user.getAnswer_count() + "");
        articleCount.setText(user.getArticle_count() + "");
        topicCount.setText(user.getTopic_focus_count() + "");
        attentionCount.setText(user.getFriend_count() + "");
        followerCount.setText(user.getFans_count() + "");
        ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + user.getAvatar_file(), userImage, ImageOptions.optionsImagePersonalDetailAvatar);

    }
}
package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.personal.Person;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalFollowing;
import org.iflab.wecentermobileandroidrestructure.model.personal.UserPersonal;
import org.iflab.wecentermobileandroidrestructure.tools.HawkControl;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hcjcch on 15/5/21.
 */

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener{
    public static final int EDIT = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView txt_motto;
    private TextView txt_user_name;
    private ImageView userImage;
    private TextView ask;
    private TextView askCount;
    private TextView answer;
    private TextView answerCount;
    private TextView article;
    private TextView articleCount;
    private TextView topic;
    private TextView topicCount;
    private TextView attention;
    private TextView attentionCount;
    private TextView follower;
    private TextView followerCount;
//    private ImageView answerFavorite;
//    private TextView answerFavoriteCount;
//    private ImageView agree;
//    private TextView agreeCount;
//    private ImageView thanks;
//    private TextView thanksCount;
//    private ImageView hasFocus;
//    private TextView hasFocusCount;
    private int uid;
    private TextView useredit;
    private boolean isOwner;
    private RelativeLayout in_ask_count, in_answer_count, in_article_count, in_topic_count, in_attention_count, in_follower_count;

    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", -1);
        isOwner = isMe(uid, User.getLoginUser(getApplicationContext()).getUid());
        findViews();

        setToolBar();
        if (uid != -1) {
            loadData();
        } else {
            //TODO 用户错误
        }


    }

    public static void openPersonalCenter(Context context, int uid) {
        Intent intent = new Intent();
        intent.putExtra("uid",uid);
        intent.setClass(context, PersonalCenterActivity.class);
        context.startActivity(intent);
    }

    private boolean isMe(int currentUid, int sqlUid) {
        return currentUid == sqlUid;
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#00ffffff"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("个人中心");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swi_personal_center);

        in_ask_count = (RelativeLayout) findViewById(R.id.in_ask_count);
        in_ask_count.setOnClickListener(this);
        askCount = (TextView) in_ask_count.findViewById(R.id.txt_ask_count);
        ask = (TextView) in_ask_count.findViewById(R.id.txt_ask);

        in_answer_count = (RelativeLayout) findViewById(R.id.in_answer_count);
        in_answer_count.setOnClickListener(this);
        answer = (TextView) in_answer_count.findViewById(R.id.txt_ask);
        answerCount = (TextView) in_answer_count.findViewById(R.id.txt_ask_count);

        in_article_count = (RelativeLayout) findViewById(R.id.in_article_count);
        in_article_count.setOnClickListener(this);
        article = (TextView) in_article_count.findViewById(R.id.txt_ask);
        articleCount = (TextView) in_article_count.findViewById(R.id.txt_ask_count);

        in_topic_count = (RelativeLayout) findViewById(R.id.in_topic_count);
        in_topic_count.setOnClickListener(this);
        topic = (TextView) in_topic_count.findViewById(R.id.txt_ask);
        topicCount = (TextView) in_topic_count.findViewById(R.id.txt_ask_count);

        in_attention_count = (RelativeLayout) findViewById(R.id.in_attention_count);
        in_attention_count.setOnClickListener(this);
        attention = (TextView) in_attention_count.findViewById(R.id.txt_ask);
        attentionCount = (TextView) in_attention_count.findViewById(R.id.txt_ask_count);

        in_follower_count = (RelativeLayout) findViewById(R.id.in_follower_count);
        in_follower_count.setOnClickListener(this);
        follower = (TextView) in_follower_count.findViewById(R.id.txt_ask);
        followerCount = (TextView) in_follower_count.findViewById(R.id.txt_ask_count);


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.in_answer_favorite);
//        answerFavorite = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
//        answerFavoriteCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
//        relativeLayout = (RelativeLayout) findViewById(R.id.in_agree);
//        agree = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
//        agreeCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
//        relativeLayout = (RelativeLayout) findViewById(R.id.in_thanks);
//        thanks = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
//        thanksCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
//        relativeLayout = (RelativeLayout) findViewById(R.id.in_focus);
//        hasFocus = (ImageView) relativeLayout.findViewById(R.id.img_answer_love);
//        hasFocusCount = (TextView) relativeLayout.findViewById(R.id.txt_answer_love_count);
        useredit = (TextView) findViewById(R.id.txt_user_edit);
        userImage = (ImageView) findViewById(R.id.img_user);
        txt_motto = (TextView) findViewById(R.id.txt_motto);
        txt_user_name = (TextView) findViewById(R.id.txt_user_name);
    }

    private void setViews() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        if(isOwner){
            useredit.setText("修改信息");
            useredit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PersonalCenterActivity.this, PersonalCenterEditActivity.class);
                    intent.putExtra("uid", uid);
                    startActivityForResult(intent, EDIT);
                }
            });
        }else {
            useredit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestParams params = new RequestParams();
                    params.put("uid",uid);
                    AsyncHttpWecnter.loadData(getApplicationContext(), RelativeUrl.FOLLOW_PEOPLE, params, AsyncHttpWecnter.Request.Post, new NetWork() {
                        @Override
                        public void parseJson(JSONObject response) {
                            try {
                                if(response.getString("type").equals("remove")){
                                    useredit.setText("关注");
                                }else if(response.getString("type").equals("add")){
                                    useredit.setText("已关注");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            });
        }
        ask.setText("提问");
        answer.setText("回答");
        article.setText("文章");
        topic.setText("话题");
        attention.setText("关注中");
        follower.setText("追随者");
//        answerFavorite.setImageDrawable(getResources().getDrawable(R.mipmap.love_icon));
//        answerFavoriteCount.setText("0");
//        agree.setImageDrawable(getResources().getDrawable(R.mipmap.like_icon));
//        agreeCount.setText("0");
//        thanks.setImageDrawable(getResources().getDrawable(R.mipmap.star_icon));
//        thanksCount.setText("0");
//        hasFocus.setImageDrawable(getResources().getDrawable(R.mipmap.tick_icon));
//        hasFocusCount.setText("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wencenter, menu);
        return true;
    }

    private void loadData() {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("mobile_sign", MD5Transform.MD5("account"+AsyncHttpWecnter.SIGN));

        AsyncHttpWecnter.get(RelativeUrl.USER_INFO, params, new AsyncHttpResponseHandler() {
            
            @Override
            public void onStart() {
                super.onStart();
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                person = gson.fromJson(new String(responseBody), Person.class);
                if(person.getRsm()!= null){
                    if(isOwner){
                        if(User.getLoginUser(getApplicationContext()).getSignNature() == null){
                            User.setOwnerSign(getApplicationContext(),person.getRsm().getSignature());
                        }
                    }
                    txt_motto.setText(person.getRsm().getSignature());
                    txt_user_name.setText(person.getRsm().getUser_name());

//                    answerFavoriteCount.setText(person.getRsm().getAnswer_favorite_count() + "");
//                    agreeCount.setText(person.getRsm().getAgree_count() + "");
//                    thanksCount.setText(person.getRsm().getThanks_count() + "");
                    askCount.setText(person.getRsm().getQuestion_count() + "");
                    answerCount.setText(person.getRsm().getAnswer_count() + "");
                    articleCount.setText(person.getRsm().getArticle_count() + "");
                    topicCount.setText(person.getRsm().getTopic_focus_count() + "");
                    attentionCount.setText(person.getRsm().getFriend_count() + "");
                    followerCount.setText(person.getRsm().getFans_count() + "");
                    ImageLoader.getInstance().displayImage(person.getRsm().getAvatar_file(), userImage, ImageOptions.optionsImagePersonalDetailAvatar);

                    if(person.getRsm().getHas_focus()== 1){
                        useredit.setText("已关注");

                    }else if(person.getRsm().getHas_focus()== 0){
                        useredit.setText("关注");
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("failure");
            }

            @Override
            public void onFinish() {
                swipeRefreshLayout.setRefreshing(false);
                setViews();
                super.onFinish();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.in_ask_count:
                    intent.setClass(PersonalCenterActivity.this, PersonalQuestionActivity.class);
                    intent.putExtra("userName", person.getRsm().getUser_name());
                    intent.putExtra("uid", uid);
                    intent.putExtra("avatar", person.getRsm().getAvatar_file());
                    startActivity(intent);
                    break;
                case R.id.in_article_count:
                    intent.setClass(PersonalCenterActivity.this, PersonalArticleActivity.class);
                    intent.putExtra("userName", person.getRsm().getUser_name());
                    intent.putExtra("uid", uid);
                    intent.putExtra("avatar", person.getRsm().getAvatar_file());
                    startActivity(intent);
                    break;
                case R.id.in_answer_count:
                    intent.setClass(PersonalCenterActivity.this, PersonalAnswerActivity.class);
                    intent.putExtra("userName", person.getRsm().getUser_name());
                    intent.putExtra("uid", uid);
                    intent.putExtra("avatar", person.getRsm().getAvatar_file());
                    intent.putExtra("sign", person.getRsm().getSignature());
                    startActivity(intent);
                    break;
                case R.id.in_topic_count:
                    intent.setClass(PersonalCenterActivity.this, PersonalTopicActivity.class);
                    intent.putExtra("userName", person.getRsm().getUser_name());
                    intent.putExtra("uid", uid);
                    intent.putExtra("avatar", person.getRsm().getAvatar_file());
                    startActivity(intent);
                    break;
                case R.id.in_attention_count:
                    intent.setClass(PersonalCenterActivity.this, PersonalFollowingActivity.class);
                    intent.putExtra("userName", person.getRsm().getUser_name());
                    intent.putExtra("uid", uid);
                    intent.putExtra("type", PersonalFollowingActivity.FOLLOWING);
                    startActivity(intent);
                    break;
                case R.id.in_follower_count:
                    intent.setClass(PersonalCenterActivity.this, PersonalFollowingActivity.class);
                    intent.putExtra("userName", person.getRsm().getUser_name());
                    intent.putExtra("uid", uid);
                    intent.putExtra("type", PersonalFollowingActivity.FAN);
                    startActivity(intent);
                    break;
            }
        }
    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT) {
            if (resultCode == RESULT_OK) {
                swipeRefreshLayout.setRefreshing(true);
                loadData();
            }
        }
    }
}
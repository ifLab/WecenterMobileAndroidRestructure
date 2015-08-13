package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.question.AnswerInfo;
import org.iflab.wecentermobileandroidrestructure.tools.Global;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionAnswerActivity extends BaseActivity implements View.OnClickListener{

    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    CircleImageView circleImageView;
    TextView usernameTextView;
    WebView contentWebView;
    TextView votesTextView;
    TextView signatureTextView;
    TextView addTimeTextView;
    ImageButton shareBtn;
    ImageButton commentBtn;
    CheckBox likeCheckBox;
    CheckBox dislikeCheckBox;
    RelativeLayout topRel;
    int answerID = -1;
    String questionTitle;
    int uid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        answerID = getIntent().getIntExtra("answer_id", -1);
        questionTitle = getIntent().getStringExtra("question_title");

        findViews();
        setViews();
        setToolBars();
        loadData();
        setListenter();

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipyrefreshlayout);
        circleImageView = (CircleImageView)findViewById(R.id.image_profile);
        usernameTextView = (TextView)findViewById(R.id.txt_user_name);
        contentWebView = (WebView)findViewById(R.id.webv_content);
        votesTextView = (TextView)findViewById(R.id.txt_votes);
        signatureTextView = (TextView)findViewById(R.id.txt_user_signature);
        addTimeTextView = (TextView)findViewById(R.id.txt_add_time);
        shareBtn = (ImageButton) findViewById(R.id.btn_share);
        commentBtn = (ImageButton)findViewById(R.id.btn_comment);
        likeCheckBox = (CheckBox)findViewById(R.id.check_like);
        dislikeCheckBox = (CheckBox)findViewById(R.id.check_dislike);
        topRel = (RelativeLayout)findViewById(R.id.rel_top);
    }

    private void setViews() {
        refreshLayout.setEnabled(false);
    }

    private void setListenter() {
        likeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            RequestParams params = new RequestParams();
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                dislikeCheckBox.setEnabled(!b);

                if(!params.has("answer_id")) {
                    params.put("answer_id", answerID);
                    params.put("value", 1);
                }
                AsyncHttpWecnter.loadData(QuestionAnswerActivity.this, RelativeUrl.ANSWER_VOTE, params, AsyncHttpWecnter.Request.Post, new NetWork() {
                    @Override
                    public void parseJson(JSONObject response) {

                    }
                });

            }
        });

        dislikeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            RequestParams params = new RequestParams();
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                likeCheckBox.setEnabled(!b);

                if(!params.has("answer_id")) {
                    params.put("answer_id", answerID);
                    params.put("value", -1);
                }

                AsyncHttpWecnter.loadData(QuestionAnswerActivity.this, RelativeUrl.ANSWER_VOTE, params, AsyncHttpWecnter.Request.Post, new NetWork() {
                    @Override
                    public void parseJson(JSONObject response) {

                    }
                });

            }
        });

        topRel.setOnClickListener(this);
    }

    public void gotoShare(View view){

    }

    public void gotoComment(View view){
        Intent intent = new Intent(QuestionAnswerActivity.this,AnswerCommentActivity.class);
        intent.putExtra("answer_id",answerID);
        startActivity(intent);
    }

    private void setToolBars() {
        toolbar.setTitle(questionTitle);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        AsyncHttpWecnter.loadData(QuestionAnswerActivity.this, RelativeUrl.QUESTION_ANSWER_INFO, setParams(), AsyncHttpWecnter.Request.Get, new NetWork() {
            AnswerInfo answerInfo;

            @Override
            public void parseJson(JSONObject response) {
                Gson gson = new Gson();

                answerInfo = gson.fromJson(response.toString(), AnswerInfo.class);
                signatureTextView.setText(answerInfo.getSignature());
                usernameTextView.setText(answerInfo.getUser_name());
                contentWebView.loadDataWithBaseURL("about:blank", answerInfo.getAnswer_content(), "text/html", "utf-8", null);
                contentWebView.setBackgroundColor(getResources().getColor(R.color.bg_color_grey));
                ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + answerInfo.getAvatar_file(), circleImageView, ImageOptions.optionsImage);
                if(answerInfo.getVote_value() > -1)
                    votesTextView.setText(answerInfo.getVote_value() + "");

                addTimeTextView.setVisibility(View.VISIBLE);
                addTimeTextView.setText(Global.TimeStamp2Date(answerInfo.getAdd_time(), "yyyy-MM-dd hh:mm:ss"));

                if(answerInfo.getVote_value() == 1){
                    likeCheckBox.setChecked(true);
                    dislikeCheckBox.setEnabled(false);
                }else if(answerInfo.getVote_value() == -1){
                    dislikeCheckBox.setChecked(true);
                    likeCheckBox.setEnabled(false);
                }

                uid = answerInfo.getUid();
            }
        });

    }

    private RequestParams setParams() {
        RequestParams params = new RequestParams();
        params.put("id", answerID);
        return params;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_top:
                if(uid != -1) {
                    Intent intent = new Intent(QuestionAnswerActivity.this, PersonalCenterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", uid);
                    bundle.putBoolean("isOwner", User.getLoginUser(QuestionAnswerActivity.this).getUid() == uid);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                break;

        }
    }
}

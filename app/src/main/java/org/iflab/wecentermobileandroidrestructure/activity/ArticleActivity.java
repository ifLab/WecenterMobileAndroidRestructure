package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.leakcanary.RefWatcher;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.application.WecenterApplication;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.personal.UserPersonal;
import org.iflab.wecentermobileandroidrestructure.tools.DisplayUtil;
import org.iflab.wecentermobileandroidrestructure.tools.FormHtmlAsyncTask;
import org.iflab.wecentermobileandroidrestructure.tools.HawkControl;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;
import org.iflab.wecentermobileandroidrestructure.tools.WecenterImageGetter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleActivity extends ShareBaseActivity {

    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    CircleImageView circleImageView;
    TextView userNameTextView;
    TextView contentWebView;
    TextView votesTextView;
    TextView signatureTextView;
    ImageButton shareBtn;
    ImageButton commentBtn;
    CheckBox likeCheckBox;
    CheckBox dislikeCheckBox;
    int articleID;
    int voteValue;
    public static Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        articleID = getIntent().getIntExtra("article_id", 1);

        findViews();
        setViews();
        setToolBars();
        setListenter();
        loadData();
    }



    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        circleImageView = (CircleImageView) findViewById(R.id.image_profile);
        userNameTextView = (TextView) findViewById(R.id.txt_user_name);
        contentWebView = (TextView) findViewById(R.id.webv_content);
        votesTextView = (TextView) findViewById(R.id.txt_votes);
        signatureTextView = (TextView) findViewById(R.id.txt_user_signature);
        shareBtn = (ImageButton) findViewById(R.id.btn_share);
        commentBtn = (ImageButton) findViewById(R.id.btn_comment);
        likeCheckBox = (CheckBox) findViewById(R.id.check_like);
        dislikeCheckBox = (CheckBox) findViewById(R.id.check_dislike);
    }

    public static void openArticle(Context context, int article_id) {
        if(intent == null){
            intent = new Intent();
        }
        // intent.putExtra 最终用的事ArrayMap.put 此方法可覆盖值，setClass也可覆盖
        intent.putExtra("article_id", article_id);
        intent.setClass(context, ArticleActivity.class);
        context.startActivity(intent);
        intent.setClassName("","");//清空对上下文的引用
    }

    private void setViews() {
        refreshLayout.setEnabled(false);
        contentWebView.setPadding(5, 5, 5, 5);
    }

    private void setListenter() {
        likeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            RequestParams params = new RequestParams();

            @Override
            public void onCheckedChanged(CompoundButton compoundButton,final boolean b) {
                dislikeCheckBox.setEnabled(!b);

                params.put("answer_id", articleID);
                params.put("value", 1);

                AsyncHttpWecnter.post(RelativeUrl.ANSWER_VOTE, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        voteValue = b ? voteValue + 1 : voteValue - 1;
                        votesTextView.setText(voteValue + "");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

            }
        });

        dislikeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            RequestParams params = new RequestParams();

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
                likeCheckBox.setEnabled(!b);

                params.put("answer_id", articleID);
                params.put("value", -1);

                AsyncHttpWecnter.post(RelativeUrl.ANSWER_VOTE, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        voteValue = b ? voteValue - 1 : voteValue + 1;
                        votesTextView.setText(voteValue + "");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

            }
        });
    }

    public void gotoShare(View view) {
        mController.openShare(ArticleActivity.this, false);
    }

    public void gotoComment(View view) {
        Intent intent = new Intent(ArticleActivity.this,AnswerCommentActivity.class);
        intent.putExtra("article_id", articleID);
        startActivity(intent);
    }

    private void setToolBars() {
        toolbar.setTitle("文章");//设置Toolbar标题
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
        AsyncHttpWecnter.loadData(ArticleActivity.this, RelativeUrl.ARTICLE_INFO, setParams(), AsyncHttpWecnter.Request.Get, new NetWork() {
            ArticleInfo artleInfo;

            @Override
            public void parseJson(JSONObject response) {
                Gson gson = new Gson();
                try {
                    artleInfo = gson.fromJson(response.getString("article_info"), ArticleInfo.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                userNameTextView.setText(artleInfo.getUser_name());
                (new FormHtmlAsyncTask(new WecenterImageGetter.Builder(ArticleActivity.this).padding(DisplayUtil.dip2px(ArticleActivity.this,20)).build(),contentWebView)).execute(artleInfo.getMessage());
                signatureTextView.setText(artleInfo.getSignature());
                contentWebView.setBackgroundColor(getResources().getColor(R.color.bg_color_grey));
                ImageLoader.getInstance().displayImage(artleInfo.getAvatar_file(), circleImageView, ImageOptions.optionsImage);
                toolbar.setTitle(artleInfo.getArticleTitle());

//                setShareContent(artleInfo.getArticleTitle(),"http://iflab.org");

                if (artleInfo.getVotes() > -1) {
                    voteValue = artleInfo.getVotes();
                    votesTextView.setText(voteValue + "");
                }

                if (artleInfo.getVote_value() == 1) {
                    likeCheckBox.setChecked(true);
                    dislikeCheckBox.setEnabled(false);
                } else if (artleInfo.getVote_value() == -1) {
                    dislikeCheckBox.setChecked(true);
                    likeCheckBox.setEnabled(false);
                }
            }

            @Override
            public void failure() {
                super.failure();
                votesTextView.setText(artleInfo.getVotes() + "");
            }
        });

    }

    private RequestParams setParams() {
        RequestParams params = new RequestParams();
        params.put("id",articleID);
        return params;
    }


}

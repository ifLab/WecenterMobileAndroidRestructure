package org.iflab.wecentermobileandroidrestructure.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.ArticleCommentsAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.CommentsAdapter;
import org.iflab.wecentermobileandroidrestructure.common.OnClickItemCallBack;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleComment;
import org.iflab.wecentermobileandroidrestructure.model.question.CommentInfo;
import org.iflab.wecentermobileandroidrestructure.tools.DisplayUtil;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnswerCommentActivity extends BaseActivity {
    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    int answerID;
    int articleID;
    EditText commentEdt;
    ArticleComment articleComment;
    CommentsAdapter commentsAdapter;
    ArticleCommentsAdapter articleCommentsAdapter;
    LinearLayoutManager linearLayoutManager;
    InputMethodManager inputManager;
    boolean isArticle;
    int atUid = -1;
    int drawingStartLocation;

    @Bind(R.id.contentRoot)
    FrameLayout contentRoot;

    @Bind(R.id.llAddComment)
    LinearLayout llAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_comment);

        ButterKnife.bind(this);
        overridePendingTransition(0, 0);
        answerID = getIntent().getIntExtra("answer_id", -1);
        articleID = getIntent().getIntExtra("article_id",1);

        findViews();
        setViews();
        setToolBars();

        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);
        if (savedInstanceState == null) {
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }

        if(answerID != -1){
            loadData();
            isArticle = false;
        }else if(articleID != -1){
            loadArticleData();
            isArticle = true;
        }

    }

    private void startIntroAnimation() {
        ViewCompat.setElevation(toolbar, 0);
        contentRoot.setScaleY(0.1f);
        contentRoot.setPivotY(drawingStartLocation);
        llAddComment.setTranslationY(200);

        contentRoot.animate()
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewCompat.setElevation(toolbar, DisplayUtil.dip2px(getApplicationContext(),8));
                        animateContent();
                    }
                })
                .start();
    }

    private void animateContent() {
        llAddComment.animate().translationY(0)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(200)
                .start();
    }


    private void findViews() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.list_answer_comment);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipyrefreshlayout);
        commentEdt = (EditText)findViewById(R.id.edt_comment);
    }

    private void setToolBars() {
        toolbar.setTitle("评论");
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

    private void setViews() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new ItemDivider(AnswerCommentActivity.this, R.drawable.item_divider));

        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();

            }
        });

        inputManager = (InputMethodManager)commentEdt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void commentOnClick(View view){
        String message = commentEdt.getText().toString();
        if(message.length() < 1){
            return;
        }

        RequestParams params = new RequestParams();
        String url = "";
        if(isArticle)
        {
            params.put("article_id",articleID);
            url = RelativeUrl.PUBLISH_ARTICLE;
        }else {
            url = RelativeUrl.UPLOAD_ANSWER ;
            params.put("answer_id",answerID);
        }

        if(atUid != -1){
            params.put("at_uid",atUid);
        }
        params.put("message", message);

        AsyncHttpWecnter.loadData(getApplicationContext(), url, params, AsyncHttpWecnter.Request.Post, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
//                Log.v("addAnswer", response.toString());
                commentEdt.setText("");
                refreshData();
                recyclerView.smoothScrollBy(0, recyclerView.getHeight());
                inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

    }

    private void refreshData(){
        if (isArticle) {
            loadArticleData();
        } else {
            loadData();
        }
    }

    private void loadArticleData(){
        AsyncHttpWecnter.loadData(AnswerCommentActivity.this, RelativeUrl.ARTICLE_COMMENT, setArticleParams(), AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                try {
                    if(response.getInt("total_rows") == 0){
                        showNoComment();
                    }else {
                        Gson gson = new Gson();
                        articleComment = gson.fromJson(response.toString(),ArticleComment.class);
                        articleCommentsAdapter = new ArticleCommentsAdapter(AnswerCommentActivity.this, articleComment, new OnClickItemCallBack() {
                            @Override
                            public void clickItemCallBack(String userName,int userID) {
                                atUid = userID;
                                commentEdt.setText("@" + userName + " ");
                                commentEdt.setFocusable(true);
                                commentEdt.setFocusableInTouchMode(true);
                                commentEdt.requestFocus();
                                commentEdt.setSelection(userName.length() + 2);
                                inputManager.showSoftInput(commentEdt, 0);
                            }
                        });
                        recyclerView.setAdapter(articleCommentsAdapter);
//                        Log.v("articleComments", articleComments.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void finish() {
                super.finish();
                refreshLayout.setRefreshing (false);
            }
        });

    }

    private void loadData() {

        AsyncHttpWecnter.get(RelativeUrl.ANSWER_COMMENT, setParams(), new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                CommentInfo commentsList = gson.fromJson(new String(responseBody),CommentInfo.class);

                if (commentsList.getRsm() != null) {
                    commentsAdapter = new CommentsAdapter(AnswerCommentActivity.this, commentsList.getRsm(), new OnClickItemCallBack() {
                        @Override
                        public void clickItemCallBack(String userName, int userID) {
                            atUid = userID;
                            commentEdt.setText("@" + userName + " ");
                            commentEdt.setFocusable(true);
                            commentEdt.setFocusableInTouchMode(true);
                            commentEdt.requestFocus();
                            commentEdt.setSelection(userName.length() + 2);
                            inputManager.showSoftInput(commentEdt, 0);
                        }
                    });
                    recyclerView.setAdapter(commentsAdapter);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        ViewCompat.setElevation(toolbar, 0);
        contentRoot.animate()
                .translationY(DisplayUtil.getScreenHeight(this))
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        AnswerCommentActivity.super.onBackPressed();
                    }
                })
                .start();
    }

    private void showNoComment(){
        final Snackbar snackbar = Snackbar.make(refreshLayout,"没有评论",Snackbar.LENGTH_SHORT);
        snackbar.show();
        snackbar.setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
    }

    private RequestParams setParams(){
        RequestParams params = new RequestParams();
        params.put("answer_id",answerID);
        return params;
    }
    private RequestParams setArticleParams(){
        RequestParams params = new RequestParams();
        params.put("id",articleID);
        params.put("mobile_sign", MD5Transform.MD5("article"+AsyncHttpWecnter.SIGN));
        return params;
    }


}

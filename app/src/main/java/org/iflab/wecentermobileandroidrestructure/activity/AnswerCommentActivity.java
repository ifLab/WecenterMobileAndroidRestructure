package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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
import org.iflab.wecentermobileandroidrestructure.ui.ItemDivider;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnswerCommentActivity extends AppCompatActivity {

    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    int answerID;
    int articleID;
    EditText commentEdt;
    List<CommentInfo> commentsList ;
    List<ArticleComment> articleComments;
    CommentsAdapter commentsAdapter;
    ArticleCommentsAdapter articleCommentsAdapter;
    LinearLayoutManager linearLayoutManager;
    InputMethodManager inputManager;
    boolean isArticle;
    int atUid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_comment);

        answerID = getIntent().getIntExtra("answer_id", -1);
        articleID = getIntent().getIntExtra("article_id",1);

        findViews();
        setViews();
        setToolBars();

        if(answerID != -1){
            commentsList = new ArrayList<>();
            loadData();
            isArticle = false;
        }else if(articleID != -1){
            articleComments = new ArrayList<>();
            loadArticleData();
            isArticle = true;
        }

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
            url = RelativeUrl.UPLOAD_ANSWER + "?answer_id=" + answerID;
        }

        if(atUid != -1){
            params.put("at_uid",atUid);
        }
        params.put("message", message);
        Log.v("params", params.toString());
        AsyncHttpWecnter.loadData(AnswerCommentActivity.this, url, params, AsyncHttpWecnter.Request.Post, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                Log.v("addAnswer",response.toString());
                commentEdt.setText("");
                refreshData();
                recyclerView.smoothScrollBy(0,recyclerView.getHeight());
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
                        articleComments = gson.fromJson(response.getString("rows"),
                                new TypeToken<ArrayList<ArticleComment>>() {
                                }.getType());
                        articleCommentsAdapter = new ArticleCommentsAdapter(AnswerCommentActivity.this, articleComments, new OnClickItemCallBack() {
                            @Override
                            public void clickItemCallBack(String userName,int userID) {
                                atUid = userID;
                                commentEdt.setText("@" + userName);
                                commentEdt.setFocusable(true);
                                commentEdt.setFocusableInTouchMode(true);
                                commentEdt.requestFocus();
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
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void loadData() {

        AsyncHttpWecnter.get(RelativeUrl.ANSWER_COMMENT, setParams(), new AsyncHttpResponseHandler() {
            String array;

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();

                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    Log.v("pbj", obj.toString());
                    array = obj.getString("rsm");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("array", array);
                if (array.equals("{\"total_rows\":\"0\"}")) {
                    showNoComment();
                } else {
                    commentsList = gson.fromJson(array,
                            new TypeToken<ArrayList<CommentInfo>>() {
                            }.getType());
                    commentsAdapter = new CommentsAdapter(AnswerCommentActivity.this, commentsList, new OnClickItemCallBack() {
                        @Override
                        public void clickItemCallBack(String userName,int userID) {
                            atUid = userID;
                            commentEdt.setText("@" + userName);
                            commentEdt.setFocusable(true);
                            commentEdt.setFocusableInTouchMode(true);
                            commentEdt.requestFocus();
                            inputManager.showSoftInput(commentEdt, 0);
                        }
                    });
                    recyclerView.setAdapter(commentsAdapter);
                    Log.v("commentsList", commentsList.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("失败" + new String(responseBody));
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshLayout.setRefreshing(false);
            }
        });
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
        params.put("id",answerID);
        return params;
    }
    private RequestParams setArticleParams(){
        RequestParams params = new RequestParams();
        params.put("id",articleID);
        return params;
    }


}

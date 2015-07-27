package org.iflab.wecentermobileandroidrestructure.activity;

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
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.CommentsAdapter;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
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
    EditText commentEdt;
    List<CommentInfo> commentsList = new ArrayList<>();
    CommentsAdapter commentsAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_comment);

        answerID = getIntent().getIntExtra("answer_id",-1);

        findViews();
        setViews();
        setToolBars();
        loadData();
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
        recyclerView.addItemDecoration(new ItemDivider(AnswerCommentActivity.this,R.drawable.item_divider));

        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    public void commentOnClick(View view){

    }

    private void loadData() {

        AsyncHttpWecnter.get(RelativeUrl.ANSWER_COMMENT, setParams(), new AsyncHttpResponseHandler() {
            String array;
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();

                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    array = obj.getString("rsm");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("array",array);
                if(array.equals("{\"total_rows\":\"0\"}")){
                    Snackbar snackbar = Snackbar.make(refreshLayout,"没有评论",Snackbar.LENGTH_SHORT);
                    snackbar.show();
//                    snackbar.setAction(取消, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            snackbar.dismiss();
//                        }
//                    });
                }else {
                    commentsList = gson.fromJson(array,
                            new TypeToken<ArrayList<CommentInfo>>() {
                            }.getType());
                    commentsAdapter = new CommentsAdapter(AnswerCommentActivity.this, commentsList);
                    recyclerView.setAdapter(commentsAdapter);
                    Log.v("commentsList",commentsList.toString());
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

    private RequestParams setParams(){
        RequestParams params = new RequestParams();
//        params.put("id",answerID);
        params.put("id",7);
        return params;
    }

}

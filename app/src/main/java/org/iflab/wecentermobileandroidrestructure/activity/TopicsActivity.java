package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalAnswerAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.TopicDetailAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalAnswer;
import org.iflab.wecentermobileandroidrestructure.model.personal.TopicDetailAnswer;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopicsActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    Button btnFoucs;
    TextView txt_topic_title;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TopicDetailAdapter answerAdapter;
    private List<TopicDetailAnswer.RowsEntity> data = new ArrayList<>();
    private boolean loadMore = true;
    int topic_id;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        topic_id = getIntent().getIntExtra("topic_id",-1);
        findViews();
        setViews();
        setToolBars();
        getData(page);
        getTopicData();
    }

    private void setToolBars() {
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);


    }


    private void setViews() {
        answerAdapter = new TopicDetailAdapter(data, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(answerAdapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);

        btnFoucs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("topic_id",topic_id);
                params.put("mobile_sign", MD5Transform.MD5("ajax"+AsyncHttpWecnter.SIGN));
                AsyncHttpWecnter.loadData(getApplicationContext(), RelativeUrl.TOPIC_FOCUS,params , AsyncHttpWecnter.Request.Get, new NetWork() {
                    @Override
                    public void parseJson(JSONObject response) {
                        try {
                            if(response.getString("type").equals("add")){
                                btnFoucs.setText("已关注");
                            }else if(response.getString("type").equals("remove")){
                                btnFoucs.setText("关注");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.list_topics);
        btnFoucs = (Button)findViewById(R.id.btn_foucs);
        txt_topic_title = (TextView)findViewById(R.id.txt_topic_title);
    }

    private void getData(int page) {
        AsyncHttpWecnter.loadData(getApplicationContext(), RelativeUrl.TOPIC_BEST_ANSWER, generateParams(page), AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                TopicDetailAnswer personalAnswer = (new Gson()).fromJson(response.toString(), TopicDetailAnswer.class);
                data.addAll(personalAnswer.getRows());
                answerAdapter.setData(data);
                if (personalAnswer.getTotal_rows() == 0) {
                    loadMore = false;
                }
            }
        });
    }

    private void getTopicData(){
        RequestParams params = new RequestParams();
        params.put("id",topic_id);
        params.put("mobile_sign", MD5Transform.MD5("topic"+AsyncHttpWecnter.SIGN));
        AsyncHttpWecnter.loadData(this, RelativeUrl.TOPIC_DETAIL, params, AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {

                try {
                    String title = response.getString("topic_title");
                    int has_focus = response.getInt("has_focus");

                    collapsingToolbar.setTitle(title);
                    txt_topic_title.setText(title);

                    if(has_focus == 0){
                        btnFoucs.setText("关注");
                    }else if(has_focus == 1){
                        btnFoucs.setText("已关注");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadMoreData() {
        if (loadMore) {
            page++;
            getData(page);
        }
    }

    private RequestParams generateParams(int page) {
        RequestParams params = new RequestParams();
        params.put("topic_id", topic_id);
        params.put("page", page);
        params.put("mobile_sign", MD5Transform.MD5("topic"+AsyncHttpWecnter.SIGN));
        return params;
    }
}

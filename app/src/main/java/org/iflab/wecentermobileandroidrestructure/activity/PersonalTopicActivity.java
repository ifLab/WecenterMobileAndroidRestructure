package org.iflab.wecentermobileandroidrestructure.activity;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/22 01:13
 */

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.MultipleTopicsAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalTopicAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.personal.MultipleTopics;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalTopic;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonalTopicActivity extends SwipeBackBaseActivity {

    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private PersonalTopicAdapter answerAdapter;
    private MultipleTopicsAdapter multipleTopicsAdapter;
    private List<PersonalTopic.RowsEntity> data = new ArrayList<>();
    private List<MultipleTopics.RsmEntity> multipleTopicsData = new ArrayList<>();
    Toolbar toolbar;
    private boolean loadMore = true;
    private String userName;
    String avatar;
    private int uid;
    private int page;
    public static int TYPE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_question);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        uid = intent.getIntExtra("uid", 4);
        avatar = intent.getStringExtra("avatar");
        findViews();


        if(getIntent().getIntExtra("multiple_topics",0)== TYPE){
            setMultipleTopicsViews();
            getMultipleTopics();
            setToolBar("问题涉及的话题");
        }else {
            setViews();
            refreshData();
            setToolBar(userName + "的话题");
        }

    }

    private void setMultipleTopicsViews() {
        multipleTopicsAdapter = new MultipleTopicsAdapter(this, multipleTopicsData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(multipleTopicsAdapter);
    }

    private void setViews() {
        answerAdapter = new PersonalTopicAdapter(this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(answerAdapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void refreshData() {
        endlessRecyclerOnScrollListener.reset();
        page = 1;
        data.clear();
        getData(1);
    }

    private void loadMoreData() {
        if (loadMore) {
            page++;
            getData(page);
        }
    }

    private void getData(int page) {
        AsyncHttpWecnter.loadData(this, RelativeUrl.PERSONAL_TOPIC, generateParams(page), AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                PersonalTopic personalTopic = (new Gson()).fromJson(response.toString(), PersonalTopic.class);
                if (personalTopic.getTotal_rows() == 0) {
                    loadMore = false;
                }else {
                    data.addAll(personalTopic.getRows());
                    answerAdapter.setData(data);
                }
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private RequestParams generateParams(int page) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("page", page);
        params.put("mobile_sign", MD5Transform.MD5("people"+AsyncHttpWecnter.SIGN));
        return params;
    }

    private void setToolBar(String content) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(content);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
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
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.list_personal_question);
    }


    public void getMultipleTopics() {
        ArrayList<String> list = getIntent().getStringArrayListExtra("topics");
        String topicParams = "";
        for(String item:list){
            topicParams += item +',';
        }
        RequestParams params = new RequestParams();
        params.put("topics", topicParams);

        AsyncHttpWecnter.post(RelativeUrl.TOPICS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                MultipleTopics multipleTopics = (new Gson()).fromJson(new String(responseBody), MultipleTopics.class);
                if(multipleTopics.getErr() == null){
                    multipleTopicsData.addAll(multipleTopics.getRsm());
                    multipleTopicsAdapter.setData(multipleTopicsData);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"出错啦",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

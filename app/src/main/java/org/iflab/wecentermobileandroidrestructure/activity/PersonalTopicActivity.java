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

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalTopicAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalTopic;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonalTopicActivity extends SwipeBackBaseActivity {

    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private PersonalTopicAdapter answerAdapter;
    private List<PersonalTopic.RowsEntity> data = new ArrayList<>();
    private boolean loadMore = true;
    private String userName;
    private int uid;
    private int page;
    private int perPage = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_question);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        uid = intent.getIntExtra("uid", 4);
        findViews();
        setToolBar();
        setViews();
        refreshData();
    }

    private void setViews() {
        answerAdapter = new PersonalTopicAdapter(this, data, userName);
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
                data.addAll(personalTopic.getRows());
                answerAdapter.setData(data);
                if (Integer.parseInt(personalTopic.getTotal_rows()) == data.size()) {
                    loadMore = false;
                }
            }
        });
    }

    private RequestParams generateParams(int page) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("page", page);
        params.put("per_page", perPage);
        return params;
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle(userName + "的回答");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

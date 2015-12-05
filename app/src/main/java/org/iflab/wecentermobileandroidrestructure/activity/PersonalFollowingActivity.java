package org.iflab.wecentermobileandroidrestructure.activity;

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
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalFollowingAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalQuestionAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalFollowing;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalQuestion;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonalFollowingActivity extends SwipeBackBaseActivity {


    public static final String FOLLOWING = "following";
    public static final String FAN = "follower";

    private String type;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private List<PersonalFollowing.RowsEntity> data = new ArrayList<>();
    private PersonalFollowingAdapter adapter;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private int uid = 4;
    private int page = 1;
    private int perPage = 10;
    private String userName;
    private String avatar;
    private boolean loadMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_question);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        uid = intent.getIntExtra("uid", 4);
        type = intent.getStringExtra("type");
        setToolBar();
        findViews();
        setViews();
        refreshData();
    }

    private void loadMoreData() {
        if (loadMore) {
            page++;
            getData(page);
        }
    }

    private void refreshData() {
        endlessRecyclerOnScrollListener.reset();
        page = 1;
        data.clear();
        getData(1);
    }


    private void getData(int page) {
        AsyncHttpWecnter.loadData(PersonalFollowingActivity.this, RelativeUrl.FOLLOWER, generateParams(page), AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                PersonalFollowing personalFollowing = new Gson().fromJson(response.toString(), PersonalFollowing.class);
                data.addAll(personalFollowing.getRows());
                adapter.setData(data);
                if (personalFollowing.getTotal_rows() == 0) {
                    loadMore = false;
                }
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private RequestParams generateParams(int page) {
        RequestParams params = new RequestParams();
        params.put("uid", uid);
        params.put("page", page);
        params.put("per_page", perPage);
        params.put("type",type.equals(FOLLOWING) ? "follows": "fans");
        params.put("mobile_sign", MD5Transform.MD5("people"+AsyncHttpWecnter.SIGN));
        return params;
    }

    private void findViews() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.list_personal_question);
    }

    private void setViews() {
        adapter = new PersonalFollowingAdapter(this, data);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
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

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        if (type.equals(FOLLOWING)) {
            toolbar.setTitle(userName + "关注者");
        } else {
            toolbar.setTitle(userName + "追随者");
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_question, menu);
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

package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalArticleAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalArticle;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/15 17:33
 */

public class PersonalArticleActivity extends SwipeBackBaseActivity{
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private int uid = 4;
    private int page = 1;
    private int perPage = 10;
    private String userName;
    private String avatar;
    private List<PersonalArticle.RowsEntity> data = new ArrayList<>();
    private PersonalArticleAdapter articleAdapter;
    private boolean loadMore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_question);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        avatar = intent.getStringExtra("avatar");
        uid = intent.getIntExtra("uid", 4);
        findViews();
        setToolBar();
        setViews();
        refreshData();
    }
    private void findViews() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.list_personal_question);
    }
    private void setViews() {
        articleAdapter = new PersonalArticleAdapter(PersonalArticleActivity.this, data, userName, avatar, uid);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(articleAdapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
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
        AsyncHttpWecnter.loadData(PersonalArticleActivity.this, RelativeUrl.PERSONAL_ARTICLE, generateParams(page), AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                PersonalArticle personalArticle = (new Gson()).fromJson(response.toString(), PersonalArticle.class);
                data.addAll(personalArticle.getRows());
                articleAdapter.setData(data);
                if (Integer.parseInt(personalArticle.getTotal_rows()) == data.size()) {
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
        toolbar.setTitle(userName + "的文章");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

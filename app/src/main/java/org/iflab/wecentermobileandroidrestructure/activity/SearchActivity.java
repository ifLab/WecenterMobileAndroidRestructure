package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.SearchAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchArticles;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchBase;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchQuestions;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchTopics;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchUsers;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.homepage.HomePage;
import org.iflab.wecentermobileandroidrestructure.ui.SearchView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity  implements SearchView.SearchViewListener{

    public static String TOPICS = "topics";
    public static String ARTICLES = "articles";
    public static String QUESTIONS = "questions";
    public static String USERS = "users";

    @Bind(R.id.swipyrefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.list_search)
    RecyclerView recyclerView;

    @Bind(R.id.main_search_layout)
    SearchView searchView;

    LinearLayoutManager linearLayoutManager;
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    SearchAdapter searchAdapter;
    List<SearchBase> searchList = new ArrayList<>();
    private boolean loadMore = true;
    private int page = 1;
    private String searchText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setView();
        setListeners();
    }

    private void setView() {//设置监听
        searchView.setSearchViewListener(this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(SearchActivity.this,searchList);
        recyclerView.setAdapter(searchAdapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
    }

    private void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(searchText.length() < 1){
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
                loadData(searchText);
            }

        });

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if(loadMore) {
                    loadData(searchText);
                }
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    private void loadData(String searchText){
        if(searchText.length() < 1){
            return;
        }

        RequestParams params = new RequestParams();
        params.put("page",page);
        params.put("search_type","all");
        params.put("q",searchText);

        AsyncHttpWecnter.get(RelativeUrl.SEARCH, params, new AsyncHttpResponseHandler() {

            SearchArticles searchArticles;
            SearchQuestions searchQuestions;
            SearchTopics searchTopics;
            SearchUsers searchUsers;

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                String response = new String(responseBody);
                try {
                    JSONObject responseObj = new JSONObject(response);
                    String rsm = responseObj.getString("rsm");

                    if(loadMore)
                        page++;
                    if (rsm.equals("false")) {
                        loadMore = false;
                        Toast.makeText(getApplicationContext(), "只有这么多了", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONArray array = new JSONArray(rsm);
                        int len = array.length();
                        for (int i = 0; i < len; i++) {
                            JSONObject obj = array.getJSONObject(i);
                            // 接口居然会给null !!
//                            if(obj.getString("uid").equals("null")){
//                                obj.put("uid",4);
//                            }
//                            if(obj.getString("score").equals("null")){
//                                obj.put("score",1);
//                            }
                            Log.v("type",obj.getString("type"));
                            if (obj.getString("type").equals(ARTICLES)) {
                                searchArticles = gson.fromJson(array.getString(i), SearchArticles.class);
                                searchList.add(searchArticles);
                            } else if (obj.getString("type").equals(QUESTIONS)) {
                                searchQuestions = gson.fromJson(array.getString(i), SearchQuestions.class);
                                searchList.add(searchQuestions);
                            } else if (obj.getString("type").equals(TOPICS)) {
                                searchTopics = gson.fromJson(array.getString(i), SearchTopics.class);
                                searchList.add(searchTopics);
                            } else {
                                searchUsers = gson.fromJson(array.getString(i), SearchUsers.class);
                                searchList.add(searchUsers);
                            }

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                searchAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onRefreshAutoComplete(String text) {

    }

    @Override
    public void onSearch(String text) {
        searchText = text;
        page = 1;
        searchAdapter.clear();
        loadData(searchText);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchView.removeSearchViewListener(this);
    }
}

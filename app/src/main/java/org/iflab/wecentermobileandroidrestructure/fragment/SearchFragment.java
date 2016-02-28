package org.iflab.wecentermobileandroidrestructure.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.SearchAdapter;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchArticles;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchBase;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchQuestions;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchTopics;
import org.iflab.wecentermobileandroidrestructure.model.Search.SearchUsers;
import org.iflab.wecentermobileandroidrestructure.ui.SearchView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchFragment extends BaseFragment implements SearchView.SearchViewListener{


    public static String TOPICS = "topics";
    public static String ARTICLES = "articles";
    public static String QUESTIONS = "questions";
    public static String USERS = "users";


    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    SearchView searchView;

    LinearLayoutManager linearLayoutManager;
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    SearchAdapter searchAdapter;
    List<SearchBase> searchList = new ArrayList<>();
    private boolean loadMore = true;
    private int page = 1;
    private String searchText = "";


    public static SearchFragment newInstance() {
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView)view.findViewById(R.id.list_search);
        searchView = (SearchView)view.findViewById(R.id.main_search_layout);

        searchView.setSearchViewListener(this);

        setView();
        setListeners();
        return view;
    }

    private void setView() {//设置监听

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getActivity(),searchList);
        recyclerView.setAdapter(searchAdapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
    }

    private void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (searchText.length() < 1) {
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
                    JSONObject rsmObj =  new JSONObject(responseObj.getString("rsm"));

                    if (loadMore) {
                        page++;
                    }
                    JSONArray array = rsmObj.getJSONArray("rows");
                    if(array.length() == 0){
                        swipeRefreshLayout.setRefreshing(false);
                        return;
                    }
                    if (rsmObj.getInt("total_rows") == 0) {
                        loadMore = false;
                        Toast.makeText(getActivity(), "只有这么多了", Toast.LENGTH_SHORT).show();
                    } else {

                        int len = array.length();
                        for (int i = 0; i < len; i++) {
                            JSONObject obj = array.getJSONObject(i);

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
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        searchView.removeSearchViewListener(this);
    }

}

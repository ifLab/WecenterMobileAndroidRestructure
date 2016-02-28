package org.iflab.wecentermobileandroidrestructure.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.MultipleTopicsAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.PersonalTopicAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.model.personal.PersonalTopic;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * HotTopicChildFragment
 */
public class HotTopicChildFragment extends BaseFragment {

    @Bind(R.id.recyclerview_topic)
    RecyclerView recyclerView;

    @Bind(R.id.swipyrefreshlayout)
    SwipeRefreshLayout refreshLayout;

    public static int WEEK = 7,MONTH = 30,SELF = 1;
    public static HotTopicChildFragment hotTopicChildFragment;
    int type;
    int page = 1;
    boolean loadMore;
    PersonalTopicAdapter answerAdapter;
    List<PersonalTopic.RowsEntity> data = new ArrayList<>();
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    public HotTopicChildFragment() {}

    public static HotTopicChildFragment newInstance(int type){
        hotTopicChildFragment = new HotTopicChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        hotTopicChildFragment.setArguments(bundle);
        return hotTopicChildFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hot_topic_child, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        type = bundle.getInt("type");

        setViews();
        loadData(page);

        return view;
    }

    private void setViews() {
        answerAdapter = new PersonalTopicAdapter(getActivity(), data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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
        loadData(page);
    }

    private void loadMoreData() {
        if (loadMore) {
            page++;
            loadData(page);
        }
    }

    private void loadData(int page) {
        refreshLayout.setRefreshing(true);
        String url = "";
        RequestParams params = new RequestParams();
        params.put("page",page);
        switch (type){
            case 7:
                params.put("day","week");
                params.put("mobile_sign", MD5Transform.MD5("topic"+AsyncHttpWecnter.SIGN));
                url = RelativeUrl.HOT_TOPICS;
                break;
            case 30:
                params.put("day","month");
                params.put("mobile_sign", MD5Transform.MD5("topic"+AsyncHttpWecnter.SIGN));
                url = RelativeUrl.HOT_TOPICS;
                break;
            case 1:
                params.put("uid", User.getLoginUser(getActivity()).getUid());
                params.put("mobile_sign", MD5Transform.MD5("people"+AsyncHttpWecnter.SIGN));
                url = RelativeUrl.PERSONAL_TOPIC;
                break;
        }
        AsyncHttpWecnter.loadData(getActivity(),url, params, AsyncHttpWecnter.Request.Get, new NetWork() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        data.clear();
    }
}

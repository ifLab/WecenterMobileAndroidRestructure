package org.iflab.wecentermobileandroidrestructure.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.FoundAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.found.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.found.BaseFoundInfo;
import org.iflab.wecentermobileandroidrestructure.model.found.QuestionInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcjcch on 15/6/22.
 */
public class FoundChildFragment extends BaseFragment {
    public static final int RECOMMEND = 0;
    public static final int HOT = 1;
    public static final int NEW = 2;
    public static final int WAIT_ANSWER = 3;
    private int perPage = 10;
    private int page = 1;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<BaseFoundInfo> foundDatas = new ArrayList<>();
    private FoundAdapter adapter;
    private boolean loadMore = true;
    private boolean refresh = false;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private LinearLayoutManager manager;
    private int foundType;

    public static FoundChildFragment newInstances(int id) {
        FoundChildFragment foundChildFragment = new FoundChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", id);
        foundChildFragment.setArguments(bundle);
        return foundChildFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (endlessRecyclerOnScrollListener != null) {
            endlessRecyclerOnScrollListener.reset();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_found_child, container, false);
        Bundle bundle = getArguments();
        foundType = bundle.getInt("position");
        findViews(relativeLayout);
        setRecyclerView();
        setData();
        setListener();
        return relativeLayout;
    }

    private void findViews(RelativeLayout relativeLayout) {
        refreshLayout = (SwipeRefreshLayout) relativeLayout.findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView) relativeLayout.findViewById(R.id.list_found);
    }

    private void setRecyclerView() {
        manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new FoundAdapter(getActivity(), foundDatas);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int current_page) {
                if (loadMore) {
                    setData();
                }
            }
        };
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshReset();
                setData();
            }
        });
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    private void setData() {
        AsyncHttpWecnter.loadData(getActivity(), RelativeUrl.FOUND, generateParam(), AsyncHttpWecnter.Request.Get, new NetWork() {

            @Override
            public void parseJson(JSONObject response) {
                if (refresh) {
                    foundDatas.clear();
                    refresh = false;
                }
                try {
                    int totalRows = response.getInt("total_rows");
                    if (totalRows == 0) {
                        //TODO 加载完成
                        loadMore = false;
                        Toast.makeText(getActivity().getApplicationContext(), "只有这么多了", Toast.LENGTH_SHORT).show();
                    } else {
                        page++;
                        JSONArray rows = response.getJSONArray("rows");
                        for (int i = 0; i < rows.length(); i++) {
                            JSONObject row = rows.getJSONObject(i);
                            String post_type = row.getString("post_type");
                            if (post_type.equalsIgnoreCase("question")) {
                                try {
                                    QuestionInfo questionInfo = new QuestionInfo();
                                    questionInfo.setQuestionId(row.getInt("question_id"));
                                    questionInfo.setQuestionContent(row.getString("question_content"));
                                    questionInfo.setQuestionDetail(row.getString("question_detail"));
                                    questionInfo.setUpdateTime(row.getString("update_time"));
                                    questionInfo.setType(post_type);
                                    int answerCount = row.getInt("answer_count");
                                    if (answerCount != 0) {
                                        JSONObject answerObject = row.getJSONObject("answer");
                                        JSONObject userInfoObject = answerObject.getJSONObject("user_info");
                                        questionInfo.setAnswerUid(userInfoObject.getInt("uid"));
                                        questionInfo.setAnswerUserName(userInfoObject.getString("user_name"));
                                        questionInfo.setAnswerAvatarFile(userInfoObject.getString("avatar_file"));
                                        questionInfo.setAnswerContent(answerObject.getString("answer_content"));
                                    }
                                    JSONObject userInfoObject = row.getJSONObject("user_info");
                                    questionInfo.setPublishUid(userInfoObject.getInt("uid"));
                                    questionInfo.setPublishUserName(userInfoObject.getString("user_name"));
                                    questionInfo.setPublishAvatarFile(userInfoObject.getString("avatar_file"));
                                    foundDatas.add(questionInfo);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity().getApplicationContext(),"失败接口:  "+RelativeUrl.FOUND+"   缺少question_detail字段",Toast.LENGTH_LONG).show();
                                }
                            } else if (post_type.equalsIgnoreCase("article")) {
                                try {
                                    ArticleInfo articleInfo = new ArticleInfo();
                                    articleInfo.setType(post_type);
                                    articleInfo.setArticleId(row.getInt("id"));
                                    articleInfo.setAddTime(row.getLong("add_time"));
                                    articleInfo.setArticleTitle(row.getString("title"));
                                    articleInfo.setArticleMessage(row.getString("message"));
                                    articleInfo.setVotes(row.getInt("votes"));
                                    JSONObject userInfoObject = row.getJSONObject("user_info");
                                    articleInfo.setUid(userInfoObject.getInt("uid"));
                                    articleInfo.setUserName(userInfoObject.getString("user_name"));
                                    articleInfo.setAvatarFile(userInfoObject.getString("avatar_file"));
                                    foundDatas.add(articleInfo);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void finish() {
                refreshLayout.setRefreshing(false);
                super.finish();
            }
        });
    }

    private RequestParams generateParam() {
        RequestParams params = new RequestParams();
        params.put("per_page", perPage);
        params.put("page", page);
        if (foundType == RECOMMEND) {
            params.put("is_recommend", 1);
        } else {
            params.put("is_recommend", 0);
            if (foundType == HOT) {
                params.put("sort_type", "hot");
            } else if (foundType == NEW) {
                params.put("sort_type", "new");
            } else {
                params.put("sort_type", "unresponsive");
            }
        }
        return params;
    }

    private void refreshReset() {
        page = 1;
        refresh = true;
        loadMore = true;
        endlessRecyclerOnScrollListener.reset();
    }

}

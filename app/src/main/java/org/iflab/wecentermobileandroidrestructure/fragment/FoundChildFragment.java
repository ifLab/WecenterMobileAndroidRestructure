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

import com.loopj.android.http.RequestParams;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.FoundAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
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
    private int perPage = 10;
    private int page = 1;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<BaseFoundInfo> foundDatas = new ArrayList<>();
    private FoundAdapter adapter;

    public static FoundChildFragment newInstances(int id) {
        FoundChildFragment foundChildFragment = new FoundChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", id);
        foundChildFragment.setArguments(bundle);
        return foundChildFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_found_child, container, false);
        findViews(relativeLayout);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new FoundAdapter(getActivity().getApplicationContext(),foundDatas);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        setData();
        return relativeLayout;
    }

    private void findViews(RelativeLayout relativeLayout) {
        refreshLayout = (SwipeRefreshLayout) relativeLayout.findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView) relativeLayout.findViewById(R.id.list_found);
    }

    private void setData() {
        AsyncHttpWecnter.loadData(getActivity(), RelativeUrl.FOUND, null, AsyncHttpWecnter.Request.Get, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                try {
                    int totalRows = response.getInt("total_rows");
                    if (totalRows == 0) {
                        //TODO 加载完成
                    } else {
                        page++;
                        JSONArray rows = response.getJSONArray("rows");
                        for (int i = 0; i < rows.length(); i++) {
                            JSONObject row = rows.getJSONObject(i);
                            String post_type = row.getString("post_type");
                            if (post_type.equalsIgnoreCase("question")) {
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
                                    questionInfo.setQuestionDetail(answerObject.getString("answer_content"));
                                }
                                JSONObject userInfoObject = row.getJSONObject("user_info");
                                questionInfo.setPublishUid(userInfoObject.getInt("uid"));
                                questionInfo.setPublishUserName(userInfoObject.getString("user_name"));
                                questionInfo.setPublishAvatarFile(userInfoObject.getString("avatar_file"));
                                foundDatas.add(questionInfo);
                            } else if (post_type.equalsIgnoreCase("article")) {
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
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private RequestParams generateParam() {
        RequestParams params = new RequestParams();
        return params;
    }

}

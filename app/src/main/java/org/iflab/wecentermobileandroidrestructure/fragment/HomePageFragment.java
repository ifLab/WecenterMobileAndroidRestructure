package org.iflab.wecentermobileandroidrestructure.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.melnykov.fab.FloatingActionButton;
import com.orhanobut.logger.Logger;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PublishAnswerArticleActivity;
import org.iflab.wecentermobileandroidrestructure.adapter.HomePageAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.HomePageRecycleAdapter;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.listener.EndlessRecyclerOnScrollListener;
import org.iflab.wecentermobileandroidrestructure.model.homepage.AnswerInfo;
import org.iflab.wecentermobileandroidrestructure.model.homepage.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.model.homepage.HomePage;
import org.iflab.wecentermobileandroidrestructure.model.homepage.QuestionInfo;
import org.iflab.wecentermobileandroidrestructure.model.homepage.UserInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcjcch on 15/5/28.
 */
public class HomePageFragment extends BaseFragment {
    private RecyclerView listHomepage;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;
    private HomePageRecycleAdapter homePageAdapter;
    private List<HomePage> homePages = new ArrayList<>();
    private int perPage = 20;
    private int page = 0;
    private boolean loadMore = true;
    private LinearLayoutManager linearLayoutManager;
    private boolean refresh = false;

    public static HomePageFragment newInstances() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_homepage, container, false);
        findViews(relativeLayout);
        setViews();
        setListeners();
        loadData();
//        HomePage homePage = new HomePage();
//        homePage = HomePage.findFirst(HomePage.class);
//        System.out.println(homePage);
//        Logger.d(homePage.toString(), homePage.toString());
        return relativeLayout;
    }

    private void findViews(View view) {
        listHomepage = (RecyclerView) view.findViewById(R.id.list_homepage);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
    }

    private void setViews() {
        homePageAdapter = new HomePageRecycleAdapter(getActivity().getApplicationContext(), homePages);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listHomepage.setLayoutManager(linearLayoutManager);
        listHomepage.setAdapter(homePageAdapter);
        fab.attachToRecyclerView(listHomepage);
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);

    }

    private void setListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshReset();
                loadData();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), PublishAnswerArticleActivity.class));
            }
        });
        listHomepage.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (loadMore) {
                    loadData();
                }
            }
        });
    }

    private void loadData() {
        AsyncHttpWecnter.get(RelativeUrl.HOME_PAGE, setParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(res);
                if (jsonProgress) return;
                if (refresh) {
                    homePages.clear();
                    refresh = false;
                }
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONObject rsm = jsonObject.getJSONObject("rsm");
                    int totalRows = rsm.getInt("total_rows");
                    if (totalRows == 0) {
                        //TODO 加载完成
                        loadMore = false;
                        Toast.makeText(getActivity().getApplicationContext(), "只有这么多了", Toast.LENGTH_SHORT).show();
                    } else {
                        page++;
                        JSONArray rows = rsm.getJSONArray("rows");
                        for (int i = 0; i < rows.length(); i++) {
                            HomePage homePage = new HomePage();
                            JSONObject row = rows.getJSONObject(i);
                            int history_id = row.getInt("history_id");
                            homePage.setHistoryId(history_id);
                            int uid = row.getInt("uid");
                            homePage.setUid(uid);
                            int associate_action = row.getInt("associate_action");
                            homePage.setAssociateAction(associate_action);
                            int associate_id = row.getInt("associate_id");
                            homePage.setAssociateId(associate_id);
                            int add_time = row.getInt("add_time");
                            homePage.setAddTime(add_time);
                            JSONObject userInfoJsonObject = row.getJSONObject("user_info");
                            int userInfoUid = userInfoJsonObject.getInt("uid");
                            String userInfoUserName = userInfoJsonObject.getString("user_name");
                            String userInfoAvatarFile = userInfoJsonObject.getString("avatar_file");
                            UserInfo userInfo = new UserInfo();
                            userInfo.setUid(userInfoUid);
                            userInfo.setUserName(userInfoUserName);
                            userInfo.setUserAvatar(userInfoAvatarFile);
                            homePage.setUserInfo(userInfo);
//                            userInfo.save();
                            if (associate_action == 101 || associate_action == 105) {
                                JSONObject questionInfoJsonObject = row.getJSONObject("question_info");
                                QuestionInfo questionInfo = new QuestionInfo();
                                int questionInfoId = questionInfoJsonObject.getInt("question_id");
                                String questionInfoContent = questionInfoJsonObject.getString("question_content");
                                questionInfo.setQuestionId(questionInfoId);
                                questionInfo.setQuestionContent(questionInfoContent);
                                homePage.setQuestionInfo(questionInfo);
//                                questionInfo.save();
                            } else if (associate_action == 501 || associate_action == 502) {
                                JSONObject articleInfoJsonObject = row.getJSONObject("article_info");
                                ArticleInfo articleInfo = new ArticleInfo();
                                int articleInfoId = articleInfoJsonObject.getInt("id");
                                String articleinfoTitle = articleInfoJsonObject.getString("title");
                                articleInfo.setArticleId(articleInfoId);
                                articleInfo.setArticleTitle(articleinfoTitle);
                                homePage.setArticleInfo(articleInfo);
//                                articleInfo.save();
                            } else {
                                JSONObject questionInfoJsonObject = row.getJSONObject("question_info");
                                QuestionInfo questionInfo = new QuestionInfo();
                                int questionInfoId = questionInfoJsonObject.getInt("question_id");
                                String questionInfoContent = questionInfoJsonObject.getString("question_content");
                                questionInfo.setQuestionId(questionInfoId);
                                questionInfo.setQuestionContent(questionInfoContent);
                                homePage.setQuestionInfo(questionInfo);
//                                questionInfo.save();

                                JSONObject answerInfoJsonObject = row.getJSONObject("answer_info");
                                int answerId = answerInfoJsonObject.getInt("answer_id");
                                int questionId = answerInfoJsonObject.getInt("question_id");
                                String answerContent = answerInfoJsonObject.getString("answer_content");
                                int agreeCount = answerInfoJsonObject.getInt("agree_count");
                                int agree_status = answerInfoJsonObject.getInt("agree_status");
                                AnswerInfo answerInfo = new AnswerInfo();
                                answerInfo.setAnswerId(answerId);
                                answerInfo.setQuestionId(questionId);
                                answerInfo.setAnswerContent(answerContent);
                                answerInfo.setAgreeCount(agreeCount);
                                answerInfo.setAgreestatus(agree_status);
                                homePage.setAnswerInfo(answerInfo);
//                                answerInfo.save();
                            }
//                            homePage.save();
                            homePages.add(homePage);
                        }
                        homePageAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
                super.onFinish();
            }
        });
    }

    private RequestParams setParams() {
        RequestParams params = new RequestParams();
        params.put("per_page", perPage);
        params.put("page", page);
        return params;
    }

    private void refreshReset() {
        page = 0;
        refresh = true;
        loadMore = true;
        EndlessRecyclerOnScrollListener.reset();
    }
}
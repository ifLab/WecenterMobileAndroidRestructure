package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apmem.tools.layouts.FlowLayout;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.AnswerAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.question.QuestionDetailAnswers;
import org.iflab.wecentermobileandroidrestructure.model.question.QuestionInfo;
import org.iflab.wecentermobileandroidrestructure.model.question.QuestionTopics;
import org.iflab.wecentermobileandroidrestructure.tools.FormHtmlAsyncTask;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;
import org.iflab.wecentermobileandroidrestructure.tools.WecenterImageGetter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionDetailActivity extends SwipeBackBaseActivity implements View.OnClickListener {


    private Toolbar toolbar;
    ListView listView;
    View headerView;
    TextView userNameTextView;
    TextView contentTextView;
    TextView bookMarkTextView;
    TextView focusTextView;
    TextView contentWebView;
    ImageView userImageView;
    Button foucsBtn;
    FlowLayout topicFlowLayout;
    RelativeLayout addAnswerRel;
    RelativeLayout topRel;
    SwipeRefreshLayout refreshLayout;
    List<QuestionDetailAnswers> answersList = new ArrayList();
    List<QuestionTopics> questionsList;
    AnswerAdapter answerAdapter;
    int question_id;
    int uid;
    int foucsNum;
    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        uid = getIntent().getIntExtra("uid", -1);
        question_id = getIntent().getIntExtra("question_id", 2);
        findViews();
        findHeaderView();
        setViews();
        setToolBars();
        loadData();
    }


    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.list_question_pic);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);
    }


    public static void openQuestionDetail(Context context, int uid, int question_id) {
        if (intent == null) {
            intent = new Intent();
        }
        // intent.putExtra 最终用的事ArrayMap.put 此方法可覆盖值，setClass也可覆盖
        intent.putExtra("uid", uid);
        intent.putExtra("question_id", question_id);
        intent.setClass(context, QuestionDetailActivity.class);
        context.startActivity(intent);
        intent.setClassName("", "");//清空对上下文的引用
    }


    private void findHeaderView() {
        headerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.question_header_view, null);
        userNameTextView = (TextView) headerView.findViewById(R.id.txt_user_name);
        userImageView = (ImageView) headerView.findViewById(R.id.image_profile);
        contentTextView = (TextView) headerView.findViewById(R.id.txt_question_content);
        contentWebView = (TextView) headerView.findViewById(R.id.webv_question_content);
        topicFlowLayout = (FlowLayout) headerView.findViewById(R.id.flow_question_topic);
        bookMarkTextView = (TextView) headerView.findViewById(R.id.txt_bookmark);
        focusTextView = (TextView) headerView.findViewById(R.id.txt_focus);
        foucsBtn = (Button) headerView.findViewById(R.id.btn_foucs);
        addAnswerRel = (RelativeLayout) headerView.findViewById(R.id.rel_add_answer);
        topRel = (RelativeLayout) headerView.findViewById(R.id.rel_top);
    }

    private void setViews() {
        refreshLayout.setEnabled(false);
        listView.addHeaderView(headerView);
        foucsBtn.setOnClickListener(this);
        topicFlowLayout.setOnClickListener(this);
        addAnswerRel.setOnClickListener(this);
        topRel.setOnClickListener(this);
    }

    private void setToolBars() {
        toolbar.setTitle("问题详情");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rel_add_answer:
                int[] startingLocation = new int[2];
                view.getLocationOnScreen(startingLocation);
                startingLocation[0] += view.getWidth() / 2;
                intent = new Intent(QuestionDetailActivity.this, PublishAnswerArticleActivity.class);
                intent.putExtra(PublishAnswerArticleActivity.PUBLISH_TYPE_INTENT, PublishAnswerArticleActivity.PUBLISH_ANSWER);
                intent.putExtra(PublishAnswerArticleActivity.QUESTION_ID_INTENT, question_id);
                intent.putExtra(PublishAnswerArticleActivity.ARG_REVEAL_START_LOCATION, startingLocation);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.btn_foucs:
                foucsQuestion();
                break;
            case R.id.rel_top:
                //到个人中心
                if (uid != -1) {
                    PersonalCenterActivity.openPersonalCenter(QuestionDetailActivity.this,uid);
                }
                break;

            case R.id.flow_question_topic:
                intent = new Intent(QuestionDetailActivity.this,PersonalTopicActivity.class);
                ArrayList<String> topicsList = new ArrayList<>();
                for(QuestionTopics t:questionsList){
                    topicsList.add(t.getTopic_id()+"");
                }
                intent.putExtra("multiple_topics",PersonalTopicActivity.TYPE);
                intent.putExtra("topics",topicsList);
                startActivity(intent);
                break;


        }
    }


    private void foucsQuestion() {
        AsyncHttpWecnter.loadData(QuestionDetailActivity.this, RelativeUrl.QUESTION_FOUCS, setFoucsParams(), AsyncHttpWecnter.Request.Post, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                // update button UI
                String type = "";
                try {
                    type = response.getString("type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                switch (type) {
                    case "add":
                        focusTextView.setText(++foucsNum + "");
                        updateFoucsBtnUI(1);
                        break;
                    case "remove":
                        focusTextView.setText(--foucsNum + "");
                        updateFoucsBtnUI(0);
                        break;
                }
//                Log.v("foucsQuestion", response.toString());

            }
        });
    }


    private void loadData() {
        AsyncHttpWecnter.loadData(QuestionDetailActivity.this, RelativeUrl.QUESTION_INFO, setParams(), AsyncHttpWecnter.Request.Get, new NetWork() {
            QuestionInfo questionInfo;
            QuestionDetailAnswers answerInfo;

            @Override
            public void parseJson(JSONObject response) {
                Gson gson = new Gson();
                try {
                    //questionInfo
                    questionInfo = gson.fromJson(response.getString("question_info"), QuestionInfo.class);
//                    Log.v("questionInfo", questionInfo.toString());

                    //answer
                    JSONArray answerArray = response.getJSONArray("answers");
                    int length = answerArray.length();
                    if (length != 0) {
                        for(int i=0;i<length;i++){
                            answerInfo = new QuestionDetailAnswers();
                            answerInfo = gson.fromJson(answerArray.getString(i), QuestionDetailAnswers.class);
                            answersList.add(answerInfo);
//                            Log.v("answerInfo", answerInfo.toString());
                        }
                    }
                    answerAdapter = new AnswerAdapter(QuestionDetailActivity.this, answersList, questionInfo.getQuestion_content());
                    listView.setAdapter(answerAdapter);

                    //QuestionTopics
                    questionsList = gson.fromJson(response.getString("question_topics"),
                            new TypeToken<ArrayList<QuestionTopics>>() {
                            }.getType());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateFoucsBtnUI(questionInfo.getUser_question_focus());
                if(questionInfo.getQuestion_detail().length() != 0) {
                    (new FormHtmlAsyncTask((new WecenterImageGetter.Builder(QuestionDetailActivity.this).build()), contentWebView)).execute(questionInfo.getQuestion_detail());
                }else{
                    contentWebView.setVisibility(View.GONE);
                }
                contentTextView.setText(questionInfo.getQuestion_content());
                bookMarkTextView.setText(questionInfo.getFocus_count() + "");

                foucsNum = questionInfo.getFocus_count();
                focusTextView.setText(foucsNum + "");
                if (questionsList != null) {
                    for (QuestionTopics q : questionsList) {
                        addFlowTopics(q.getTopic_title());
                    }

                }
                uid = questionInfo.getUser_info().getUid();
                userNameTextView.setText(questionInfo.getUser_info().getUser_name());
                ImageLoader.getInstance().displayImage(questionInfo.getUser_info().getAvatar_file(), userImageView, ImageOptions.optionsImage);

            }
        });

    }

    private void updateFoucsBtnUI(int hasFoucs) {
        switch (hasFoucs) {
            case 0:
                foucsBtn.setText("关注");

                break;
            case 1:
                foucsBtn.setText("已关注");
                break;
        }
    }

    private void addFlowTopics(String toipcString) {
        TextView button = new TextView(QuestionDetailActivity.this);
        button.setMinWidth(getResources().getDimensionPixelSize(R.dimen.topic_min_width));
        button.setGravity(Gravity.CENTER);
        button.setText(toipcString);
        button.setBackground(getResources().getDrawable(R.drawable.public_topic));
        button.setTextColor(Color.WHITE);
        button.setPadding(10, 10, 10, 10);
        topicFlowLayout.addView(button);
    }

    private RequestParams setParams() {
        RequestParams params = new RequestParams();
        params.put("id", question_id);
        return params;
    }

    private RequestParams setFoucsParams() {
        RequestParams params = new RequestParams();
//        params.put("id",articleID);
        params.put("question_id", question_id);
        return params;
    }


}

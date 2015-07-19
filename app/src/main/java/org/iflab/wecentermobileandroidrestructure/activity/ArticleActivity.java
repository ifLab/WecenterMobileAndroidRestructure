package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.article.ArticleInfo;
import org.iflab.wecentermobileandroidrestructure.tools.ImageOptions;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleActivity extends BaseActivity {

    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    CircleImageView circleImageView;
    TextView usernameTextView;
    WebView contentWebView;
    TextView votesTextView;
    ImageButton shareBtn;
    ImageButton commentBtn;
    CheckBox likeCheckBox;
    CheckBox dislikeCheckBox;
    int articleID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        findViews();
        setViews();
        setToolBars();
        setListenter();
        articleID = getIntent().getIntExtra("article_id",1);
        loadData();
    }



    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipyrefreshlayout);
        circleImageView = (CircleImageView)findViewById(R.id.image_profile);
        usernameTextView = (TextView)findViewById(R.id.txt_user_name);
        contentWebView = (WebView)findViewById(R.id.webv_content);
        votesTextView = (TextView)findViewById(R.id.txt_votes);
        shareBtn = (ImageButton) findViewById(R.id.btn_share);
        commentBtn = (ImageButton)findViewById(R.id.btn_comment);
        likeCheckBox = (CheckBox)findViewById(R.id.check_like);
        dislikeCheckBox = (CheckBox)findViewById(R.id.check_dislike);
    }

    private void setViews() {
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
    }

    private void setListenter() {
        likeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


            }
        });

        dislikeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    public void gotoShare(View view){

    }

    public void gotoComment(View view){

    }

    private void setToolBars() {
        toolbar.setTitle("文章");//设置Toolbar标题
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

    private void loadData() {
        AsyncHttpWecnter.loadData(ArticleActivity.this, RelativeUrl.ARTICLE_INFO, setParams(), AsyncHttpWecnter.Request.Get, new NetWork() {
            ArticleInfo artleInfo;

            @Override
            public void parseJson(JSONObject response) {
                Gson gson = new Gson();
                try {
                    artleInfo = gson.fromJson(response.getString("article_info"), ArticleInfo.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                usernameTextView.setText(artleInfo.getUser_name());
                contentWebView.loadDataWithBaseURL("about:blank", artleInfo.getMessage(), "text/html", "utf-8", null);
                contentWebView.setBackgroundColor(getResources().getColor(R.color.bg_color_grey));
                ImageLoader.getInstance().displayImage(artleInfo.getAvatar_file(), circleImageView, ImageOptions.optionsImage);
                toolbar.setTitle(artleInfo.getArticleTitle());
                votesTextView.setText(artleInfo.getVotes() + "");
            }
        });

    }

    private RequestParams setParams() {
        RequestParams params = new RequestParams();
//        params.put("id",articleID);
        params.put("id",1);
        return params;
    }

}

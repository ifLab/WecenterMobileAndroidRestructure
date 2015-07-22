package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.iflab.wecentermobileandroidrestructure.R;

import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {

    ArrayList<String> topicList;
    Toolbar toolbar;
    RecyclerView listTopics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        topicList = getIntent().getStringArrayListExtra("topics");
        findViews();
        setViews();
        setToolBars();
        loadData();
    }

    private void setToolBars() {
        toolbar.setTitle("话题");//设置Toolbar标题
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

    }

    private void setViews() {

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listTopics = (RecyclerView)findViewById(R.id.list_topics);
    }


}

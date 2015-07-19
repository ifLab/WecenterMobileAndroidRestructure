package org.iflab.wecentermobileandroidrestructure.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.iflab.wecentermobileandroidrestructure.R;

import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {

    ArrayList<String> topicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        topicList = getIntent().getStringArrayListExtra("topics");
    }


}

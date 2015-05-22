package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.HomePageAdapter;


public class WencenterActivity extends BaseActivity {

    private ListView listHomepage;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wencenter);
        findViews();
        setViews();
        setListeners();
    }

    private void findViews() {
        listHomepage = (ListView) findViewById(R.id.list_homepage);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);
    }

    private void setViews() {
        listHomepage.setAdapter(new HomePageAdapter(getApplicationContext()));
        fab.attachToListView(listHomepage);
        refreshLayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void setListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wencenter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
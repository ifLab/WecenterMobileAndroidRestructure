package org.iflab.wecentermobileandroidrestructure.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.HomePageAdapter;


public class WencenterActivity extends BaseActivity {

    private ListView listHomepage;
    private FloatingActionButton fab;

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
    }

    private void setViews() {
        listHomepage.setAdapter(new HomePageAdapter(getApplicationContext()));
        fab.attachToListView(listHomepage);
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

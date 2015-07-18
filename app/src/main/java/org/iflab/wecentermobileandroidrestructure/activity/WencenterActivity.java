package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.fragment.FoundFrgment;
import org.iflab.wecentermobileandroidrestructure.fragment.HomePageFragment;
import org.iflab.wecentermobileandroidrestructure.model.User;


public class WencenterActivity extends BaseActivity {

    private final String[] navStrings = {"主页", "发现"};

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    //    private RelativeLayout nav_header;
    private ListView list_nav;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout rel_nav;
    private RelativeLayout rel_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_wencenter);
        findViews();
        setToolbar();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.hello_world, R.string.hello_world) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        list_nav.setAdapter(new NavAdapter());
        navigationDrawerItemSelected(0);
        list_nav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigationDrawerItemSelected(position);
            }
        });
    }

    private void setToolbar() {
        setToolbarTitle("主页");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setToolbarTitle(String toolbarString) {
        toolbar.setTitle(toolbarString);
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

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        list_nav = (ListView) findViewById(R.id.list_nav);
        rel_nav = (RelativeLayout) findViewById(R.id.rel_drawer);
        rel_header = (RelativeLayout) findViewById(R.id.rel_nav_header);
        rel_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WencenterActivity.this, PersonalCenterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("uid", User.getLoginUser(getApplicationContext()).getUid());
                bundle.putString("userName", User.getLoginUser(getApplicationContext()).getUserName());
                bundle.putString("avatarFile", User.getLoginUser(getApplicationContext()).getAvatarFile());
                bundle.putBoolean("isOwner", true);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void navigationDrawerItemSelected(final int id) {
        mDrawerLayout.closeDrawer(rel_nav);
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment;
                switch (id) {
                    case 0:
                        fragment = HomePageFragment.newInstances();
                        fragmentManager.beginTransaction().replace(R.id.coo_homepage_content, fragment).commit();
                        break;
                    case 1:
                        fragment = FoundFrgment.newInstances();
                        fragmentManager.beginTransaction().replace(R.id.coo_homepage_content, fragment).commit();
                        break;
                }
            }
        },300);
    }

    class NavAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return navStrings.length;
        }

        @Override
        public Object getItem(int position) {
            return navStrings[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(WencenterActivity.this).inflate(R.layout.item_homepage_nav, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image_nav);
            TextView textView = (TextView) convertView.findViewById(R.id.text_nav);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.mipmap.test_ic_dashboard);
                    textView.setText(navStrings[position]);
                    break;
                case 1:
                    imageView.setImageResource(R.mipmap.test_ic_event);
                    textView.setText(navStrings[position]);
                    break;
            }
            return convertView;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
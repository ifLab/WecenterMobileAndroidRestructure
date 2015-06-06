package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.fragment.HomePageFragment;
import org.iflab.wecentermobileandroidrestructure.model.User;


public class WencenterActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private RelativeLayout nav_header;
    private HomePageFragment homePageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_wencenter);
        findViews();
        setToolbar();
        //创建返回键，并实现打开关/闭监听
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.action_settings, R.string.action_settings) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                mAnimationDrawable.stop();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                mAnimationDrawable.start();
            }
        };
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);
        setupDrawerContent(navigationView);
        navigationDrawerItemSelected(0);
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
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        nav_header = (RelativeLayout) navigationView.findViewById(R.id.rel_nav_header);
        nav_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WencenterActivity.this, PersonalCenterActivity.class);
                intent.putExtra("uid", User.getLoginUser(getApplicationContext()).getUid());
                startActivity(intent);
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                setToolbarTitle("主页");
                                break;
                            case R.id.nav_messages:
                                setToolbarTitle("发现");
                                break;
                        }
                        return true;
                    }
                });

    }

    private void navigationDrawerItemSelected(int id) {
        homePageFragment = HomePageFragment.newInstances();
        getSupportFragmentManager().beginTransaction().replace(R.id.coo_homepage_content, homePageFragment).commit();
    }
}
package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.fragment.PhotoDetailFragment;
import org.iflab.wecentermobileandroidrestructure.ui.PhotoViewPager;

import java.util.ArrayList;

/**
 * Created by hcjcch on 15/6/20.
 */
public class PublishPhotoDetailActivity extends BaseActivity {
    private PhotoViewPager pager;
    private ArrayList<String> arrUri;
    private int position;
    private Intent intent;
    private Toolbar toolbar;
    private ArrayList<String> mDelUrls = new ArrayList<>();
    private ImagePagerAdapter adapter;
    private final String SAVE_INSTANCE_INDEX = "SAVE_INSTANCE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_photo_detail);
        pager = (PhotoViewPager) findViewById(R.id.pager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        intent = getIntent();
        arrUri = intent.getStringArrayListExtra("mArrayUri");
        position = intent.getIntExtra("mPagerPosition", 0);
        adapter = new ImagePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);
        setToolBar();
    }

    private void setToolBar() {
        toolbar.setTitle("");
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_INSTANCE_INDEX, pager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt(SAVE_INSTANCE_INDEX, position);
    }

    class ImagePagerAdapter extends FragmentPagerAdapter {

        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoDetailFragment.getInstance(arrUri.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoDetailFragment fragment = (PhotoDetailFragment) super.instantiateItem(container, position);
            fragment.setData(arrUri.get(position));
            return fragment;
        }

        @Override
        public int getCount() {
            return arrUri.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.photo_delete) {
            final int selectPos = pager.getCurrentItem();
            new AlertDialog.Builder(this)
                    .setTitle("图片")
                    .setMessage("确定删除？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String s = arrUri.remove(selectPos);
                            mDelUrls.add(s);
                            if (arrUri.isEmpty()) {
                                onBackPressed();
                            } else {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDelUrls.isEmpty()) {
            setResult(RESULT_CANCELED);
        } else {
            Intent intent = new Intent();
            intent.putExtra("mDelUrls", mDelUrls);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

}
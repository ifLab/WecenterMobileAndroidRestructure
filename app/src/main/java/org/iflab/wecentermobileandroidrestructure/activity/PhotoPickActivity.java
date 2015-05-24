package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.FolderAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.PhotoPickGridAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.PhotolGridAllAdapter;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfoExtra;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by hcjcch on 15/5/22.
 */

public class PhotoPickActivity extends BaseActivity implements LoaderManager.LoaderCallbacks {
    private PhotoPickGridAdapter photoPickGridAdapter;
    private GridView photoGridview;
    private ListView photoFolderlistView;
    private String allPhotos = "所有图片";
    private FolderAdapter folderAdapter;
    private int mMaxPick = 6;
    private TextView txtPhotoFolder;
    private int mFolderId = 0;
    private MenuItem mMenuItem;
    //    private FrameLayout photoFolderParent;
    private ArrayList<ImageInfo> mPickData = new ArrayList<>();
    private String[] projection = {
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
            MediaStore.Images.ImageColumns.WIDTH,
            MediaStore.Images.ImageColumns.HEIGHT
    };
    public static DisplayImageOptions optionsImage = new DisplayImageOptions
            .Builder()
            .showImageOnLoading(R.mipmap.test_ic_default_image)
            .showImageForEmptyUri(R.mipmap.test_image_not_exist)
            .showImageOnFail(R.mipmap.test_image_not_exist)
            .cacheInMemory(true)
            .cacheOnDisk(false)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_pick);
        getMaxPhoto();
        findViews();
        initListView();
        initGridView();
        setViews();
        setToolBar();
    }

    private void getMaxPhoto() {
        Intent intent = getIntent();
        mMaxPick = intent.getIntExtra(PublishAnswerArticle.EXTRA_MAX, 6);
    }

    private void setViews() {
        txtPhotoFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoFolderlistView.getVisibility() == View.VISIBLE) {
                    hideFolderList();
                } else {
                    showFolderList();
                }
            }
        });
        photoFolderlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                folderAdapter.setSelect((int) id);
                String folderName = folderAdapter.getSelect();
                txtPhotoFolder.setText(folderName);
                hideFolderList();
                if (mFolderId != position) {
                    getLoaderManager().destroyLoader(mFolderId);
                    mFolderId = position;
                }
                getLoaderManager().initLoader(mFolderId, null, PhotoPickActivity.this);
            }
        });
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("图片");
//        toolbar.setNavigationIcon();
        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.abc_);
        setSupportActionBar(toolbar);
    }

    private void initListView() {
        final String[] needInfos = {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
        };
        LinkedHashMap<String, Integer> mNames = new LinkedHashMap<>();
        LinkedHashMap<String, ImageInfo> mData = new LinkedHashMap<>();
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, needInfos, "", null, MediaStore.MediaColumns.DATE_ADDED + " DESC");
        while (cursor.moveToNext()) {
            String name = cursor.getString(2);
            if (!mNames.containsKey(name)) {
                mNames.put(name, 1);
                ImageInfo imageInfo = new ImageInfo(cursor.getString(1));
                mData.put(name, imageInfo);
            } else {
                int newCount = mNames.get(name) + 1;
                mNames.put(name, newCount);
            }
        }
        ArrayList<ImageInfoExtra> mFolderData = new ArrayList<>();

        if (cursor.moveToFirst()) {
            ImageInfo imageInfo = new ImageInfo(cursor.getString(1));
            int allImagesCount = cursor.getCount();
            mFolderData.add(new ImageInfoExtra(allPhotos, imageInfo, allImagesCount));
        }

        for (String item : mNames.keySet()) {
            ImageInfo info = mData.get(item);
            Integer count = mNames.get(item);
            mFolderData.add(new ImageInfoExtra(item, info, count));
        }
        cursor.close();
        folderAdapter = new FolderAdapter(mFolderData);
        photoFolderlistView.setAdapter(folderAdapter);
    }

    private void findViews() {
        photoGridview = (GridView) findViewById(R.id.gridView);
        photoFolderlistView = (ListView) findViewById(R.id.list_photo_pick);
        txtPhotoFolder = (TextView) findViewById(R.id.txt_photo_folder);
//        photoFolderParent = (FrameLayout) findViewById(R.id.listViewParent);
    }

    private void initGridView() {
        getLoaderManager().initLoader(0, null, this);
    }

    private void showFolderList() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.pickphoto_listview_up);
//        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.pickphoto_listview_fade_in);
        photoFolderlistView.startAnimation(animation);
//        photoFolderParent.startAnimation(fadeIn);
        photoFolderlistView.setVisibility(View.VISIBLE);
    }

    private void hideFolderList() {
        Animation animation = AnimationUtils.loadAnimation(PhotoPickActivity.this, R.anim.pickphoto_listview_down);
//        Animation fadeOut = AnimationUtils.loadAnimation(PhotoPickActivity.this, R.anim.pickphoto_listview_fade_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                photoFolderParent.setVisibility(View.INVISIBLE);
                photoFolderlistView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        photoFolderlistView.startAnimation(animation);
//        photoFolderParent.startAnimation(fadeOut);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String where = "";
        if (!isAllPhotoMode()) {
            String select = ((FolderAdapter) photoFolderlistView.getAdapter()).getSelect();
            where = String.format("%s='%s'",
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    select
            );
        } else {
            where = "";
        }
        return new CursorLoader(
                this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                where,
                null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (isAllPhotoMode()) {
            photoPickGridAdapter = new PhotolGridAllAdapter(this, (Cursor) data, false, this);
        } else {
            photoPickGridAdapter = new PhotoPickGridAdapter(this, (Cursor) data, false, this);
        }
        photoGridview.setAdapter(photoPickGridAdapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        photoPickGridAdapter.swapCursor(null);
    }

    private boolean isAllPhotoMode() {
        return mFolderId == 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_pick, menu);
        mMenuItem = menu.getItem(0);
        updatePickCount();
        return true;
    }

    private void updatePickCount() {
        String format = "完成(%d/%d)";
        mMenuItem.setTitle(String.format(format, mPickData.size(), mMaxPick));

        //String formatPreview = "预览(%d/%d)";
        // mPreView.setText(String.format(formatPreview, mPickData.size(), mMaxPick));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_finish) {
            send();
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addPicked(String path) {
        if (!isPicked(path)) {
            mPickData.add(new ImageInfo(path));
        }
    }

    public boolean isPicked(String path) {
        for (ImageInfo item : mPickData) {
            if (item.path.equals(path)) {
                return true;
            }
        }

        return false;
    }

    private void removePicked(String path) {
        for (int i = 0; i < mPickData.size(); ++i) {
            if (mPickData.get(i).path.equals(path)) {
                mPickData.remove(i);
                return;
            }
        }
    }

    public void clickPhotoItem(View v) {
        GridViewCheckTag tag = (GridViewCheckTag) v.getTag();
        if (((CheckBox) v).isChecked()) {
            if (mPickData.size() >= mMaxPick) {
                ((CheckBox) v).setChecked(false);
                String s = String.format("最多只能选择%d张", mMaxPick);
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                return;
            }

            addPicked(tag.path);
            tag.iconFore.setVisibility(View.VISIBLE);
        } else {
            removePicked(tag.path);
            tag.iconFore.setVisibility(View.INVISIBLE);
        }
        ((BaseAdapter) photoFolderlistView.getAdapter()).notifyDataSetChanged();
        updatePickCount();
    }

    public static class GridViewCheckTag {
        public View iconFore;
        public String path = "";

        public GridViewCheckTag(View iconFore) {
            this.iconFore = iconFore;
        }
    }

    private void send() {
        if (mPickData.isEmpty()) {
            setResult(Activity.RESULT_CANCELED);
        } else {
            Intent intent = new Intent();
            intent.putExtra("data", mPickData);
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }
}
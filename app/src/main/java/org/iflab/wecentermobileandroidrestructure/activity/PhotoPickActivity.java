package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.FolderAdapter;
import org.iflab.wecentermobileandroidrestructure.adapter.PhotoPickGridAdapter;
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
        findViews();
        initListView();
        //initGridView();
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
    }

    private void initGridView() {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String where = "";
        return new CursorLoader(
                this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                where,
                null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        System.out.println(data);
        photoPickGridAdapter = new PhotoPickGridAdapter(this, (Cursor) data, false);
        photoGridview.setAdapter(photoPickGridAdapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        photoPickGridAdapter.swapCursor(null);
    }
}
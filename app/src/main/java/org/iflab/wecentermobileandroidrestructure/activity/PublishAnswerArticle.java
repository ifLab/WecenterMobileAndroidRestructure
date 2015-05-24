package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.AttachmentGridAdapter;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;
import org.iflab.wecentermobileandroidrestructure.ui.AutoHeightGridView;

import java.util.ArrayList;

/**
 * Created by hcjcch on 15/5/23.
 */
public class PublishAnswerArticle extends BaseActivity {
    private AutoHeightGridView gridView;
    public static final int PHOTO_MAX_COUNT = 6;
    public static final int RESULT_REQUEST_PICK_PHOTO = 1;
    public static final String EXTRA_MAX = "EXTRA_MAX";
    public ArrayList<ImageInfo> photos = new ArrayList<>();
    private AttachmentGridAdapter attachmentGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        findViews();
        setViews();
    }

    private void findViews() {
        gridView = (AutoHeightGridView) findViewById(R.id.grid_attachment);
    }

    private void setViews() {
        attachmentGridAdapter = new AttachmentGridAdapter(photos);
        gridView.setAdapter(attachmentGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == photos.size()) {
                    startPhotoPickActivity();
                }
            }
        });
    }


    private void startPhotoPickActivity() {
        int count = PHOTO_MAX_COUNT - photos.size();
        if (count <= 0) {
            return;
        }
        Intent intent = new Intent(PublishAnswerArticle.this, PhotoPickActivity.class);
        intent.putExtra(EXTRA_MAX, count);
        startActivityForResult(intent, RESULT_REQUEST_PICK_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_REQUEST_PICK_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<ImageInfo> datas = (ArrayList<ImageInfo>) data.getSerializableExtra("data");
                photos.addAll(datas);
            }
            attachmentGridAdapter.notifyDataSetChanged();
        } else super.onActivityResult(requestCode, resultCode, data);
    }
}
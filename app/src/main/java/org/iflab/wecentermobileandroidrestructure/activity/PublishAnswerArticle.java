package org.iflab.wecentermobileandroidrestructure.activity;

import android.os.Bundle;

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
    public ArrayList<ImageInfo> photos = new ArrayList<>();
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
        gridView.setAdapter(new AttachmentGridAdapter(photos));
    }
}

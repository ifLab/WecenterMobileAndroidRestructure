package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.RequestParams;

import org.apmem.tools.layouts.FlowLayout;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.AttachmentGridAdapter;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.common.PhotoOperate;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.iflab.wecentermobileandroidrestructure.ui.AutoHeightGridView;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by hcjcch on 15/5/23.
 */
public class PublishAnswerArticleActivity extends BaseActivity {
    private AutoHeightGridView gridView;
    public static final int PHOTO_MAX_COUNT = 6;
    public static final int RESULT_REQUEST_PICK_PHOTO = 1;
    public static final String EXTRA_MAX = "EXTRA_MAX";
    public ArrayList<ImageInfo> photos = new ArrayList<>();
    private AttachmentGridAdapter attachmentGridAdapter;
    private FlowLayout topicFlowLayout;
    private EditText topicEditText;
    private String toipcString;
    private Button addTopicButton;
    private ArrayList<TextView> topics = new ArrayList<>();
    private static final String attach_access_key = MD5Transform.MD5(System.currentTimeMillis() + "");
    private String publishId;
    private PhotoOperate photoOperate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        photoOperate = new PhotoOperate(getApplicationContext());
        Intent intent = getIntent();
        publishId = "question";
        findViews();
        setViews();
    }

    private void findViews() {
        gridView = (AutoHeightGridView) findViewById(R.id.grid_attachment);
        topicFlowLayout = (FlowLayout) findViewById(R.id.flow_layout_topic);
        topicEditText = (EditText) findViewById(R.id.edt_topic);
        addTopicButton = (Button) findViewById(R.id.btn_add_topic);
    }

    private void setViews() {
        attachmentGridAdapter = new AttachmentGridAdapter(photos);
        gridView.setAdapter(attachmentGridAdapter);
        topicEditText.setText("");
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == photos.size()) {
                    startPhotoPickActivity();
                }
            }
        });
        addTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toipcString = topicEditText.getText().toString();
                if (toipcString.equals("") || toipcString == null) {
                    Toast.makeText(PublishAnswerArticleActivity.this, "请输入话题", Toast.LENGTH_SHORT).show();
                } else {
                    TextView button = new TextView(PublishAnswerArticleActivity.this);
                    button.setText(toipcString);
                    button.setBackground(getResources().getDrawable(R.drawable.public_topic));
                    button.setTextColor(Color.WHITE);
                    button.setPadding(10, 10, 10, 10);
                    topicFlowLayout.addView(button);
                    topicEditText.setText("");
                }
            }
        });
    }


    private void startPhotoPickActivity() {
        int count = PHOTO_MAX_COUNT - photos.size();
        if (count <= 0) {
            return;
        }
        Intent intent = new Intent(PublishAnswerArticleActivity.this, PhotoPickActivity.class);
        intent.putExtra(EXTRA_MAX, count);
        startActivityForResult(intent, RESULT_REQUEST_PICK_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_REQUEST_PICK_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<ImageInfo> datas = (ArrayList<ImageInfo>) data.getSerializableExtra("data");
                photos.addAll(datas);
                for (ImageInfo imageInfo : datas) {
                    Uri uri = Uri.parse(imageInfo.path);
                    try {
                        File out = photoOperate.scal(uri);
                        uploadAttachment(out);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            attachmentGridAdapter.notifyDataSetChanged();
        } else super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadAttachment(File attachment) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("id", publishId);
        params.put("attach_access_key", attach_access_key);
        params.put("qqfile", attachment);
        System.out.println(publishId + "   " + attach_access_key + "   " + attachment);
        AsyncHttpWecnter.loadData(PublishAnswerArticleActivity.this, RelativeUrl.ATTACHMENT_UPLOAD, params, AsyncHttpWecnter.Request.Post, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {

            }
        });
    }
}
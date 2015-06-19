package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by hcjcch on 15/5/23.
 */
public class PublishAnswerArticleActivity extends BaseActivity {
    private AutoHeightGridView gridView;
    public static final int PHOTO_MAX_COUNT = 6;
    public static final int RESULT_REQUEST_PICK_PHOTO = 1;
    public static final String EXTRA_MAX = "EXTRA_MAX";
    private AttachmentGridAdapter attachmentGridAdapter;
    private FlowLayout topicFlowLayout;
    private EditText topicEditText;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private String toipcString;
    private Button addTopicButton;
    private ArrayList<String> topics = new ArrayList<>();
    private static final String attach_access_key = MD5Transform.MD5(System.currentTimeMillis() + "");
    private String publishId;
    private PhotoOperate photoOperate;
    private ArrayList<PhotoData> mData = new ArrayList<>();
    private Vector<Integer> attachIds = new Vector<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        photoOperate = new PhotoOperate(getApplicationContext());
        Intent intent = getIntent();
        publishId = "question";
        findViews();
        setViews();
        setToolBars();
    }

    private void setToolBars() {
        toolbar.setTitle("发布");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findViews() {
        gridView = (AutoHeightGridView) findViewById(R.id.grid_attachment);
        topicFlowLayout = (FlowLayout) findViewById(R.id.flow_layout_topic);
        topicEditText = (EditText) findViewById(R.id.edt_topic);
        addTopicButton = (Button) findViewById(R.id.btn_add_topic);
        titleEditText = (EditText) findViewById(R.id.edt_title);
        descriptionEditText = (EditText) findViewById(R.id.edt_description);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setViews() {
        attachmentGridAdapter = new AttachmentGridAdapter(mData);
        gridView.setAdapter(attachmentGridAdapter);
        topicEditText.setText("");
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == mData.size()) {
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
                    topics.add(button.getText().toString());
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
        int count = PHOTO_MAX_COUNT - mData.size();
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
                for (ImageInfo imageInfo : datas) {
                    Uri uri = Uri.parse(imageInfo.path);
                    try {
                        File out = photoOperate.scal(uri);
                        uploadAttachment(out);
                        mData.add(new PhotoData(out));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            attachmentGridAdapter.notifyDataSetChanged();
        } else super.onActivityResult(requestCode, resultCode, data);
    }

    public static class PhotoData {
        Uri uri = Uri.parse("");
        String serviceUri = "";

        public PhotoData(File file) {
            uri = Uri.fromFile(file);
        }

        public PhotoData(PhotoDataSerializable data) {
            uri = Uri.parse(data.uriString);
            serviceUri = data.serviceUri;
        }

        public Uri getUri() {
            return uri;
        }
    }

    // 因为PhotoData包含Uri，不能直接序列化，所以有了这个类
    public static class PhotoDataSerializable implements Serializable {
        String uriString = "";
        String serviceUri = "";

        public PhotoDataSerializable(PhotoData data) {
            uriString = data.uri.toString();
            serviceUri = data.serviceUri;
        }
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
                try {
                    String attachid = response.getString("attach_id");
                    attachIds.add(Integer.parseInt(attachid));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void uploadWord() {
        RequestParams params = new RequestParams();
        String title = titleEditText.getText().toString();
        if (title.length() < 6) {
            Toast.makeText(PublishAnswerArticleActivity.this, "标题不能小于6个字", Toast.LENGTH_SHORT).show();
            titleEditText.requestFocus();
            return;
        }
        params.put("question_content", title);
        String description = descriptionEditText.getText().toString();
        if (description.length() < 6) {
            Toast.makeText(PublishAnswerArticleActivity.this, "描述不能小于6个字", Toast.LENGTH_SHORT).show();
            descriptionEditText.requestFocus();
            return;
        }
        for (int i : attachIds) {
            description += "[attach]" + i + "[/attach]" + "\n";
        }
        params.put("question_detail", description);
        params.put("attach_access_key", attach_access_key);
        if (topics.size() != 0) {
            String topicsUpload = "";
            for (String topic : topics) {
                topicsUpload = topicsUpload + topic + ",";
            }
            topicsUpload = topicsUpload.substring(0, topicsUpload.length());
            params.put("topics", topicsUpload);
        }
        AsyncHttpWecnter.loadData(PublishAnswerArticleActivity.this, RelativeUrl.UPLOAD_QUESTION, params, AsyncHttpWecnter.Request.Post, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                try {
                    String question_id = response.getString("question_id");
                    if (question_id != null) {
                        Toast.makeText(PublishAnswerArticleActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.publish_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.publish_finish) {
            uploadWord();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
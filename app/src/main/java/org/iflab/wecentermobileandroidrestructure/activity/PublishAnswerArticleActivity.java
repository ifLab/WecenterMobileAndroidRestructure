package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import org.iflab.wecentermobileandroidrestructure.tools.RecycleBitmapInLayout;
import org.iflab.wecentermobileandroidrestructure.ui.AutoHeightGridView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hcjcch on 15/5/23.
 */
public class PublishAnswerArticleActivity extends BaseActivity {

    public static final String PUBLISH_QUESTION = "publih_question";
    public static final String PUBLISH_ARTICLE = "publish_article";
    public static final String PUBLISH_ANSWER = "publish_answer";

    private String publishType;
    public static final String PUBLISH_TYPE_INTENT = "publish_type";
    public static final String QUESTION_ID_INTENT = "question_id";
    private AutoHeightGridView gridView;
    public static final int PHOTO_MAX_COUNT = 6;
    public static final int RESULT_REQUEST_PICK_PHOTO = 1;
    public static final int RESULT_REQUEST_IMAGE = 2;
    public static final String EXTRA_MAX = "EXTRA_MAX";
    private AttachmentGridAdapter attachmentGridAdapter;
    private FlowLayout topicFlowLayout;
    private EditText topicEditText;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private String topicString;
    private Button addTopicButton;
    private ArrayList<String> topics = new ArrayList<>();
    private static final String attach_access_key = MD5Transform.MD5(System.currentTimeMillis() + "");
    private String publishId;
    private PhotoOperate photoOperate;
    private ArrayList<PhotoData> mData = new ArrayList<>();
    private Vector<String> attachIds = new Vector<>();
    private Toolbar toolbar;
    private Hashtable<String, String> hashtable = new Hashtable<>();
    @Bind(R.id.rel_topic)
    RelativeLayout rel_topic;

    @Bind(R.id.rel_description)
    RelativeLayout rel_description;

    @Bind(R.id.txt_title)
    TextView txt_title;

    private String url;
    private Intent intent;
    private int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        photoOperate = new PhotoOperate(getApplicationContext());
        intent = getIntent();
        publishType = intent.getStringExtra(PUBLISH_TYPE_INTENT);
        setPublishId();
        findViews();
        setViews();
        setToolBars();
    }

    private void setPublishId() {
        switch (publishType) {
            case PUBLISH_ANSWER:
                publishId = "answer";
                url = RelativeUrl.PUBLISH_ANSWER;
                questionId = intent.getIntExtra(QUESTION_ID_INTENT, -1);
                break;
            case PUBLISH_ARTICLE:
                publishId = "article";
                break;
            case PUBLISH_QUESTION:
                publishId = "question";
                url = RelativeUrl.UPLOAD_QUESTION;
                break;
        }
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
        attachmentGridAdapter = new AttachmentGridAdapter(mData,getApplicationContext());
        gridView.setAdapter(attachmentGridAdapter);
        if (publishType.equals(PUBLISH_ANSWER)) {
            rel_topic.setVisibility(View.GONE);
            rel_description.setVisibility(View.GONE);
            txt_title.setText("描述");
        }
        topicEditText.setText("");
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == mData.size()) {
                    startPhotoPickActivity();
                } else {
                    Intent intent = new Intent(PublishAnswerArticleActivity.this, PublishPhotoDetailActivity.class);
                    ArrayList<String> arrayUri = new ArrayList<>();
                    for (PhotoData item : mData) {
                        arrayUri.add(item.uri.toString());
                    }
                    intent.putExtra("mArrayUri", arrayUri);
                    intent.putExtra("mPagerPosition", position);
                    startActivityForResult(intent, RESULT_REQUEST_IMAGE);
                }
            }
        });
        addTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topicString = topicEditText.getText().toString();
                if (topicString.equals("") || topicString == null) {
                    Toast.makeText(PublishAnswerArticleActivity.this, "请输入话题", Toast.LENGTH_SHORT).show();
                } else {
                    TextView button = new TextView(PublishAnswerArticleActivity.this);
                    button.setText(topicString);
                    topics.add(topicString);
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
                        mData.add(new PhotoData(uri));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            attachmentGridAdapter.notifyDataSetChanged();
        } else if (requestCode == RESULT_REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> delUris = data.getStringArrayListExtra("mDelUrls");
                for (String item : delUris) {
                    attachIds.remove(hashtable.get(item));
                    hashtable.remove(item);
                    for (int i = 0; i < mData.size(); ++i) {
                        if (mData.get(i).uri.toString().equals(item)) {
                            mData.remove(i);
                        }
                    }
                }
                attachmentGridAdapter.notifyDataSetChanged();
            }

        } else super.onActivityResult(requestCode, resultCode, data);
    }

    public static class PhotoData {
        Uri uri = Uri.parse("");
        String serviceUri = "";

        public PhotoData(File file) {
            uri = Uri.fromFile(file);
        }

        public PhotoData(Uri file) {
            uri = file;
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

    private void uploadAttachment(final File attachment) throws FileNotFoundException {
        final RequestParams params = new RequestParams();
        params.put("id", publishId);
        params.put("attach_access_key", attach_access_key);
        params.put("qqfile", attachment);
        System.out.println(publishId + "   " + attach_access_key + "   " + attachment);
        AsyncHttpWecnter.loadData(getApplicationContext(), RelativeUrl.ATTACHMENT_UPLOAD, params, AsyncHttpWecnter.Request.Post, new UploadNetWork(attachIds,hashtable,attachment));
    }

    private static class UploadNetWork extends NetWork{
        private  final WeakReference<Vector<String>> attachIds ;
        private  final WeakReference<Hashtable<String, String>> hashtable;
        private  final WeakReference<File> attachment ;

        public UploadNetWork(Vector<String> attachIds,Hashtable<String, String> hashtable,File attachment){
            this.attachIds = new WeakReference<>(attachIds);
            this.hashtable = new WeakReference<>(hashtable);
            this.attachment = new WeakReference<>(attachment);
        }

        @Override
        public void parseJson(JSONObject response) {
            if(attachIds.get() != null && hashtable.get() != null && attachment.get() != null) {
                try {
                    String attachid = response.getString("attach_id");
                    attachIds.get().add(attachid);
                    hashtable.get().put(Uri.fromFile(attachment.get()).toString(), attachid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadWord() {
        RequestParams params = new RequestParams();
        if (publishType.equals(PUBLISH_ANSWER)) {
            String title = titleEditText.getText().toString();
            if (title.length() < 6) {
                Toast.makeText(PublishAnswerArticleActivity.this, "描述不能小于6个字", Toast.LENGTH_SHORT).show();
                titleEditText.requestFocus();
                return;
            }
            for (String i : attachIds) {
                title += "[attach]" + i + "[/attach]" + "\n";
            }
            params.put("answer_content", title);
            params.put("attach_access_key", attach_access_key);
            params.put("question_id", questionId);
        } else {
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
            for (String i : attachIds) {
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
        }
        AsyncHttpWecnter.loadData(getApplicationContext(), url, params, AsyncHttpWecnter.Request.Post, new NetWork() {
            @Override
            public void parseJson(JSONObject response) {
                try {
                    String question_id;
                    if (publishType.equals(PUBLISH_ANSWER)) {
                        question_id = response.getString("answer_id");
                    } else {
                        question_id = response.getString("question_id");
                    }
                    if (question_id != null) {
                        Toast.makeText(PublishAnswerArticleActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        PublishAnswerArticleActivity.this.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AsyncHttpWecnter.cancelAllRequest();

        hashtable.clear();
        topics.clear();
        mData.clear();
        attachIds.clear();

        RecycleBitmapInLayout.getInstance(false).recycle(gridView);
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
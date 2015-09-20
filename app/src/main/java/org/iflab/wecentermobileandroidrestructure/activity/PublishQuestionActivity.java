package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishQuestionActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txt_desc)
    TextView txtDesc;
    @Bind(R.id.edt_desc)
    EditText edtDesc;
    @Bind(R.id.txt_attachment)
    TextView txtAttachment;
    @Bind(R.id.grid_attachment)
    AutoHeightGridView gridView;
    @Bind(R.id.linear_desc)
    LinearLayout linearDesc;

    public static final int PHOTO_MAX_COUNT = 6;
    public static final int RESULT_REQUEST_PICK_PHOTO = 1;
    public static final int RESULT_REQUEST_IMAGE = 2;
    public static final String EXTRA_MAX = "EXTRA_MAX";
    private ArrayList<PublishAnswerArticleActivity.PhotoData> mData = new ArrayList<>();
    private ArrayList<String> attachIds = new ArrayList<>();
    private ArrayMap<String, String> hashtable = new ArrayMap<>();
    private AttachmentGridAdapter attachmentGridAdapter;
    private String publishId;
    private static final String attach_access_key = MD5Transform.MD5(System.currentTimeMillis() + "");
    private PhotoOperate photoOperate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_question);

        findViews();
        setToolBars();

    }

    private void findViews() {
        ButterKnife.bind(this);
    }

    private void setView(){
        attachmentGridAdapter = new AttachmentGridAdapter(mData,PublishQuestionActivity.this);
        gridView.setAdapter(attachmentGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == mData.size()) {
                    startPhotoPickActivity();
                } else {
                    Intent intent = new Intent(PublishQuestionActivity.this, PublishPhotoDetailActivity.class);
                    ArrayList<String> arrayUri = new ArrayList<>();
                    for (PublishAnswerArticleActivity.PhotoData item : mData) {
                        arrayUri.add(item.uri.toString());
                    }
                    intent.putExtra("mArrayUri", arrayUri);
                    intent.putExtra("mPagerPosition", position);
                    startActivityForResult(intent, RESULT_REQUEST_IMAGE);
                }
            }
        });
    }


    private void setToolBars() {
        toolbar.setTitle("发布回答");//设置Toolbar标题
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

    private void startPhotoPickActivity() {
        int count = PHOTO_MAX_COUNT - mData.size();
        if (count <= 0) {
            return;
        }
        Intent intent = new Intent(PublishQuestionActivity.this, PhotoPickActivity.class);
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
                        mData.add(new PublishAnswerArticleActivity.PhotoData(uri));
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

    private void uploadAttachment(final File attachment) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("id", publishId);
        params.put("attach_access_key", attach_access_key);
        params.put("qqfile", attachment);
        System.out.println(publishId + "   " + attach_access_key + "   " + attachment);

        AsyncHttpWecnter.loadData(PublishQuestionActivity.this, RelativeUrl.ATTACHMENT_UPLOAD, params, AsyncHttpWecnter.Request.Post,new UploadNetWork(attachIds,hashtable,attachment));
    }


    private static class UploadNetWork extends NetWork{
        private  final WeakReference<ArrayList<String>> attachIds ;
        private  final WeakReference<ArrayMap<String, String>> hashtable;
        private  final WeakReference<File> attachment ;

        public UploadNetWork(ArrayList<String> attachIds,ArrayMap<String, String> hashtable,File attachment){
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
}

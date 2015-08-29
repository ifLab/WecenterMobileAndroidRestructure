package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.dd.CircularProgressButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.ImageInfo;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.tools.CameraPhotoUtil;
import org.iflab.wecentermobileandroidrestructure.tools.Global;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by hcjcch on 15/6/6.
 */

public class PersonalCenterEditActivity extends BaseActivity {

    private Uri fileUri;
    private final int RESULT_REQUEST_PHOTO = 1005;
    private final int RESULT_REQUEST_PHOTO_CROP = 1006;
    private TextView userImageSelect;
    private Uri fileCropUri;
    private ImageView imgUser;
    private TextView birthDaySelect;
    private Calendar calendar;
    private TextView txtMale;
    private Bundle bundle;
    private Intent intent;
    private RadioGroup radioGroup;
    private RadioButton radioMale;
    private int sexCheck;
    private TextInputLayout txtInputSignature;
    private TextInputLayout txtinputUserName;
    private RadioButton radioFeMale;
    private RadioButton radioNo;
    private RelativeLayout RelMars;
    private CircularProgressBar progress;
    private CircularProgressButton save;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_edit);
        calendar = Calendar.getInstance();
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        user = User.getLoginUser(getApplicationContext());
        setToolBar();
        findViews();
        getUserInformation();
        setViews();
    }


    private void findViews() {
        userImageSelect = (TextView) findViewById(R.id.txt_user_img);
        imgUser = (ImageView) findViewById(R.id.img_user);
        birthDaySelect = (TextView) findViewById(R.id.txt_birthday_select);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_sex);
        radioMale = (RadioButton) findViewById(R.id.radio_sex_male);
        txtInputSignature = (TextInputLayout) findViewById(R.id.txt_signature);
        txtinputUserName = (TextInputLayout) findViewById(R.id.txt_user_name);
        radioFeMale = (RadioButton) findViewById(R.id.radio_sex_female);
        radioNo = (RadioButton) findViewById(R.id.radio_sex_no);
        RelMars = (RelativeLayout) findViewById(R.id.rel_marz);
        progress = (CircularProgressBar) findViewById(R.id.progress);
        save = (CircularProgressButton) findViewById(R.id.btn_cave_user_information);
        save.setIndeterminateProgressMode(true);
    }

    private void setViews() {
        //设置用户头像
        ImageLoader.getInstance().displayImage(RelativeUrl.AVATAR + user.getAvatarFile(), imgUser);
        userImageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserImage();
            }
        });

        birthDaySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PersonalCenterEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String dateString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        birthDaySelect.setText(dateString);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_sex_male:
                        sexCheck = 1;
                        break;
                    case R.id.radio_sex_female:
                        sexCheck = 2;
                        break;
                    case R.id.radio_sex_no:
                        sexCheck = 3;
                        break;
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadOtherInformation();
            }
        });
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#00ffffff"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("信息修改");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalCenterEditActivity.this.setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void camera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = CameraPhotoUtil.getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, RESULT_REQUEST_PHOTO);
    }

    private void photo() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_REQUEST_PHOTO);
    }

    private void setUserImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更换头像")
                .setItems(new String[]{"相机", "图片"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            camera();
                        } else {
                            photo();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_REQUEST_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    fileUri = data.getData();
                }
                fileCropUri = CameraPhotoUtil.getOutputMediaFileUri();
                cropImageUri(fileUri, fileCropUri, 640, 640, RESULT_REQUEST_PHOTO_CROP);
            }
        } else if (requestCode == RESULT_REQUEST_PHOTO_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    String filePath = Global.getPath(this, fileCropUri);
                    ImageLoader.getInstance().displayImage(ImageInfo.pathAddPreFix(filePath), imgUser, PhotoPickActivity.optionsImage);
                    if (filePath == null) {
                        Toast.makeText(PersonalCenterEditActivity.this, "文件失剪裁败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    File file = new File(filePath);
                    RequestParams params = new RequestParams();
                    try {
                        params.put("user_avatar", file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    uploadAvatar(params);
                } catch (Exception e) {

                }
            }
        }
    }

    private void cropImageUri(Uri uri, Uri outputUri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    private void uploadAvatar(RequestParams params) {
        AsyncHttpWecnter.post(RelativeUrl.USER_IMG_EDIT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(json);
                if (jsonProgress) return;
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONObject rsm = jsonObject.getJSONObject("rsm");
                    String priview = rsm.getString("preview");
                    if (priview != null) {
                        //TODO 上传成功
                        Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

        });
    }

    private void uploadOtherInformation() {
        RequestParams params = new RequestParams();
        params.put("uid", user.getUid());
        final String userName = txtinputUserName.getEditText().getText().toString();
        if (userName.equals("")) {
            txtinputUserName.setError("用户名不能为空");
            txtinputUserName.requestFocus();
            return;
        } else {
            txtinputUserName.setError("");
        }
        params.put("user_name", userName);
        params.put("sex", sexCheck);
        String signature = txtInputSignature.getEditText().getText().toString();
        if (signature.equals("")) {
            txtInputSignature.setError("不能为空");
            txtInputSignature.requestFocus();
            return;
        } else {
            txtInputSignature.setError("");
        }
        params.put("signature", signature);
        String a = birthDaySelect.getText().toString();
        long date = Global.Date2TimeStamp(birthDaySelect.getText().toString());
        if (date == -1) {
            //TODO 选择日期无效
        } else {
            params.put("birthday", date);
        }
        AsyncHttpWecnter.post(RelativeUrl.USER_INFORMATION_EDIT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                save.setProgress(50);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(json);
                if (jsonProgress) return;
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(new String(responseBody));
                    String rsm = jsonObject.getString("rsm");
                    if (rsm.equals("success")) {
                        //TODO 成功
                        updateUser(userName);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PersonalCenterEditActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                save.setProgress(100);
                                PersonalCenterEditActivity.this.setResult(RESULT_OK);
                                finish();
                            }
                        }, 1000);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                save.setProgress(-1);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    private void getUserInformation() {
        onGetUserInformation();
        txtinputUserName.getEditText().setText(user.getUserName());
        RequestParams params = new RequestParams();
        params.put("uid", user.getUid());
        AsyncHttpWecnter.get(RelativeUrl.USER_INFO_GET_EDIT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(json);
                if (jsonProgress) return;
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONArray rsm = jsonObject.getJSONArray("rsm");
                    JSONObject user = rsm.getJSONObject(0);
                    String sex = user.getString("sex");
                    String birthday = user.getString("birthday");
                    String signature = user.getString("signature");
                    if (birthday.contains("-")) {
                        //TODO 不设置时间
                        birthDaySelect.setText("");
                    } else {
                        String date = Global.TimeStamp2Date(birthday, "yyyy-MM-dd");
                        birthDaySelect.setText(date);
                    }
                    if (sex.equals("1")) {
                        radioMale.setChecked(true);
                    } else if (sex.equals("2")) {
                        radioFeMale.setChecked(true);
                    } else {
                        radioNo.setChecked(true);
                    }
                    txtInputSignature.getEditText().setText(signature);
                    afterGetUserInformation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                afterGetUserInformation();
                super.onFinish();
            }
        });
    }

    private void updateUser(String userName) {
        user.setUserName(userName);
        User.save(PersonalCenterEditActivity.this, user);
    }

    private void onGetUserInformation() {
        txtinputUserName.getEditText().setEnabled(false);
        txtInputSignature.getEditText().setEnabled(false);
        radioGroup.setEnabled(false);
        birthDaySelect.setEnabled(false);
    }

    private void afterGetUserInformation() {
        txtinputUserName.getEditText().setEnabled(true);
        txtInputSignature.getEditText().setEnabled(true);
        radioGroup.setEnabled(true);
        birthDaySelect.setEnabled(true);
        RelMars.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
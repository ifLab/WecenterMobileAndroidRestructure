package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import net.qiujuer.genius.app.BlurKit;

import org.apache.http.Header;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by hcjcch on 15/5/15.
 */
public class LoginActivity extends BaseActivity {

    public static int REGISTERCODE = 9;
    private ShimmerFrameLayout container;
    private Button btnLogin;
    private EditText userName;
    private EditText passWord;
    private Button btnRegister;
    private CircularProgressBar progressBar;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
//        Log.v("cookie", myCookieStore.getCookies().size() + "");
        if (myCookieStore.getCookies().size() > 0) {
            AsyncHttpWecnter.setCookieStore(myCookieStore);
            startActivity(new Intent(LoginActivity.this, WencenterActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        findViews();
        setViews();
        setListeners();

    }

    protected void findViews() {
        container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        btnLogin = (Button) findViewById(R.id.btn_login);
        userName = (EditText) findViewById(R.id.edt_user_name);
        passWord = (EditText) findViewById(R.id.edt_passwd);
        btnRegister = (Button)findViewById(R.id.btn_register);
        progressBar = (CircularProgressBar)findViewById(R.id.progress);
    }


    protected void setViews() {
        container.startShimmerAnimation();
    }

    protected void setListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,REGISTERCODE);
            }
        });
    }

    private void login() {
        String usernameString = userName.getText().toString();

        if (usernameString.equals("")) {
            toast("用户名不能为空");
            return;
        }
        String passWordString = passWord.getText().toString();
        if (passWordString.equals("")) {
            toast("密码不能为空");
            return;
        }
//        userName.setText(usernameString);
//        passWord.setText(passWordString);
        RequestParams params = new RequestParams();
        params.put("user_name", usernameString);
        params.put("password", passWordString);

        final PersistentCookieStore cookieStore = new PersistentCookieStore(getApplicationContext());
        AsyncHttpWecnter.setCookieStore(cookieStore);

        AsyncHttpWecnter.post(RelativeUrl.USER_LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(json);
                if (jsonProgress) {
                    cookieStore.clear();
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONObject rsm = jsonObject.getJSONObject("rsm");
                    User user = new User(rsm);
                    User.save(getApplicationContext(), user);
                    startActivity(new Intent(LoginActivity.this, WencenterActivity.class));

                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                cookieStore.clear();
                if(responseBody == null){
                    Toast.makeText(getApplicationContext(), "请检查网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), new String(responseBody), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTERCODE){
            if(data != null)
                userName.setText(data.getStringExtra("user_name"));
        }else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if(bitmap != null){
            if(!bitmap.isRecycled()){
                bitmap.recycle();
            }
        }
        super.onDestroy();
    }
}
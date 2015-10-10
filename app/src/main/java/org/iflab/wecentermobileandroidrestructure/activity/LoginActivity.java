package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import net.qiujuer.genius.app.BlurKit;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.model.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hcjcch on 15/5/15.
 */
public class LoginActivity extends BaseActivity {
    private RelativeLayout imageView;
    private ShimmerFrameLayout container;
    private Button btnLogin;
    private EditText userName;
    private EditText passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(User.getLoginUser(getApplicationContext()).getUid() != -1){
            startActivity(new Intent(LoginActivity.this, WencenterActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        findViews();
        setViews();
        setListeners();

    }

    protected void findViews() {
        imageView = (RelativeLayout) findViewById(R.id.image_blur_jni_bitmap);
        container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        btnLogin = (Button) findViewById(R.id.btn_login);
        userName = (EditText) findViewById(R.id.edt_user_name);
        passWord = (EditText) findViewById(R.id.edt_passwd);
    }

    protected void setViews() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_background);
        bitmap = BlurKit.blurNatively(bitmap, 100, false);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        imageView.setBackground(drawable);
        container.startShimmerAnimation();
    }

    protected void setListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String usernameString = userName.getText().toString();
//        usernameString = user.getUserName();
        usernameString = "Lyn";

        if (usernameString.equals("")) {
            toast("用户名不能为空");
            return;
        }
        String passWordString = passWord.getText().toString();
        passWordString = "935600f12";
        if (passWordString.equals("")) {
            toast("密码不能为空");
            return;
        }
        userName.setText(usernameString);
        passWord.setText(passWordString);
        RequestParams params = new RequestParams();
        params.put("user_name", usernameString);
        params.put("password", passWordString);
        AsyncHttpWecnter.post(RelativeUrl.USER_LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                boolean jsonProgress = jsonPreproccess(json);
                if (jsonProgress) return;
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONObject rsm = jsonObject.getJSONObject("rsm");
                    User user = new User(rsm);
                    User.save(getApplicationContext(), user);
                    startActivity(new Intent(LoginActivity.this, WencenterActivity.class));
//                    startActivity(new Intent(LoginActivity.this, QuestionDetailActivity.class));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
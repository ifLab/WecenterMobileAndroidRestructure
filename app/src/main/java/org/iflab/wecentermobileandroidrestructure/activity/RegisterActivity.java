package org.iflab.wecentermobileandroidrestructure.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.qiujuer.genius.app.BlurKit;

import org.apache.http.Header;
import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.common.NetWork;
import org.iflab.wecentermobileandroidrestructure.http.AsyncHttpWecnter;
import org.iflab.wecentermobileandroidrestructure.http.RelativeUrl;
import org.iflab.wecentermobileandroidrestructure.tools.Global;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.edit_name)
    EditText edit_name;

    @Bind(R.id.edit_pwd)
    EditText edit_pwd;

    @Bind(R.id.edit_confirm_pwd)
    EditText edit_confirm_pwd;

    @Bind(R.id.edit_email)
    EditText edit_email;

    @Bind(R.id.btn_register)
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        setView();
        setUpToolBar(toolbar);

    }

    private void setView() {
        toolbar.setTitle("注册");
    }

    public void onRegister(View view){
        String name = edit_name.getText().toString();
        String pwd = edit_pwd.getText().toString();
        String confirmPwd = edit_confirm_pwd.getText().toString();
        String email = edit_email.getText().toString();

        if(name.length() > 10){
            Toast.makeText(getApplicationContext(),"用户名过长",Toast.LENGTH_SHORT).show();
            return;
        }

        if(pwd.length() < 6 || confirmPwd.length() < 6){
            Toast.makeText(getApplicationContext(),"密码长度必须大于6位",Toast.LENGTH_SHORT).show();
            edit_confirm_pwd.setText("");
            edit_pwd.setText("");
            return;
        }

        if(!pwd.equals(confirmPwd)){
            Toast.makeText(getApplicationContext(),"密码不一致",Toast.LENGTH_SHORT).show();
            edit_confirm_pwd.setText("");
            edit_pwd.setText("");
            return;
        }

        if(!Global.isEmail(email)){
            Toast.makeText(getApplicationContext(),"邮箱格式不正确",Toast.LENGTH_SHORT).show();
            edit_email.setText("");
            return;
        }

        btn_register.setEnabled(false);

        RequestParams params = new RequestParams();
        params.put("user_name",name);
        params.put("password",pwd);
        params.put("email",email);
        AsyncHttpWecnter.post(RelativeUrl.REGISTER, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Intent intent = null;
                try {
                    JSONObject obj  = new JSONObject(new String(responseBody));
                    if(!obj.getString("err").equals("null")){
                        Toast.makeText(getApplicationContext(),obj.getString("err"),Toast.LENGTH_SHORT).show();
                        btn_register.setEnabled(true);
                        return;
                    }
                    intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("user_name",obj.optJSONObject("rsm").getString("user_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(intent != null){
                    setResult(LoginActivity.REGISTERCODE,intent);
                    finish();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                btn_register.setEnabled(true);
                Toast.makeText(getApplicationContext(),"请求失败",Toast.LENGTH_SHORT).show();
            }
        });


    }

}

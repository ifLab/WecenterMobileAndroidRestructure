package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import net.qiujuer.genius.app.BlurKit;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.tools.BlurBitmap;

/**
 * Created by hcjcch on 15/5/15.
 */
public class LoginActivity extends BaseActivity {
    private RelativeLayout imageView;
    private ShimmerFrameLayout container;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setViews();
        setListeners();
    }

    protected void findViews() {
        imageView = (RelativeLayout) findViewById(R.id.image_blur_jni_bitmap);
        container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        btnLogin = (Button) findViewById(R.id.btn_login);
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

            }
        });
    }
}
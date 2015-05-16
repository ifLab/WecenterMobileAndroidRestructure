package org.iflab.wecentermobileandroidrestructure.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import net.qiujuer.genius.app.BlurKit;

import org.iflab.wecentermobileandroidrestructure.R;


public class WencenterActivity extends BaseActivity {

    private RelativeLayout imageView;
    private ShimmerFrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wencenter);
        imageView = (RelativeLayout)findViewById(R.id.image_blur_jni_bitmap);
        container = (ShimmerFrameLayout)findViewById(R.id.shimmer_view_container);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.login_background);
        bitmap = BlurKit.blurNatively(bitmap,100,false);
        Drawable drawable = new BitmapDrawable(getResources(),bitmap);
        imageView.setBackground(drawable);
        container.startShimmerAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wencenter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

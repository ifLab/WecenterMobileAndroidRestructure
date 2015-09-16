package org.iflab.wecentermobileandroidrestructure.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.iflab.wecentermobileandroidrestructure.application.WecenterApplication;

/**
 * Description:
 *
 * @author huangchen
 * @version 1.0
 * @time 15/8/30 10:44
 */

public class WecenterImageGetter implements Html.ImageGetter {
    private Context context;
    private int padding ;

    private WecenterImageGetter(Builder builder){
        this.context = builder.context;
        this.padding = builder.padding;
    }

    @Override
    public Drawable getDrawable(String source) {
        Bitmap bitmap = ImageLoader.getInstance().loadImageSync(source, ImageOptions.optionsImage);
        if (bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int requestWidth;
            int requestHeight;
            int textViewWidth = WecenterApplication.sWidthPix - DisplayUtil.dip2px(context, padding);
            if (width > textViewWidth) {
                requestWidth = textViewWidth;
                requestHeight = (int) (height * (textViewWidth / (width * 1.0)));
            } else if (width > textViewWidth / 2.0 ) {
                requestWidth = textViewWidth;
                requestHeight = (int) (height * (textViewWidth / (width * 1.0)));
            } else {
                requestWidth = width;
                requestHeight = height;
            }
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, requestWidth, requestHeight, true);
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap1);
            if (bitmap.isRecycled()) {
                bitmap.recycle();
            }
            if (bitmap1.isRecycled()) {
                bitmap1.recycle();
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;
        }
        return null;
    }

    public static class Builder {
        private Context context;
        private int padding = 40;

        public Builder(Context context) {
            this.context = context;
        }
        public Builder padding(int padding){
            this.padding = padding;
            return this;
        }

        public WecenterImageGetter build(){
            return new WecenterImageGetter(this);
        }
    }
}
package org.iflab.wecentermobileandroidrestructure.tools;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.iflab.wecentermobileandroidrestructure.R;

/**
 * Created by Lyn on 15/7/16.
 */
public class ImageOptions {
    public static DisplayImageOptions optionsImage = new DisplayImageOptions
            .Builder()
            .showImageForEmptyUri(R.mipmap.test_image_not_exist)
            .showImageOnFail(R.mipmap.test_image_not_exist)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .build();
    public static DisplayImageOptions optionsImagePersonalDetailAvatar = new DisplayImageOptions
            .Builder()
            .showImageForEmptyUri(R.mipmap.test_image_not_exist)
            .showImageOnFail(R.mipmap.test_image_not_exist)
            .cacheInMemory(false)
            .cacheOnDisk(false)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .imageScaleType(ImageScaleType.EXACTLY)
            .build();
}

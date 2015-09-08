package org.iflab.wecentermobileandroidrestructure.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.tools.RecycleBitmapInLayout;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by hcjcch on 15/6/20.
 */
public class PhotoDetailFragment extends BaseFragment {

    private PhotoView photoView;
    private ImageView loadFailed;
    private String photoUri;
    private FrameLayout rootLayout;
    public static PhotoDetailFragment getInstance(String photoUri) {
        Bundle bundle = new Bundle();
        bundle.putString("photoUri", photoUri);
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        photoUri = bundle.getString("photoUri");
        super.onCreate(savedInstanceState);
    }

    public void setData(String uriString) {
        photoUri = uriString;
    }

    public static DisplayImageOptions optionsImage = new DisplayImageOptions
            .Builder()
            .showImageForEmptyUri(R.mipmap.test_image_not_exist)
            .showImageOnFail(R.mipmap.test_image_not_exist)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .cacheOnDisk(true)
            .resetViewBeforeLoading(true)
            .cacheInMemory(false)
            .considerExifParams(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .build();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootLayout = (FrameLayout) inflater.inflate(R.layout.fragment_photo_detail, container, false);
        photoView = (PhotoView) rootLayout.findViewById(R.id.photoview);
        loadFailed = (ImageView) rootLayout.findViewById(R.id.imageLoadFail);
        ImageLoader.getInstance().loadImage(photoUri, optionsImage, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                loadFailed.setVisibility(View.VISIBLE);
                photoView.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                photoView.setImageBitmap(loadedImage);
            }
        });
        return rootLayout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RecycleBitmapInLayout.getInstance(true).recycle(rootLayout);
    }
}
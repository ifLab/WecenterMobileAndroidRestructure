package org.iflab.wecentermobileandroidrestructure.application;


import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.iflab.wecentermobileandroidrestructure.tools.DroidUncaughtExceptionHandler;
import org.iflab.wecentermobileandroidrestructure.tools.MD5Transform;
import org.litepal.LitePalApplication;

/**
 * Created by hcjcch on 15/5/23.
 */
public class WecenterApplication extends LitePalApplication {
    public static int sWidthPix;
    public static int sHeightPix;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext());
        sWidthPix = getResources().getDisplayMetrics().widthPixels;
        sHeightPix = getResources().getDisplayMetrics().heightPixels;
        initImageLoader(getApplicationContext());
//        initRefWatcher();
        Thread.setDefaultUncaughtExceptionHandler(new DroidUncaughtExceptionHandler(this));
//        int[] a = {1};
//        System.out.print(a[11]);
    }

    private void initRefWatcher() {
        refWatcher = LeakCanary.install(this);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .diskCacheFileCount(300)
                        // .imageDownloader(new MyImageDownloader(context))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static RefWatcher getRefWatcher(Context context) {
        WecenterApplication application = (WecenterApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
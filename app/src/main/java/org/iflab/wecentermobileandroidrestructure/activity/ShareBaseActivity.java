package org.iflab.wecentermobileandroidrestructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import org.iflab.wecentermobileandroidrestructure.listener.ShareRegister;

/**
 * Created by Lyn on 15/8/23.
 */
public class ShareBaseActivity extends SwipeBackBaseActivity implements ShareRegister {

    UMSocialService mController;
    String shareUrl = "www.wecenter.com";
    String title;
    String header = "来自wecenter的分享 : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initShare();
    }

    private void initShare() {
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
// 设置分享内容

// 设置分享图片, 参数2为图片的url地址，不支持png !
        mController.setShareMedia(new UMImage(getApplicationContext(),
                "http://down.55.la/data/soft_img/1374892321.jpg"));

        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN,SHARE_MEDIA.TENCENT);
    }
    /**
     * 配置分享平台参数</br>
     */
    private void configPlatforms() {
        // 添加新浪SSO授权
//        mController.getConfig().setSsoHandler(new SinaSsoHandler());

        // 添加QQ、QZone平台
        addQQQZonePlatform();

        // 添加微信、微信朋友圈平台
        addWXPlatform();

        addSinaPlaform();
    }

    /**
     * @功能描述 : 添加微信平台分享
     * @return
     */
    private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx8143cec221dd2dd9";
        String appSecret = "96bb25c0d69b9f5b3a7769f68e68baa7";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(getApplicationContext(), appId, appSecret);
        wxHandler.addToSocialSDK();
        wxHandler.setTitle(title);
        wxHandler.setTargetUrl(shareUrl);
        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(getApplicationContext(), appId, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        wxCircleHandler.setTitle(title);
        wxCircleHandler.setTargetUrl(shareUrl);
    }

    /**
     * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
     *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
     *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
     *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
     * @return
     */
    private void addQQQZonePlatform() {
        String appId = "1104825624";
        String appKey = "vDkmHGzG4nC0iufF";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(ShareBaseActivity.this,
                appId, appKey);
        qqSsoHandler.setTargetUrl(shareUrl);
        qqSsoHandler.setTitle(title);
//        qqSsoHandler.shareToQQ(header + title);
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(ShareBaseActivity.this, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
        qZoneSsoHandler.setTargetUrl(shareUrl);
    }

    private void addSinaPlaform(){
        SinaSsoHandler sinaSsoHandler = new SinaSsoHandler(getApplicationContext());
        sinaSsoHandler.setTargetUrl(shareUrl);
        sinaSsoHandler.addToSocialSDK();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    @Override
    public void setShareContent(String title,String shareUrl){
        this.title = title;
        this.shareUrl = shareUrl;
        mController.setShareContent(header + title);
        configPlatforms();
    }
}

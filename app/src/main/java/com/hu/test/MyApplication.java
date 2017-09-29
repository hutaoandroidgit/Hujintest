package com.hu.test;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;
import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by TT on 2017/6/29.
 */

public class MyApplication extends SkinBaseApplication {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //  initHotfix();

        //Facebook图片加载库初始化
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, new OkHttpClient())
                .build();
        Fresco.initialize(this, config);

        initSkin();

        //网络okgo初始化
        initOkgo();

    }

    private void initOkgo() {
        //创建build
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();


        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);//log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor); //添加okgo默认debug模式

        //超时时间设置
        builder.readTimeout(30000, TimeUnit.MILLISECONDS);  //设置全局读取超时时间
        builder.writeTimeout(30000, TimeUnit.MILLISECONDS); //设置写入数据超时时间
        builder.connectTimeout(30000, TimeUnit.MILLISECONDS); //设置连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
       // builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失
       // builder.cookieJar(new CookiesManager());                                    //自定义cook管理

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
       // HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
       // builder.hostnameVerifier(new SafeHostnameVerifier());

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .setRetryCount(3)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
        ;
    }

    private void initSkin() {
        SkinConfig.setCanChangeStatusColor(true);
        SkinConfig.setCanChangeFont(true);
        SkinConfig.setDebug(true);
        // SkinConfig.addSupportAttr("tabLayoutIndicator", new TabLayoutIndicatorAttr());
        // SkinConfig.addSupportAttr("button", new RadioButtonAttr());
        SkinConfig.enableGlobalSkinApply();
    }

/*
    private void initHotfix() {
        SophixManager.getInstance().setContext(this)
                .setAppVersion("1.0.0")
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Toast.makeText(getApplicationContext(),"加载成功=="+info,Toast.LENGTH_SHORT).show();
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            Toast.makeText(getApplicationContext(),"需要重启=="+info,Toast.LENGTH_SHORT).show();
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                             SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Toast.makeText(getApplicationContext(),"错误代码code=="+code+"   错误消息"+info,Toast.LENGTH_SHORT).show();
                        }
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中


    }
*/


}

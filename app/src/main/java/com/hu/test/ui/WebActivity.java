package com.hu.test.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hu.test.R;
import com.hu.test.utils.StatusBarUtil;
import com.hu.test.utils.UtilCom;


public class WebActivity extends Activity {


    private WebView mWebView;
    private Handler mHandler = new Handler();
    private static final int REQUEST_CAMERA = 0;
    private String telurl;
    private Toolbar toolbar;
    private TextView mTitle;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initID();
        initview();
        initWebView();

    }

    private void initID() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.mTitle);
        mWebView = (WebView) findViewById(R.id.mwebview);
        progressBar = (ProgressBar) findViewById(R.id.ss_htmlprogessbar);
    }


    private void initview() {
        if (toolbar != null) {
            toolbar.setTitle("");
            mTitle.setText("");
            toolbar.setVisibility(View.VISIBLE);
            StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
            toolbar.setNavigationIcon(R.mipmap.arrow_finish);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                }


            });
        }


    }

    private void initWebView() {

        progressBar.setVisibility(View.VISIBLE);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//设置可以运行JS脚本
        mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "jsObj");
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
        settings.setSupportZoom(true);//  设置可以支持缩放
        settings.setUseWideViewPort(true); //扩大比例的缩放
//			mWebView.setInitialScale(25);//为25%，最小缩放等级
        settings.setBuiltInZoomControls(false);// 设置出现缩放工具
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不设置缓存
        mWebView.setVisibility(View.VISIBLE);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());

        Intent data = getIntent();
        final String url = data.getStringExtra("url");
        final String title = data.getStringExtra("title");

        if (title != null) {
            mTitle.setText(title);
        }

        if (url != null) {

            if (!UtilCom.isConn(WebActivity.this)) {
                Toast.makeText(WebActivity.this, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                return;
            }


        }

        mWebView.loadUrl(url);
        mTitle.setText(title);
    }


    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {
            progressBar.setVisibility(View.VISIBLE);




            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            progressBar.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
          //  view.loadUrl("javascript:showButtons('" + Utils.getVersionName(WebActivity.this) + "')");
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            progressBar.setVisibility(View.GONE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            // TODO Auto-generated method stub
            if (newProgress != 100) {
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }


    class MyJavaScriptInterface {

        //关闭webView
        @JavascriptInterface
        public void APP_Jump_CloseWebView() {
            mHandler.post(new Runnable() {
                public void run() {
                    finish();
                }
            });
        }



        //跳转新的web
        @JavascriptInterface
        public void APP_Jump_Webview(final String url, final String title) {
            mHandler.post(new Runnable() {
                public void run() {
                    mWebView.loadUrl(url);
                    mTitle.setText(title);
                }
            });
        }




        //给我鼓励
        @JavascriptInterface
        public void APP_JUMP_APPMARKET() {
            mHandler.post(new Runnable() {
                public void run() {
                    try {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ganqianwang.ganqianwang"));
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(WebActivity.this, "未安装手机助手",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }


    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();

    }


    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.hu.test.ui.drawer.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hu.test.R;
import com.hu.test.ui.BaseActivity;

import solid.ren.skinlibrary.SkinLoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by TT on 2017/6/29.
 */
public class MessageActivity extends BaseActivity {

    private ImageView iv;

    public MessageActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        sum(1,2);
        iv = (ImageView) findViewById(R.id.iv_img);
        iv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SkinManager.getInstance().loadSkin("theme_style.skin",
                                new SkinLoaderListener() {
                                    @Override
                                    public void onStart() {
                                        Log.i("SkinLoaderListener", "正在切换中");
                                        //dialog.show();
                                    }

                                    @Override
                                    public void onSuccess() {
                                        Log.i("SkinLoaderListener", "切换成功");
                                    }

                                    @Override
                                    public void onFailed(String errMsg) {
                                        Log.i("SkinLoaderListener", "切换失败:" + errMsg);
                                    }

                                    @Override
                                    public void onProgress(int progress) {
                                        Log.i("SkinLoaderListener", "皮肤文件下载中:" + progress);

                                    }
                                }

                        );
                    }
                }

        );


    }

    public int sum(int a, int b){
        return a + b;
    }

    public int sum1(int a, int b){
        return a + b;
    }
}

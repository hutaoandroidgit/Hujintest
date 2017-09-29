package com.hu.test.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hu.test.R;
import com.hu.test.utils.PerfectClickListener;
import com.hu.test.utils.StatusBarUtil;

import solid.ren.skinlibrary.base.SkinBaseActivity;

/**
 * Created by TT on 2017/6/29.
 */

public class BaseActivity extends SkinBaseActivity{

    private View baseView;
    private View childView;
    private LinearLayout llProgress;
    private LinearLayout refresh;
    private AnimationDrawable mAnimationDrawable;
    private ImageView img;
    private Toolbar toolbar;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        baseView = LayoutInflater.from(this).inflate(R.layout.activity_base, null, false);
        childView = getLayoutInflater().inflate(layoutResID, null, false);

        //content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        childView.getRootView().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) baseView.getRootView().findViewById(R.id.container);
        mContainer.addView(childView.getRootView());//这里的意思就是让子类布局添加到base布局中，不需要每一个去添加空界面
        getWindow().setContentView(baseView.getRootView());//最后让界面去显示baseview

        //设置透明状态栏
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimaryDark),0);
        //加载中
        llProgress = getView(R.id.ll_progress_bar);
        //加载失败
        refresh = getView(R.id.ll_error_refresh);

        toolbar = getView(R.id.tool_bar);

        img = getView(R.id.img_progress);

        // 加载动画，gif动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();

        // 点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }

            
        });
        childView.getRootView().setVisibility(View.GONE);
    }

    /**
     * toolbar文本，简化结构
     * @param text
     */
    public void setTitle(CharSequence text) {
        toolbar.setTitle(text);
    }


    
    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

    /**
     * 显示加载
     */
    protected void showLoading() {
        if (llProgress.getVisibility() != View.VISIBLE) {
            llProgress.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (childView.getRootView().getVisibility() != View.GONE) {
            childView.getRootView().setVisibility(View.GONE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
    }

    /**
     * 显示内容
     */
    protected void showContentView() {
        if (llProgress.getVisibility() != View.GONE) {
            llProgress.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (childView.getRootView().getVisibility() != View.VISIBLE) {
            childView.getRootView().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示失败
     */
    protected void showError() {
        if (llProgress.getVisibility() != View.GONE) {
            llProgress.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (childView.getRootView().getVisibility() != View.GONE) {
            childView.getRootView().setVisibility(View.GONE);
        }
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    
    
}

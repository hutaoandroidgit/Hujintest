package com.hu.test.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hu.test.R;
import com.hu.test.utils.PerfectClickListener;

/**
 * Created by TT on 2017/7/11.
 */

public abstract class BaseFragment extends Fragment {

    protected View base;
    protected View child;
    private RelativeLayout mContainer;
    private LinearLayout mLlProgressBar;
    private AnimationDrawable mAnimationDrawable;
    private LinearLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        base = inflater.inflate(R.layout.fragment_base, null);
        //在拿到被需要继承的布局
         child =  getActivity().getLayoutInflater().inflate(setContent(), null, false);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        child.setLayoutParams(params);
        mContainer = (RelativeLayout) base.findViewById(R.id.container);
        mContainer.addView(child);

        return base;
    }

    /**
     * 子类必须
     * @return
     */
    protected  abstract int setContent() ;

    // fragment是否显示了
    protected boolean mIsVisible = false;
    /**
     * 这里实现fragment的数据缓加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            mIsVisible = true;
            onVisible();
        }else {
            mIsVisible = false;
            onInVisible();
        }

    }

    protected  void onInVisible(){

    }

    protected  void onVisible(){
        loadData();
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected  void loadData(){

    };

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLlProgressBar = getView(R.id.ll_progress_bar);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        mRefresh = getView(R.id.ll_error_refresh);
        // 点击加载失败布局
        mRefresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        child.setVisibility(View.GONE);
    }

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
        if (mLlProgressBar.getVisibility() != View.VISIBLE) {
            mLlProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (child.getVisibility() != View.GONE) {
            child.setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (child.getVisibility() != View.VISIBLE) {
            child.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (child.getVisibility() != View.GONE) {
            child.setVisibility(View.GONE);
        }
    }


}

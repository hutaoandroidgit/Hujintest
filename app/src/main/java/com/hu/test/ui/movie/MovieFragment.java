package com.hu.test.ui.movie;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.hu.test.R;
import com.hu.test.adapter.MyMainFragmentAdapter;
import com.hu.test.ui.BaseFragment;
import com.hu.test.ui.movie.child.MovieMakeFragment;

import java.util.ArrayList;

/**
 * Created by TT on 2017/6/29.
 */
public class MovieFragment extends BaseFragment {


    private ArrayList<String> mTitleList  = new ArrayList<>();
    private ArrayList<Fragment> mFragments  = new ArrayList<>();
    private View view;
    private ViewPager vpBook;
    private TabLayout tabBook;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //加載動畫
        showLoading();
        initFragmentList();

        initID();



        Handler handler =new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //加载完成返回一个view
                showContentView();

            }
        },1500);

        MyMainFragmentAdapter myMainFragmentAdapter =new MyMainFragmentAdapter(getChildFragmentManager(),mFragments,mTitleList);
        vpBook.setAdapter(myMainFragmentAdapter);
        myMainFragmentAdapter.notifyDataSetChanged();
        tabBook.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabBook.setupWithViewPager(vpBook);


    }



    private void initID() {

        if(child != null){
            Toast.makeText(getActivity(),"view不为空",Toast.LENGTH_SHORT).show();
        }
        tabBook = (TabLayout) child.findViewById(R.id.tab_book);
        if(tabBook == null){
            return;
        }
        vpBook = (ViewPager) child.findViewById(R.id.vp_book);
        if(vpBook == null){
            return;
        }


    }

    /**
     * 定装多个tab
     */
    private void initFragmentList() {
        mTitleList.add("正在热映");
        mTitleList.add("即将上映");
        mTitleList.add("剧情");
        mTitleList.add("爱情");
        mTitleList.add("动画");
        mTitleList.add("TOP-250");

        mFragments.add(MovieMakeFragment.newInstance("正在热映"));
        mFragments.add(MovieMakeFragment.newInstance("即将上映"));
        mFragments.add(MovieMakeFragment.newInstance("剧情"));
        mFragments.add(MovieMakeFragment.newInstance("爱情"));
        mFragments.add(MovieMakeFragment.newInstance("动画"));
        mFragments.add(MovieMakeFragment.newInstance("TOP-250"));

    }

    /**
     * 加载布局
     * @return
     */
    @Override
    public int setContent() {
        return R.layout.fragment_moview;
    }
}

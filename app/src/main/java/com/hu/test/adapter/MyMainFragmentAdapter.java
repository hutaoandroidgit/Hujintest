package com.hu.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2017/7/11.
 */
public class MyMainFragmentAdapter  extends FragmentPagerAdapter {


    private List<?> mFragment;
    private ArrayList<String> mTitleList;

    public MyMainFragmentAdapter(FragmentManager fm , ArrayList<?> mFragment) {
        super(fm);
        this.mFragment = mFragment;
    }


    /**
     * 接收首页传递的标题
     */
    public MyMainFragmentAdapter(FragmentManager fm, ArrayList<?> mFragment, ArrayList<String> mTitleList) {
        super(fm);
        this.mFragment =  mFragment;
        this.mTitleList = mTitleList;
    }


    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size()!=0?mFragment.size():0;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitleList != null){
            return mTitleList.get(position);
        }else{
            return "";
        }
    }
}

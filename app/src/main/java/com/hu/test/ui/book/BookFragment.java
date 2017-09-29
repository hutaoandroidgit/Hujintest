package com.hu.test.ui.book;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hu.test.R;
import com.hu.test.ui.BaseFragment;

/**
 * Created by TT on 2017/6/29.
 */
public class BookFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //加载动画
        showLoading();
        //添加title和fragment
        initFrgmentList();

       // showContentView();
    }

    private void initFrgmentList() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_book;
    }
}

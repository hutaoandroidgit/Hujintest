package com.hu.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * Created by TT on 2017/8/28.
 */
public class GridLayoutAdapter extends DelegateAdapter.Adapter {


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 0;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }



    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return null;
    }
}

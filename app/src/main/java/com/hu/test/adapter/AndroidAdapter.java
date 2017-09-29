package com.hu.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hu.test.R;
import com.hu.test.bean.GankDataModle;
import com.hu.test.ui.WebActivity;
import com.hu.test.utils.FrescoUtils;

import java.util.List;

/**
 * Created by TT on 2017/9/7.
 */

public class AndroidAdapter extends BaseQuickAdapter<GankDataModle.ResultsBean> {


    private  Context mContext;

    public AndroidAdapter(Context context , List<GankDataModle.ResultsBean> data) {
        super(R.layout.item_android, data);
        this.mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder holder, final GankDataModle.ResultsBean data) {

        holder.setText(R.id.tv_android_des, data.getDesc())
                .setText(R.id.tv_android_who, data.getWho())
                .setText(R.id.tv_android_time, data.getCreatedAt());

        SimpleDraweeView simpleDraweeView = holder.getView(R.id.sd_android_pic);
        if(data.getUrl() != null){
            FrescoUtils.setController(data.getUrl(), simpleDraweeView);
        }
        if(data.getImages() != null && data.getImages().size()>0){
            FrescoUtils.setController(data.getImages().get(0), simpleDraweeView);
        }


        holder.getView(R.id.ll_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", data.getUrl());
                intent.putExtra("title", data.getDesc());
                mContext.startActivity(intent);
            }
        });

    }
}

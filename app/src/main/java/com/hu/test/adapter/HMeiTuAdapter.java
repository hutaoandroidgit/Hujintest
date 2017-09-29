package com.hu.test.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hu.test.R;
import com.hu.test.bean.GankDataModle;
import com.hu.test.utils.FrescoUtils;

import java.util.List;

/**
 * Created by TT on 2017/9/7.
 */

public class HMeiTuAdapter extends BaseQuickAdapter<GankDataModle.ResultsBean> {


    public HMeiTuAdapter(List<GankDataModle.ResultsBean> data) {
        super(R.layout.item_fuli, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GankDataModle.ResultsBean data) {
        FrescoUtils.setController(data.getUrl(), (SimpleDraweeView) holder.getView(R.id.sd_fuli));
    }
}

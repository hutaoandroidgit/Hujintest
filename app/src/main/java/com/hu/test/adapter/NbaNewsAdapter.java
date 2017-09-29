package com.hu.test.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hu.test.R;
import com.hu.test.bean.NewsMoble;
import com.hu.test.utils.FrescoUtils;

import java.util.List;

/**
 * Created by TT on 2017/9/12.
 */
public class NbaNewsAdapter extends BaseQuickAdapter<NewsMoble.DataBean>{

    private SimpleDraweeView sd;

    public NbaNewsAdapter(List<NewsMoble.DataBean> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsMoble.DataBean dataBean) {

        if(dataBean!=null){
            if(dataBean.getTitle()!= null){
                holder.setText(R.id.tv_nbatitle, dataBean.getTitle());
            }

            if(dataBean.getImageUrls()!=null && dataBean.getImageUrls().size()>0){
                sd = holder.getView(R.id.sd_nba);
                FrescoUtils.setController(dataBean.getImageUrls().get(0), sd);
            }
        }






    }
}

package com.hu.test.adapter;

import android.content.Context;
import android.view.animation.OvershootInterpolator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hu.test.R;
import com.hu.test.bean.Movie250Modle;
import com.hu.test.utils.FrescoUtils;
import com.hu.test.utils.UtilCom;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.List;


/**
 * Created by TT on 2017/9/20.
 */
public class Movie250Adapter extends BaseQuickAdapter<Movie250Modle.SubjectsBean> {

    private Context mContext;

    public Movie250Adapter(Context context, List<Movie250Modle.SubjectsBean> data) {
        super(R.layout.item_movie250, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, Movie250Modle.SubjectsBean data) {

        ViewHelper.setScaleX(helper.itemView, 0.6f);
        ViewHelper.setScaleY(helper.itemView, 0.6f);
        ViewPropertyAnimator.animate(helper.itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        ViewPropertyAnimator.animate(helper.itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();

        helper.setText(R.id.tv_one_title, data.getTitle())
                .setText(R.id.tv_one_directors, UtilCom.formatName(data.getDirectors()))
                .setText(R.id.tv_one_casts, UtilCom.formatCastsName(data.getCasts()))
                .setText(R.id.tv_one_genres, UtilCom.formatGenres(data.getGenres()))
                .setText(R.id.tv_one_rating_rate,"评分"+data.getRating().getAverage());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.iv_one_photo);
        FrescoUtils.setController(data.getImages().getLarge(),simpleDraweeView);
    }
}

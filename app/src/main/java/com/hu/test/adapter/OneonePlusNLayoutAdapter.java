package com.hu.test.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hu.test.R;
import com.hu.test.bean.WeatherMoble;
import com.hu.test.utils.FrescoUtils;

/**
 * Created by TT on 2017/9/8.
 */
public class OneonePlusNLayoutAdapter extends DelegateAdapter.Adapter<MainViewHolder>{


    private  Context context;
    private  SingleLayoutHelper singleLayoutHelper;
    private  WeatherMoble.ShowapiResBodyBean.NowBean  showapi_res_bodyNow;
    private SimpleDraweeView sdWeather;
    private TextView tvWeather, tvTemperature, tvArea, tvWinddirection, tvQuality;

    public OneonePlusNLayoutAdapter(Context context, SingleLayoutHelper singleLayoutHelper, WeatherMoble.ShowapiResBodyBean.NowBean showapi_res_bodyNow) {
        this.context = context;
        this.singleLayoutHelper = singleLayoutHelper;
        this.showapi_res_bodyNow = showapi_res_bodyNow;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vlayout_oneplus, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        sdWeather = (SimpleDraweeView)holder.itemView.findViewById(R.id.sd_weather);
        tvTemperature = (TextView) holder.itemView.findViewById(R.id.tv_temperature);
        tvArea = (TextView) holder.itemView.findViewById(R.id.tv_area);
        tvWinddirection = (TextView) holder.itemView.findViewById(R.id.tv_wind_direction);
        tvQuality = (TextView) holder.itemView.findViewById(R.id.tv_quality);
        tvWeather = (TextView) holder.itemView.findViewById(R.id.tv_weather);


        if(showapi_res_bodyNow != null){
            tvTemperature.setText(showapi_res_bodyNow.getTemperature()+"度");
            tvWeather.setText(showapi_res_bodyNow.getWeather());
            tvQuality.setText(showapi_res_bodyNow.getAqiDetail().getQuality());
            tvWinddirection.setText(showapi_res_bodyNow.getWind_direction()+"："+showapi_res_bodyNow.getWind_power());
            tvArea.setText(showapi_res_bodyNow.getAqiDetail().getArea());
            FrescoUtils.setController(showapi_res_bodyNow.getWeather_pic(),sdWeather );
            Log.e("TAG", "天气适配"+"position=="+position);
        }


    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

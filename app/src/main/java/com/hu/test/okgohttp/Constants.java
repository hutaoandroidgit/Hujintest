package com.hu.test.okgohttp;

import com.hu.test.bean.GankDataModle;
import com.hu.test.bean.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2017/9/6.
 */

public class Constants {
    //轮播图的url  百度音乐
    public final static String API_TING = "https://tingapi.ting.baidu.com/v1/restserver/";
    public final static String BANNER = "ting?from=android&version=5.8.1.0&channel=ppzs&operator=3&method=baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14";
    public final static String API_GANKIO = "https://gank.io/api/";
    public final static String API_Weather = "https://route.showapi.com/9-2";


    private static List<GankDataModle.ResultsBean> bodyResults = new ArrayList<>();


    /**
     * @param bigtype 大类型
     * @param subtype 小类型
     * @param pre_page 每页数量
     * @param page 页数
     * @return
     */
    public static  List<GankDataModle.ResultsBean> okgoGanhuo(String bigtype, String subtype, int pre_page, int page) {

        OkGo.<GankDataModle>get(Constants.API_GANKIO+bigtype+"/"+subtype+"/"+pre_page+"/"+page)
                .execute(new JsonCallback<GankDataModle>() {
                    @Override
                    public void onSuccess(Response<GankDataModle> response) {
                        GankDataModle body = response.body();
                        bodyResults = body.getResults();
                    }
                });
        return bodyResults;
    }
}
